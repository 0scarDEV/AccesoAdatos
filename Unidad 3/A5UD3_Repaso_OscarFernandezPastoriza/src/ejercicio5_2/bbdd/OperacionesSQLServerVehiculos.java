package ejercicio5_2.bbdd;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ejercicio5_2.Vehiculo;
import ejercicio5_2.VehiculoAdapter;
import ejercicio5_2.VehiculoPropio;
import ejercicio5_2.VehiculoRenting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class OperacionesSQLServerVehiculos extends Operaciones {
    public OperacionesSQLServerVehiculos(Conexion con) {
        super(con.getConnection());
    }

    public void createTables() {
        try {
            borrarTablasSiExisten(new String[]{"VEHICULOS_RENTING", "VEHICULOS_PROPIOS", "VEHICULOS"});
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Statement stmt = con.createStatement();

            stmt.addBatch(
                    "CREATE TABLE VEHICULOS (" + "id int PRIMARY KEY IDENTITY," + "matricula char(8) NOT NULL," + "marca varchar(255) NOT NULL," +
                            "modelo varchar(255) NOT NULL," + "combustible char(1) NOT NULL," +
                            "CONSTRAINT CK_Vehiculos_MatriculaValida CHECK (matricula LIKE ('[0-9][0-9][0-9][0-9][A-Z][A-Z][A-Z]'))," +
                            "CONSTRAINT CK_Vehiculos_Combustible CHECK (combustible IN ('G', 'D'))" + ")");

            stmt.addBatch(
                    "CREATE TABLE VEHICULOS_RENTING (" + "id int PRIMARY KEY," + "fecha_inicio date NOT NULL," + "precioMensual money NOT NULL," +
                            "meses int NOT NULL," + "CONSTRAINT FK_RENTING_VEHICULOS FOREIGN KEY (id) REFERENCES VEHICULOS(id)" + ")");

            stmt.addBatch(
                    "CREATE TABLE VEHICULOS_PROPIOS (" + "id int PRIMARY KEY," + "fecha_compra date NOT NULL," + "precio_compra money NOT NULL," +
                            "CONSTRAINT FK_PROPIOS_VEHICULOS FOREIGN KEY (id) REFERENCES VEHICULOS(id)" + ")");

            stmt.executeBatch();
            System.out.println("Tablas creadas correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /*  Código SQL Puro
        CREATE TABLE VEHICULOS (
            id int PRIMARY KEY IDENTITY,
            matricula char(8) NOT NULL,
            marca varchar(255) NOT NULL,
            modelo varchar(255) NOT NULL,
            combustible char(1) NOT NULL,

            CONSTRAINT CK_Vehiculos_MatriculaValida CHECK (matricula LIKE ('[0-9]{4}[A-Z]{3}')),
            CONSTRAINT CK_Vehiculos_Combustible CHECK (combustible IN ('G', 'D'))
        )
        go
        CREATE TABLE VEHICULOS_RENTING (
            id int PRIMARY KEY,
            fecha_inicio date NOT NULL,
            precioMensual money NOT NULL,

            CONSTRAINT FK_RENTING_VEHICULOS FOREIGN KEY (id) REFERENCES VEHICULOS(id)
        )
        go
        CREATE TABLE VEHICULOS_PROPIOS (
            id int PRIMARY KEY,
            fecha_compra date NOT NULL,
            precio_compra money NOT NULL,

            CONSTRAINT FK_PROPIOS_VEHICULOS FOREIGN KEY (id) REFERENCES VEHICULOS(id)
        )
        */
    }

    private boolean existeMatricula(Vehiculo vehiculo) {
        crearFuncionExisteMatricula();

        CallableStatement cs;
        try {
            cs = con.prepareCall("{? = call fn_comprobarSiHayMatricula(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, vehiculo.getMatricula());
            cs.execute();
            return cs.getInt(1) == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void crearFuncionExisteMatricula() {
        /*
        CREATE FUNCTION fn_comprobarSiHayMatricula(
            @mat varchar(8)
        ) RETURNS int as BEGIN
            IF (@mat IN (SELECT * FROM VEHICULOS))
                RETURN 1;
            ELSE
                RETURN 0;
        END
         */

        try {
            Statement st = con.createStatement();

            st.execute("DROP FUNCTION IF EXISTS fn_comprobarSiHayMatricula");

            st.execute(
                    "CREATE FUNCTION fn_comprobarSiHayMatricula(" + "   @mat varchar(8)" + ") RETURNS int as BEGIN " + "   DECLARE @esMatricula INT" +
                            "   IF (@mat IN (SELECT matricula FROM VEHICULOS)) " + "       SET @esMatricula = 1 " + "   ELSE " +
                            "       SET @esMatricula = 0" + "   RETURN @esMatricula " + "END");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO VEHICULOS (matricula, marca, modelo, combustible) VALUES (?, ?, ?, ?)";
        PreparedStatement ps;
        int id;

        if (existeMatricula(vehiculo)) {
            return;
        }

        // Insertamos el vehículo en la tabla VEHICULOS.
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vehiculo.getMatricula());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setString(4, vehiculo.getCombustible());
            ps.execute();
            System.out.println("Insertado el vehículo con matrícula " + vehiculo.getMatricula());

            // Obtenemos en un resultset el valor de la clave primaria.
            ResultSet rs = ps.getGeneratedKeys();

            // Como solo puede devolver un valor, no hacemos un while, si no un if directamente.
            if (rs.next()) {
                // Almacenamos la clave primaria para insertarla como clave en las tablas específicas.
                id = rs.getInt(1);
            } else {
                System.err.println("No existe primary key en la tabla");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Insertamos el vehículo en la tabla que corresponda según sea VehiculoPropio o VehiculoRenting
        if (vehiculo instanceof VehiculoPropio) {
            // Si el vehículo es una instancia de (un objeto de la clase) VehiculoPropio
            sql = "INSERT INTO VEHICULOS_PROPIOS (id, fecha_compra, precio_compra) VALUES (?, ?, ?)";
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setDate(2, ((VehiculoPropio) vehiculo).getFechaCompra());
                ps.setObject(3, ((VehiculoPropio) vehiculo).getPrecioCompra());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (vehiculo instanceof VehiculoRenting) {
            // Si el vehículo es una instancia de (un objeto de la clase) VehiculoRenting
            sql = "INSERT INTO VEHICULOS_RENTING (id, fecha_inicio, precioMensual, meses) VALUES (?, ?, ?, ?)";
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setDate(2, ((VehiculoRenting) vehiculo).getFechaInicio());
                ps.setObject(3, ((VehiculoRenting) vehiculo).getPrecioMensual());
                ps.setObject(4, ((VehiculoRenting) vehiculo).getMeses());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void insertarVehiculosJSON(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            Vehiculo vehiculo = null;

            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray vehiculosArray = jsonObject.getAsJsonArray("vehiculos");

            for (JsonElement element : vehiculosArray) {
                JsonObject objetoVehiculo = element.getAsJsonObject();

                String matricula = objetoVehiculo.get("matricula").getAsString();
                String marca = objetoVehiculo.get("marca").getAsString();
                String modelo = objetoVehiculo.get("modelo").getAsString();
                String combustible = objetoVehiculo.get("tipo").getAsString();

                if (objetoVehiculo.has("vehiculoRenting")) {
                    JsonObject objetoVehPropio = objetoVehiculo.getAsJsonObject("vehiculoRenting");

                    String[] compFecha = objetoVehPropio.get("fechaInicio").getAsString().split("-");
                    Date fechaInicio = new Date(LocalDate.of(Integer.parseInt(compFecha[0]), Integer.parseInt(compFecha[1]), Integer.parseInt(compFecha[2])).toEpochDay());

                    double precioMensual = objetoVehPropio.get("precioMensual").getAsDouble();
                    int meses = objetoVehPropio.get("meses").getAsInt();

                    vehiculo = new VehiculoRenting(matricula, marca, modelo, combustible.charAt(0), fechaInicio, precioMensual, meses);
                } else if (objetoVehiculo.has("vehiculoPropio")) {
                    JsonObject objetoVehRenting = objetoVehiculo.getAsJsonObject("vehiculoPropio");

                    String[] compFecha = objetoVehRenting.get("fechaCompra").getAsString().split("-");
                    Date fechaCompra = new Date(LocalDate.of(Integer.parseInt(compFecha[0]), Integer.parseInt(compFecha[1]), Integer.parseInt(compFecha[2])).toEpochDay());
                    double precioCompra = objetoVehRenting.get("precio").getAsDouble();

                    vehiculo = new VehiculoPropio(matricula, marca, modelo, combustible.charAt(0), fechaCompra, precioCompra);
                }

                insertarVehiculo(vehiculo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertarVehiculosJSON2(String fileName) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Vehiculo.class, new VehiculoAdapter()).create();
        try {
            JsonObject jsonObject = gson.fromJson(new FileReader(fileName), JsonObject.class);
            JsonArray vehiculosArray = jsonObject.getAsJsonArray("vehiculos");
            Type listType = new TypeToken<List<Vehiculo>>() {}.getType();
            List<Vehiculo> vehiculos = gson.fromJson(vehiculosArray, listType);

            for (Vehiculo vehiculo : vehiculos) {
                insertarVehiculo(vehiculo);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
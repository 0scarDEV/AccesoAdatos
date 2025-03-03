package comunes;

import clases.AdaptadorEmpleado;
import clases.Empleado;
import clases.Familiar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OperacionesSQLServerFamiliares extends Operaciones {
    public OperacionesSQLServerFamiliares(Connection con) {
        super(con);
    }

    // region E7_1
    public void crearTablas() {
        try {
            // Crear tabla Familiares
            Statement stmt = con.createStatement();
            stmt.addBatch(
            "CREATE TABLE FAMILIAR ("
                + "NSS_empleado VARCHAR(15) NOT NULL,"
                + "Numero SMALLINT NOT NULL,"
                + "NSS VARCHAR(15) NOT NULL,"
                + "Nombre VARCHAR(25) NOT NULL,"
                + "Apellido1 VARCHAR(25) NOT NULL,"
                + "Apellido2 VARCHAR(25),"
                + "FechaNacimiento DATE,"
                + "Parentesco VARCHAR(20) NOT NULL,"
                + "Sexo CHAR(1) DEFAULT 'M'" +
                ")"
            );

            stmt.addBatch(
            "ALTER TABLE FAMILIAR ADD CONSTRAINT PK_FAMILIAR PRIMARY KEY (NSS_empleado, Numero)"
            );

            stmt.addBatch(
            "ALTER TABLE FAMILIAR ADD CONSTRAINT FK_FAMILIAR_EMPLEADOS FOREIGN KEY (NSS_empleado) REFERENCES EMPREGADO(NSS)"
            );

            stmt.addBatch(
            "ALTER TABLE FAMILIAR ADD CONSTRAINT CK_SEXO CHECK (Sexo IN ('H', 'M'))"
            );

            stmt.executeBatch();
            System.out.println("Tabla FAMILIAR creada correctamente");
        } catch (Exception e) {
            System.out.println("Error al crear la tabla FAMILIAR");
            e.printStackTrace();
        }
    }
    // endregion

    // region E7_2
    public boolean insertarFamiliar(String nssEmpregado, Familiar familiar) {
        crearProcedimientoExisteFamiliar();
        try {
            CallableStatement cs = con.prepareCall("{call prExisteFamiliar(?, ?, ?)}");
            cs.setString(1, nssEmpregado);
            cs.setString(2, familiar.getNss());
            cs.registerOutParameter(3, Types.BIT);
            cs.execute();

            if (cs.getBoolean(3)) {
                System.err.println("Ya existe un familiar con el NSS " + familiar.getNss() + " para el empleado " + nssEmpregado);
                return false;
            }

            String sql =
                "INSERT INTO FAMILIAR (" +
                    "NSS_empleado, Numero, NSS, Nombre, Apellido1, Apellido2, FechaNacimiento, Parentesco, Sexo " +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            ;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nssEmpregado);
            ps.setInt(2, numeroFamiliar(nssEmpregado));
            ps.setString(3, familiar.getNss());
            ps.setString(4, familiar.getNome());
            ps.setString(5, familiar.getApelido1());
            ps.setString(6, familiar.getApelido2());
            ps.setDate(7, familiar.getDataNacemento());
            ps.setString(8, familiar.getParentesco());
            ps.setString(9, String.valueOf(familiar.getSexo()));
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Conseguir el número de familiar para un empleado
    private int numeroFamiliar(String nssEmpregado) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT MAX(Numero) FROM FAMILIAR WHERE NSS_empleado = ?");
            ps.setString(1, nssEmpregado);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Crear un procedimiento que se le pase el empleado y el nss del familiar y devuelva en un parámetro de tipo bit si existe o no ( 0 no existe, 1 existe).
    private void crearProcedimientoExisteFamiliar() {
        try {
            Statement stmt = con.createStatement();
            stmt.execute("DROP PROCEDURE IF EXISTS prExisteFamiliar");

            stmt.execute(
            "CREATE PROCEDURE prExisteFamiliar (" +
                    "@NSS_empleado VARCHAR(15), " +
                    "@NSS VARCHAR(15), " +
                    "@existe BIT OUTPUT" +
                ") AS BEGIN " +
                    "IF EXISTS (SELECT * FROM FAMILIAR WHERE NSS_empleado = @NSS_empleado AND NSS = @NSS) " +
                        "SET @existe = 1 " +
                    "ELSE " +
                        "SET @existe = 0 " +
                "END"
            );
            System.out.println("Procedimiento prExisteFamiliar creado correctamente");
        } catch (Exception e) {
            System.out.println("Error al crear el procedimiento prExisteFamiliar");
            e.printStackTrace();
        }
    }
    // endregion

    // region E7_3
    //Inserta los familiares de los empleados que vengan de un json. Si hay varios familiares de un empleado con el mismo nss de familiar, se inserta el primero que que se encuentre. Se creará un json con los familiares errorneos que no se han podido insertar. El json de los familiares a insertar tiene la siguiente estructura:
    public void insertarFamiliarJson(String nombreFichero, String nombreFicheroCambios){
        Gson gson = new GsonBuilder().registerTypeAdapter(Empleado.class,new AdaptadorEmpleado()).create();

        try{
            JsonObject jsonObject = gson.fromJson(new FileReader(nombreFichero), JsonObject.class);
            JsonArray jsonArray = jsonObject.getAsJsonArray("empleados");
            Type tipoLista = new TypeToken<List<Empleado>>(){}.getType();
            List<Empleado> empleados = gson.fromJson(jsonArray, tipoLista);
            Map<String, List<Familiar>> familiaresCorrectos = new LinkedHashMap<>();
            Map<String, List<Familiar>> familiaresErroneos = new LinkedHashMap<>();

            for (Empleado empleado : empleados) {
                for (Familiar familiar : empleado.getFamiliares()) {
                    if (!insertarFamiliar(familiar.getNssEmpregado(), familiar)) {
                        System.out.println("No se ha podido insertar el familiar: " + familiar.getNss());

                        if (familiaresErroneos.containsKey(empleado.getNss())) {
                            familiaresErroneos.get(empleado.getNss()).add(familiar);
                        } else {
                            List<Familiar> familiares = new ArrayList<>();
                            familiares.add(familiar);
                            familiaresErroneos.put(empleado.getNss(), familiares);
                        }
                    } else {
                        System.out.println("El familiar " + familiar.getNss() + " se ha insertado correctamente");
                        if (familiaresCorrectos.containsKey(empleado.getNss())) {
                            familiaresCorrectos.get(empleado.getNss()).add(familiar);
                        } else {
                            List<Familiar> familiares = new ArrayList<>();
                            familiares.add(familiar);
                            familiaresCorrectos.put(empleado.getNss(), familiares);
                        }
                    }
                }
            }

            crearJsonFamiliaresErroneos(familiaresCorrectos, familiaresErroneos, nombreFicheroCambios);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void crearJsonFamiliaresErroneos(Map<String, List<Familiar>> correctos, Map<String, List<Familiar>> erroneos, String nombreFichero) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject objetoEmpleados = new JsonObject();
        JsonArray jsonArrayCorrectos = new JsonArray();
        JsonArray jsonArrayErroneos = new JsonArray();

        objetoEmpleados.add("familiaresInsertados", jsonArrayCorrectos);
        objetoEmpleados.add("familiaresErroneos", jsonArrayErroneos);

        try (FileWriter fileWriter = new FileWriter(nombreFichero)) {
            insertarFamiliares(correctos, jsonArrayCorrectos);
            insertarFamiliares(erroneos, jsonArrayErroneos);
            gson.toJson(objetoEmpleados, fileWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void insertarFamiliares(Map<String, List<Familiar>> correctos, JsonArray jsonArrayCorrectos) {
        for (Map.Entry<String, List<Familiar>> entry : correctos.entrySet()) {
            JsonObject objetoEmpleado = new JsonObject();
            objetoEmpleado.addProperty("NSS", entry.getKey());

            JsonArray jsonArrayFamiliares = new JsonArray();
            for (Familiar familiar : entry.getValue()) {
                JsonObject familiarJson = new JsonObject();
                familiarJson.addProperty("NSS", familiar.getNss());
                familiarJson.addProperty("NSS_empregado", familiar.getNssEmpregado());
                familiarJson.addProperty("nome", familiar.getNome());
                familiarJson.addProperty("apelido1", familiar.getApelido1());
                familiarJson.addProperty("apelido2", familiar.getApelido2());
                familiarJson.addProperty("dataNacemento", familiar.getDataNacementoAsStr());
                familiarJson.addProperty("parentesco", familiar.getParentesco());
                familiarJson.addProperty("sexo", familiar.getSexo());
                jsonArrayFamiliares.add(familiarJson);
            }

            objetoEmpleado.add("familiares", jsonArrayFamiliares);
            jsonArrayCorrectos.add(objetoEmpleado);
        }
    }
    // endregion
}

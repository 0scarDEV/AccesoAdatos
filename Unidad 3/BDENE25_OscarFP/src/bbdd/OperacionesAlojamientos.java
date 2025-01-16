package bbdd;

import clases.Alojamiento;
import clases.CasaRural;
import clases.Hotel;
import clases.HotelSpa;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Óscar Fernández Pastoriza - 52862191D */
public class OperacionesAlojamientos {
    protected Connection con;

    public OperacionesAlojamientos(Conexion con) {
        this.con = con.getConnection();
    }

    public boolean existeAlojamiento(Alojamiento alojamiento) {
        return existeAlojamiento(alojamiento.getNombre());
    }

    private boolean existeAlojamiento(String nombreAlojamiento) {
        CallableStatement cs;
        try {
            cs = con.prepareCall("{? = call fnExisteAlojamiento(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, nombreAlojamiento);
            cs.execute();

            return cs.getString(1) != null;
        } catch (SQLException e) {
            System.err.println("Ocurrió un error al intentar verificar la existencia de un alojamiento");
            throw new RuntimeException(e);
        }
    }

    private int getCodigoAlojamiento(String nombreAlojamiento) {
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT codigo FROM ALOJAMIENTO WHERE nombre = ?");
            ps.setString(1, nombreAlojamiento);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Ocurrió un error al obtener el código del alojamiento indicado");
            throw new RuntimeException(e);
        }
    }

    //region EJERCICIO 1
    public void insertarAlojamiento(Alojamiento alojamiento) throws SQLException {
        if (existeAlojamiento(alojamiento)) {
            System.err.println("ERROR. El alojamiento \"" + alojamiento.getNombre() + "\" ya existe.");
            return;
        }

        int idAlojamientoInsertado;
        con.setAutoCommit(false);

        // region InsertarAlojamiento
        try {
            PreparedStatement ps = con.prepareStatement(
            "INSERT INTO ALOJAMIENTO (nombre, direccion, localidad, telefono, precio_habitacion, cama_extra, numHabitaciones) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, alojamiento.getNombre());
            ps.setString(2, alojamiento.getDireccion());
            ps.setString(3, alojamiento.getLocalidad());
            ps.setString(4, alojamiento.getTelefono());
            ps.setDouble(5, alojamiento.getPrecioHabitacion());
            ps.setDouble(6, alojamiento.getCamaExtra());
            ps.setInt(7, alojamiento.getNumHabitaciones());

            ps.execute();

            // Obtenemos en un resultset el valor de la clave primaria.
            ResultSet rs = ps.getGeneratedKeys();

            // Como solo puede devolver un valor, hacemos un if en vez de un while.
            if (rs.next()) {
                // Almacenamos la clave primaria para insertarla como clave en las tablas específicas.
                idAlojamientoInsertado = rs.getInt(1);
            } else {
                System.err.println("ERROR. No existe primary key en la tabla. Se desharán los cambios hechos hasta ahora.");
                con.rollback();
                return;
            }
        } catch (SQLException e) {
            System.err.println("ERROR. Ocurrió un error al insertar un alojamiento. Se desharán los cambios.");
            con.rollback();
            throw new RuntimeException(e);
        }
        // endregion
        try {
            PreparedStatement ps;
            switch (alojamiento) {
                case Hotel hotel -> {
                    // NOTA: Según MI planteamiento del ejercicio no tiene sentido conseguir el nombre del hotel sede para después comprobar su existencia por el código. Únicamente lo hago para cumplir con el requerimiento de "Para comprobar la existencia de un hotel se hará mediante....".
                    if (getCodHotelSiExiste(getNombreHotelSiExiste(hotel.getHotelSede())) == null) {
                        System.err.println("ERROR. Se intentó insertar el hotel " + alojamiento.getNombre() + " (" + idAlojamientoInsertado + ") pero la sede " + hotel.getHotelSede() + " no existe. Se desharán los cambios hechos hasta ahora.");
                        con.rollback();
                        return;
                    }

                    ps = con.prepareCall("INSERT INTO HOTEL (cod_hotel, estrellas, hotelsede) VALUES (?, ?, ?)");
                    ps.setInt(1, idAlojamientoInsertado);
                    ps.setInt(2, hotel.getNumEstrellas());
                    ps.setInt(3, hotel.getHotelSede());
                    ps.execute();

                    if (hotel instanceof HotelSpa hotelSpa) {
                        ps = con.prepareCall("INSERT INTO HOTELSPA (cod_spa, gorro, capacidad) VALUES (?, ?, ?)");
                        ps.setInt(1, idAlojamientoInsertado);
                        ps.setString(2, String.valueOf(hotelSpa.getGorro()));
                        ps.setInt(3, hotelSpa.getCapacidad());
                        ps.execute();
                        System.out.println("El alojamiento \"" + alojamiento.getNombre() + "\" (" + idAlojamientoInsertado + ") ha sido insertado como un hotel con spa.");
                    } else {
                        System.out.println("El alojamiento \"" + alojamiento.getNombre() + "\" (" + idAlojamientoInsertado + ") ha sido insertado como un hotel.");
                    }
                }
                case CasaRural casaRural -> {
                    ps = con.prepareCall("INSERT INTO CASARURAL (cod_casa, alquiler_completa) VALUES (?, ?)");
                    ps.setInt(1, idAlojamientoInsertado);
                    ps.setString(2, String.valueOf(casaRural.getAlquilerCompleta()));
                    ps.execute();
                    System.out.println("El alojamiento \"" + alojamiento.getNombre() + "\" (" + idAlojamientoInsertado + ") ha sido insertado como una casa rural.");
                }
                default -> {
                    System.err.println("El tipo de alojamiento introducido no está contemplado en esta operación. Se desharán los cambios");
                    con.rollback();
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR. Ocurrió un error al insertar un alojamiento en la tabla específica. Se desharán los cambios.");
            con.rollback();
            throw new RuntimeException(e);
        }

        con.commit();
        con.setAutoCommit(true);
    }

    public String getNombreHotelSiExiste(int codHotel) {
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT nombre FROM ALOJAMIENTO WHERE codigo = ?");
            ps.setInt(1, codHotel);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return "";
            }
        } catch (SQLException e) {
            System.err.println("ERROR. Ocurrió un error al tratar de obtener el nombre de un hotel, aún contemplando su existencia");
            throw new RuntimeException(e);
        }
    }
    public Integer getCodHotelSiExiste(String nombreHotel) {
        Integer codHotel;
        try {
            PreparedStatement ps = con.prepareStatement(
            "SELECT cod_hotel FROM HOTEL WHERE cod_hotel IN (SELECT codigo FROM ALOJAMIENTO WHERE nombre = ?)"
            );
            ps.setString(1, nombreHotel);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                codHotel = rs.getInt(1);
            } else {
                codHotel = null;
            }
        } catch (SQLException e) {
            System.err.println("ERROR. Ocurrió un error al tratar de obtener el código de un hotel, aún contemplando su existencia");
            throw new RuntimeException(e);
        }
        return codHotel;
    }
    //endregion

    // region EJERCICIO 2
    public void darBajaALojamiento(Alojamiento alojamiento) {
        // Comprobar existencia
        if (!existeAlojamiento(alojamiento)) {
            System.err.println("ERROR. El alojamiento que intentas dar de baja (\"" + alojamiento.getNombre() + "\") no existe.");
            return;
        }

        // Mostrar información alojamiento
        mostrarInformacion(alojamiento);

        // Pedimos confirmación al usuario
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Quieres darlo de baja? [S para borrar cualquier otra letra para cancelar]");
        String eleccion = String.valueOf(sc.next().charAt(0));
        if (!eleccion.equalsIgnoreCase("S")) {
            return;
        }

        // Borramos (se harán todas o ninguna)
        try {
            String tipoAlojamiento = "tipo desconocido";
            con.setAutoCommit(false);
            PreparedStatement ps;

            String nombreAlojamiento = alojamiento.getNombre();
            int codAlojamiento = getCodigoAlojamiento(nombreAlojamiento);

            ps = con.prepareStatement("DELETE FROM ALOJAMIENTO_ACTIVIDAD WHERE cod_alojamiento = ?");
            ps.setInt(1, codAlojamiento);
            ps.executeUpdate();

            if (alojamiento instanceof Hotel) {
                // Si el hotel es sede de algun hotel, ponemos a esos hoteles que su sede es null.
                ps = con.prepareStatement("UPDATE HOTEL SET hotelsede = null WHERE hotelsede = ?");
                ps.setInt(1, codAlojamiento);
                ps.executeUpdate();
                tipoAlojamiento = "hotel";

                if (alojamiento instanceof HotelSpa) {
                    tipoAlojamiento = "hotel con spa";

                    ps = con.prepareStatement("DELETE FROM HOTELSPA_SERVICIOS WHERE cod_spa = ?");
                    ps.setInt(1, codAlojamiento);
                    ps.executeUpdate();

                    ps = con.prepareStatement("DELETE FROM HOTELSPA WHERE cod_spa = ?");
                    ps.setInt(1, codAlojamiento);
                    ps.executeUpdate();
                }
                ps = con.prepareStatement("DELETE FROM HOTEL WHERE cod_hotel = ?");
                ps.setInt(1, codAlojamiento);
                ps.executeUpdate();
            } else if (alojamiento instanceof CasaRural) {
                tipoAlojamiento = "casa rural";
                ps = con.prepareStatement("DELETE FROM CASARURAL WHERE cod_casa = ?");
                ps.setInt(1, codAlojamiento);
                ps.executeUpdate();
            }

            ps = con.prepareStatement("DELETE FROM ALOJAMIENTO WHERE nombre = ?");
            ps.setString(1, nombreAlojamiento);
            ps.executeUpdate();

            System.out.println("Se ha borrado de la tabla " + tipoAlojamiento  + " el alojamiento " + nombreAlojamiento);
        } catch (SQLException e) {
            System.err.println("ERROR. Ocurrió un error inesperado al tratar de dar de baja un alojamiento. Se desharán los cambios.");
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {con.setAutoCommit(true);} catch (SQLException _) {}
        }
    }

    public void mostrarInformacion(Alojamiento alojamiento) {
        System.out.print("[------------- INFORMACIÓN -------------]");
        String formato = "%-30s, %-45s";
        StringBuilder stringBuilder = new StringBuilder();

        if (alojamiento instanceof Hotel hotel) {
            String tipoHotel = "HOTEL";
            if (alojamiento instanceof HotelSpa) {
                tipoHotel = "HOTEL SPA";
            }

            String nombreSede = getNombreSedeHotel(hotel.getNombre());
            stringBuilder.append(String.format(formato, "\n" + tipoHotel + ": " + hotel.getNombre(), "SEDE: " + nombreSede));
        } else if (alojamiento instanceof CasaRural casaRural) {
            String alquilerCompleto = casaRural.getAlquilerCompleta() == 'S' ? "Si" : "No";
            stringBuilder.append(String.format(formato, "\nCASA RURAL: " + casaRural.getNombre(), "ALQUILER COMPLETO: " + alquilerCompleto));
        } else {
            System.err.println("No hay información que mostrar de este alojamiento. No es un tipo válido");
            return;
        }

        stringBuilder.append("\nACTIVIDADES\n--------------------------------");
        List<String> listaActividades = getActivitiesByName(alojamiento.getNombre());
        for (String actividad : listaActividades) {
            stringBuilder.append("\n").append(actividad);
        }
        stringBuilder.append("\n---------------------------------------------------");
        stringBuilder.append("\n").append(listaActividades.size()).append(" actividades");
        System.out.println(stringBuilder);
    }

    public String getNombreSedeHotel(String nombreHotel) {
        try {
            CallableStatement cs = con.prepareCall("{call prNombreSedeHotel(?, ?)}");
            cs.setString(1, nombreHotel);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            String nombreSede = cs.getString(2);
            return nombreSede == null ? "SIN SEDE" : nombreSede;
        } catch (SQLException e) {
            System.err.println("ERROR. Ocurrió un error al tratar de obtener el nombre de la sede de un hotel.");
            throw new RuntimeException(e);
        }
    }

    public List<String> getActivitiesByName(String nombreAlojamiento) {
        List<String> listaActividades = new ArrayList<>();
        try {
            CallableStatement cs = con.prepareCall("{call prActividades(?)}");
            cs.setString(1, nombreAlojamiento);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                listaActividades.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.err.println("ERROR. Ocurrió un error al tratar de obtener las actividades de un alojamiento.");
            throw new RuntimeException(e);
        }
        return listaActividades;
    }
    // endregion

    // region EJERCICIO 3
    public void actualizarPrecios(String rutaArchivo) {
        try (FileReader reader = new FileReader(rutaArchivo)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray arrayActualizaciones = jsonObject.getAsJsonArray("Actualizaciones");

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE ALOJAMIENTO SET precio_habitacion = ? WHERE nombre = ?");
                for (JsonElement actualizacion : arrayActualizaciones) {
                    JsonObject objetoActualizacion = actualizacion.getAsJsonObject();
                    String nombre = objetoActualizacion.get("nombre").getAsString();

                    // Mostrar info
                    if (!existeAlojamiento(nombre)) {
                        System.err.println("Error: El alojamiento " + nombre + " no existe en la base de datos");
                        continue;
                    }

                    double nuevoPrecioHabitacion = objetoActualizacion.get("precio_habitacion").getAsDouble();
                    int codigoAlojamiento = getCodigoAlojamiento(nombre);
                    System.out.println("Código:" + codigoAlojamiento + " Alojamiento: " + nombre + "  Precio antiguo: " + getPrecioHabitacion(codigoAlojamiento) + "  Precio nuevo: " + nuevoPrecioHabitacion);

                    // Añadimos un nuevo lote
                    ps.setDouble(1, nuevoPrecioHabitacion);
                    ps.setString(2, nombre);
                    ps.addBatch();
                }

                // Actualizamos
                ps.executeBatch();
            } catch (SQLException e) {
                System.err.println("ERROR. Ocurrió un error SQL al tratar de modificar los datos a partir del archivo.");
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.err.println("ERROR. Ocurrió un error al tratar de ejecutar acciones con el archivo especificado.");
            throw new RuntimeException(e);
        }
    }

    private Double getPrecioHabitacion(int codigoAlojamiento) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT precio_habitacion FROM ALOJAMIENTO WHERE codigo = ?");
            ps.setInt(1, codigoAlojamiento);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            rs.next();
            return rs.getDouble(1);
        } catch (SQLException e) {
            System.err.println("ERROR. Ocurrió un error al tratar de obtener el precio de una habitación.");
            throw new RuntimeException(e);
        }
    }
    // endregion
}

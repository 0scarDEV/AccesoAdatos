package comunes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Operaciones {
    protected Connection con;

    public Operaciones(Connection con) {
        this.con = con;
    }

    public Connection getConnection() {
        return con;
    }

    public boolean borrarTablasSiExisten(String[] nombresTablas) throws SQLException {
        boolean tablasEliminadas = false;
        try {
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();

            for (String tabla : nombresTablas) {
                if (tablaExiste(tabla)) {
                    stmt.addBatch("DROP TABLE " + tabla);
                }
            }

            stmt.executeBatch();
            con.commit();
            System.out.println("Tablas existentes eliminadas correctamente.");
            tablasEliminadas = true;
        } catch (SQLException e) {
            con.rollback();
            System.err.println("Error al eliminar tablas: " + e.getMessage());
        } finally {
            con.setAutoCommit(true);
        }

        return tablasEliminadas;
    }

    private boolean tablaExiste(String nombreTabla) throws SQLException {
        ResultSet rs = con.getMetaData().getTables(null, null, nombreTabla, null);
        return rs.next();
    }

    public void crearArchivoJson(JsonObject datosJson, String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(datosJson);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

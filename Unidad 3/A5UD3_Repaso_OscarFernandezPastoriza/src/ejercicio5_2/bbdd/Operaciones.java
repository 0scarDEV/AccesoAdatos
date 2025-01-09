package ejercicio5_2.bbdd;

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
}

package ejercicio5_4y5_5;

import comunes.Operaciones;

import java.sql.*;

public class OperacionesSQLServerProyecto extends Operaciones {
    public OperacionesSQLServerProyecto(Connection con) {
        super(con);
    }

    public void addProyectos(Proyecto[] proyectos) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO PROXECTO (num_proxecto, nome_proxecto, lugar, num_departamento_pertenece) VALUES (?, ?, ?, ?)");

            for (Proyecto proyecto : proyectos) {
                ps.setInt(1, proyecto.getNumProxecto());
                ps.setString(2, proyecto.getNomeProxecto());
                ps.setString(3, proyecto.getLugar());
                ps.setInt(4, proyecto.getNumDepartamentoPertenece());

                ps.addBatch();
            }

            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarProyecto(int numProxecto) {
        String nomeProxecto;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT Nome_proxecto FROM PROXECTO WHERE Num_proxecto = ?");
            ps.setInt(1, numProxecto);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.err.println("No existe un proyecto de número " + numProxecto);
                return;
            }
            nomeProxecto = rs.getString("Nome_proxecto");

            DatabaseMetaData metaData = con.getMetaData();
            ResultSet importedKeys = metaData.getImportedKeys(null, null, "EMPREGADO_PROXECTO");

            int deleteRule = -1;
            if (importedKeys.next()) {
                deleteRule = importedKeys.getInt(11);
            }

            if (deleteRule == DatabaseMetaData.importedKeyNoAction) {
                ps = con.prepareStatement("DELETE FROM EMPREGADO_PROXECTO WHERE Num_proxecto = ?");
                ps.setInt(1, numProxecto);
                ps.executeUpdate();
                System.out.println("Empregados pertenecentes ao proxecto " + numProxecto + " (" + nomeProxecto + ") eliminado correctamente.");
            }

            ps = con.prepareStatement("DELETE FROM PROXECTO WHERE Num_proxecto = ?");
            ps.setInt(1, numProxecto);
            ps.execute();
            System.out.println("Proxecto " + numProxecto + " (" + nomeProxecto + ") eliminado correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
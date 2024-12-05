package Comunes;

import E2_3y4.Proxecto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperacionesMySQL extends Operaciones {
    public OperacionesMySQL(Conexion con) {
        super(con.getConnection());
    }

    public boolean cambiarDepartamentoProxecto(String nomDepartamento, String nomProxecto) {
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(
                "UPDATE PROXECTO " +
                        "SET PROXECTO.Num_departamento_pertenece = (" +
                            "SELECT Num_departamento FROM DEPARTAMENTO WHERE Nome_departamento = ?" +
                        ") " +
                        "WHERE Nome_proxecto = ?"
            );

            ps.setString(1, nomDepartamento);
            ps.setString(2, nomProxecto);

            ps.execute();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean inserirProxecto(Proxecto proxecto) {
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("INSERT INTO PROXECTO VALUES (?, ?, ?, ?)");
            ps.setInt(1, proxecto.getNumProxecto());
            ps.setString(2, proxecto.getNomeProxecto());
            ps.setString(3, proxecto.getLugar());
            ps.setInt(4, proxecto.getNumDepartamentoPertenece());

            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean eliminarProxecto(int numProxecto) {
        PreparedStatement ps;
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement("DELETE FROM EMPREGADO_PROXECTO WHERE Num_proxecto = ?");
            ps.setInt(1, numProxecto);
            ps.execute();

            ps = con.prepareStatement("DELETE FROM PROXECTO WHERE Num_proxecto = ?");
            ps.setInt(1, numProxecto);

            ps.execute();
            con.commit();
            return true;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}

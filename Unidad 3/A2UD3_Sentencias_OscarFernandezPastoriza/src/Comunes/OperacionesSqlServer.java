package Comunes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class OperacionesSqlServer extends Operaciones {

    public OperacionesSqlServer(Conexion con) {
        super(con.getConnection());
    }

    public boolean aumentarSalario(String departamento, double aumento) {
        try {
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            String consulta =
                "UPDATE EMPREGADO " +
                "SET Salario = Salario + " + aumento + " " +
                "WHERE Num_departamento_pertenece = (" +
                    "SELECT Num_departamento " +
                    "FROM DEPARTAMENTO D " +
                    "WHERE D.Nome_departamento = '" + departamento + "'" +
                ");"
            ;

            st.executeUpdate(consulta);

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

    public boolean insertarDepartamento(int numDpto, String nomDpto, String nifDirector) {
        try {
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            LocalDate fechaSistema = LocalDate.now();
            String sql =
                "INSERT INTO departamento " +
                "VALUES (" + numDpto + ", '" + nomDpto + "', '" + nifDirector + "','" + fechaSistema + "' )"
            ;

            st.executeUpdate(sql);

            con.commit();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteEmpregadoProxecto(String nssEmpregado, int numProxecto) {
        try {
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            String sql =
                    "DELETE FROM EMPREGADO_PROXECTO " +
                            "WHERE NSS_EMPREGADO = '" + nssEmpregado + "' AND NUM_PROXECTO = " + numProxecto
                    ;

            st.executeUpdate(sql);

            con.commit();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarEmpleadosPorLocalidad(String localidad) {
        ResultSet rs = consultarEmpleadosPorLocalidad(localidad);

        try {
            while (rs.next()) {
                System.out.println(
                    "Nome: " + rs.getString("Nome") + "\n" +
                    "Apelidos: " + rs.getString("Apelidos") + "\n" +
                    "Localidade: " + rs.getString("Localidade") + "\n" +
                    "Salario: " + rs.getDouble("Salario") + "\n" +
                    "Data Nacemento: " + rs.getDate("Data_Nacemento") + "\n" +
                    "Nome Supervisor: " + rs.getString("NOME SUPERVISOR") + "\n" +
                    "Nome Departamento: " + rs.getString("Nome_departamento") + "\n"
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ResultSet consultarEmpleadosPorLocalidad(String localidad) {
        ResultSet rs;

        try {
            Statement st = con.createStatement();

            String sql =
                "SELECT E.Nome, E.Apelido_1 + ' ' + E.Apelido_2 as Apelidos, E.Localidade, E.Salario, E.Data_Nacemento, " +
                        "S.Nome + ' ' + S.Apelido_1 + ' ' + S.Apelido_2 as [NOME SUPERVISOR]," +
                    " D.Nome_departamento " +
                "FROM EMPREGADO E " +
                    "INNER JOIN EMPREGADO S ON E.NSS_Supervisa = S.NSS " +
                    "INNER JOIN DEPARTAMENTO D ON E.Num_departamento_pertenece = D.Num_departamento " +
                "WHERE E.Localidade = '" + localidad + "'"
            ;

            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rs;
    }
}

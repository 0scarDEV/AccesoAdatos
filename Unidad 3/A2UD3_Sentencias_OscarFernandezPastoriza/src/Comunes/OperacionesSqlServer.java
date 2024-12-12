package Comunes;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OperacionesSqlServer extends Operaciones {

    public OperacionesSqlServer(Conexion con) {
        super(con.getConnection());
    }

    // region UD2A2 - Ej1y2

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
                            ");";

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
                            "VALUES (" + numDpto + ", '" + nomDpto + "', '" + nifDirector + "','" + fechaSistema + "' )";

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
                            "WHERE NSS_EMPREGADO = '" + nssEmpregado + "' AND NUM_PROXECTO = " + numProxecto;

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
                            "WHERE E.Localidade = '" + localidad + "'";

            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rs;
    }
    // endregion

    // region UD2A2 - Ej5a
    public void actualizarDireccionEmpregado(String nss, String rua, int num, String piso, String codPostal, String localidade) {
        crearPr_CambioDomicilio();

        CallableStatement cs;
        try {
            cs = con.prepareCall("{call pr_cambioDomicilio(?, ?, ?, ?, ?, ?)}");

            cs.setString(1, nss);
            cs.setString(2, rua);
            cs.setInt(3, num);
            cs.setString(4, piso);
            cs.setString(5, codPostal);
            cs.setString(6, localidade);

            cs.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void crearPr_CambioDomicilio() {
        Statement st;
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Si existe, borramos
        try {
            st.execute(
                    "IF EXISTS (SELECT * FROM sys.procedures WHERE name = 'pr_cambioDomicilio') " +
                            "DROP PROCEDURE pr_cambioDomicilio"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Creamos el procedimiento
        try {
            st.execute(
                    "CREATE PROCEDURE pr_cambioDomicilio (" +
                            "    @nssEmpregado varchar(15)," +
                            "    @rua varchar(30)," +
                            "    @num int," +
                            "    @piso varchar(4)," +
                            "    @codPostal char(5)," +
                            "    @localidade varchar(25)" +
                            ") AS BEGIN" +
                            "    UPDATE EMPREGADO" +
                            "    SET Rua = @rua," +
                            "        Numero_rua = @num," +
                            "        Piso = @piso," +
                            "        CP = @codPostal," +
                            "        Localidade = @localidade" +
                            "    WHERE NSS = @nssEmpregado;" +
                            "END"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // endregion

    // region UD2A2 - Ej5b
    public Proxecto getProxecto(int numProxecto) {
        Proxecto p;
        crearPr_DatosProxecto();

        CallableStatement cs;
        try {
            cs = con.prepareCall("{call pr_datosProxecto(?, ?, ?, ?)}");

            cs.setInt(1, numProxecto);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.INTEGER);

            cs.execute();

            p = new Proxecto(numProxecto, cs.getString(2), cs.getString(3), cs.getInt(4));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return p;
    }

    private void crearPr_DatosProxecto() {
        Statement st;
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Si existe, borramos
        try {
            st.execute(
                    "IF EXISTS (SELECT * FROM sys.procedures WHERE name = 'pr_datosProxecto') " +
                            "DROP PROCEDURE pr_datosProxecto;"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Creamos el procedimiento
        try {
            st.execute(
                    "CREATE PROCEDURE pr_datosProxecto (" +
                            "    @numProxecto int," +
                            "    @nome varchar(25) OUTPUT," +
                            "    @lugar varchar(25) OUTPUT," +
                            "    @numDepartamento int OUTPUT" +
                            ") AS BEGIN" +
                            "   SELECT " +
                            "   @nome = Nome_proxecto, " +
                            "   @lugar = Lugar, " +
                            "   @numDepartamento = Num_departamento_pertenece " +
                            "FROM PROXECTO " +
                            "WHERE Num_proxecto = @numProxecto " +
                            "END"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // endregion

    // region UD2A2 - Ej5c
    public void mostrarDepartamentosConMas_X_Proxectos(int i) {
        ArrayList<Departamento> departamentos = new ArrayList<>();

        crearPr_DepartControlaProxec();

        CallableStatement cs;
        try {
            cs = con.prepareCall("{call pr_departControlaProxec(?)}");
            cs.setInt(1, i);

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                departamentos.add(new Departamento(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Departamento d : departamentos) {
            System.out.println(d);
        }
    }

    private void crearPr_DepartControlaProxec() {
        Statement st;

        try {
            st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Si existe, borramos
        try {
            st.execute(
                    "IF EXISTS (SELECT * FROM sys.procedures WHERE name = 'pr_departControlaProxec') " +
                            "DROP PROCEDURE pr_departControlaProxec"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Creamos el procedimiento
        try {
            st.execute(
            "CREATE PROCEDURE pr_departControlaProxec (" +
                "    @numProxectosMin int " +
                ") AS BEGIN" +
                "   SELECT " +
                "       Num_departamento, " +
                "       Nome_departamento, " +
                "       NSS_dirige, " +
                "       Data_direccion " +
                "   FROM DEPARTAMENTO " +
                "   WHERE Num_departamento IN (" +
                "       SELECT Num_departamento_pertenece " +
                "       FROM PROXECTO " +
                "       GROUP BY Num_departamento_pertenece " +
                "       HAVING COUNT(*) >= @numProxectosMin" +
                "   )" +
                "END"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // endregion

    // region UD2A2 - Ej5d
    public void mostrarNumEmpregadosPorDepartamento(String nomDepartamento) {
        crearFn_nEmpDepart();

        CallableStatement cs;
        try {
            cs = con.prepareCall("{? = call fn_nEmpDepart(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, nomDepartamento);

            cs.execute();

            System.out.println("Número de empregados no departamento " + nomDepartamento + ": " + cs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void crearFn_nEmpDepart () {
        Statement st;

        try {
            st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Creamos la funcion
        try {
            st.execute(
            "CREATE FUNCTION fn_nEmpDepart ( " +
                    "@nomDepartamento varchar(25) " +
                ") RETURNS INT AS BEGIN " +
                    "RETURN (" +
                        "SELECT COUNT(*) FROM EMPREGADO " +
                        "WHERE Num_departamento_pertenece = (" +
                            "SELECT Num_departamento " +
                            "FROM DEPARTAMENTO " +
                            "WHERE Nome_departamento = @nomDepartamento" +
                        ")" +
                    ");" +
                "END"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // endregion

    // region UD2A2 - Ej6 - Útiles
    private boolean existeNumDepartamento(int numDepartamento) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DEPARTAMENTO WHERE Num_departamento = " + numDepartamento);

            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean existeNumDepartamento(Proxecto proxecto) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DEPARTAMENTO WHERE Num_departamento = " + proxecto.getNumDepartamentoPertenece());

            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean existeNomProxecto(Proxecto proxecto) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PROXECTO WHERE Nome_proxecto = '" + proxecto.getNomeProxecto() + "'");

            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // endregion

    // region UD2A2 - Ej6a
    public void visualizarTiposResultSet() {
        try {
            DatabaseMetaData dbmd = con.getMetaData();
            ResultSet rs = dbmd.getTypeInfo();

            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("TYPE_NAME"));
                System.out.println("Tipo: " + rs.getString("DATA_TYPE"));
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // endregion

    // region UD2A2 - Ej6b
    public boolean insertarNuevoProxecto(Proxecto proxecto) {
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("SELECT * FROM PROXECTO");

            if (!existeNomProxecto(proxecto) && existeNumDepartamento(proxecto)) {
                rs.beforeFirst();   // Nos ponemos antes de la primera fila
                rs.moveToInsertRow();   // Nos ponemos en la siguiente fila
                // Cambiamos cosas en memoria
                rs.updateInt("Num_proxecto", proxecto.getNumProxecto());
                rs.updateString("Nome_proxecto", proxecto.getNomeProxecto());
                rs.updateString("Lugar", proxecto.getLugar());
                rs.updateInt("Num_departamento_pertenece", proxecto.getNumDepartamentoPertenece());
                rs.insertRow(); // Lo cambiamos en la BBDD
                rs.moveToCurrentRow();
            } else {
                return false;
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // endregion

    // region UD2A2 - Ej6c
    public boolean aumentarSalario(int numDepartamento, int cantidad) {
        boolean hayAumento = false;
        try {
            if (existeNumDepartamento(numDepartamento)) {
                String sql = "SELECT Salario FROM EMPREGADO WHERE Num_departamento_pertenece = ?";

                PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ps.setInt(1, numDepartamento);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    rs.updateDouble("salario", rs.getDouble("Salario") + cantidad);
                    rs.updateRow();
                }
                hayAumento = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hayAumento;
    }


    // region UD2A2 - Ej6d
    public void mostrarPersonalizado(int numProxectos) {
        String sql = "SELECT NSS, NOME + ' ' + Apelido_1 + ' ' + Apelido_2 as NomeCompleto, Localidade, Salario " +
                "FROM EMPREGADO WHERE NSS IN (" +
                "   SELECT NSS_EMPREGADO FROM EMPREGADO_PROXECTO GROUP BY NSS_EMPREGADO HAVING COUNT(*) > ?" +
                ")";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, numProxectos);
            ResultSet rs = ps.executeQuery();

            rs.beforeFirst();
            rs.first();
            System.out.println("Primera fila");
            System.out.println("NSS: " + rs.getString("NSS") + " Nome completo: " + rs.getString("NomeCompleto") + " Localidade: " + rs.getString("Localidade") + " Salario " + rs.getDouble("Salario"));

            rs.last();
            System.out.println("Última fila");
            System.out.println("NSS: " + rs.getString("NSS") + " Nome completo: " + rs.getString("NomeCompleto") + " Localidade: " + rs.getString("Localidade") + " Salario " + rs.getDouble("Salario"));

            rs.relative(-2);
            System.out.println("Antepenúltima fila");
            System.out.println("NSS: " + rs.getString("NSS") + " Nome completo: " + rs.getString("NomeCompleto") + " Localidade: " + rs.getString("Localidade") + " Salario " + rs.getDouble("Salario"));

            rs.last();
            System.out.println("Recorrido de última a primera fila");
            do {
                System.out.println("\t- NSS: " + rs.getString("NSS") + " Nome completo: " + rs.getString("NomeCompleto") + " Localidade: " + rs.getString("Localidade") + " Salario " + rs.getDouble("Salario"));
            } while (rs.previous());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
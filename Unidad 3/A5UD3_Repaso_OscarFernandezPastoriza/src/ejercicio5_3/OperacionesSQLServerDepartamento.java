package ejercicio5_3;

import comunes.Operaciones;

import java.sql.*;

public class OperacionesSQLServerDepartamento extends Operaciones {
    static final String FORMATO_STRING_EMPREGADO = "%-30s%-10s%-40s%-20s%-10s%-7s%-20s";
    static final String FORMATO_STRING_PROXECTO = "%-20s%-20s%-20s";

    public OperacionesSQLServerDepartamento(Connection con) {
        super(con);
    }

    public void eliminarDepartamento(String nombreDepartamento, String nombreDepartamentoReasignar) {
        crearProcedimientoNumDepartamento();
        mostrarDatosDepartamento(nombreDepartamento);
        mostraryAsignarDatosEmpleadosDepartamento(nombreDepartamento, nombreDepartamentoReasignar);
        System.out.println("Los Empregados del Departamento " + nombreDepartamento + " han sido reasignados al departamento " + nombreDepartamentoReasignar);

        mostrarYAsignarDatosProyectoDepartamento(nombreDepartamento, nombreDepartamentoReasignar);
        System.out.println("Los Proyectos del Departamento " + nombreDepartamento + " han sido reasignados al departamento " + nombreDepartamentoReasignar);

        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM DEPARTAMENTO WHERE Nome_departamento = ?");
            ps.setString(1, nombreDepartamento);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Departamento " + nombreDepartamento + " eliminado correctamente.");
    }

    private void crearProcedimientoNumDepartamento() {
        try {
            Statement st = con.createStatement();
            st.execute("DROP PROCEDURE IF EXISTS prObtenerNumDepartamento");
            st.execute(
            "CREATE PROCEDURE prObtenerNumDepartamento(" +
                    "@nomDepartamento varchar(25)," +
                    "@numDepartamento int OUTPUT " +
                    ") as BEGIN " +
                        "SELECT @numDepartamento = Num_departamento " +
                        "FROM Departamento " +
                        "WHERE Nome_departamento = @nomDepartamento " +
                    "END");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getNumDepartamentoByNome(String nomeDepartamento) {
        try {
            CallableStatement cs = con.prepareCall("{call prObtenerNumDepartamento(?, ?)}");
            cs.setString(1, nomeDepartamento);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            return cs.getInt(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mostrarYAsignarDatosProyectoDepartamento(String nombreDepartamento, String nombreDepartamentoReasignar) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM PROXECTO WHERE Num_departamento_pertenece = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, getNumDepartamentoByNome(nombreDepartamento));
            ResultSet proyectosDepartamento = ps.executeQuery();

            if (!proyectosDepartamento.next()) {
                System.err.println("No hay proyectos en el departamento " + nombreDepartamento);
                return;
            }
            System.out.println("Proyectos pertenecientes al departamento " + nombreDepartamento + ":");
            System.out.printf((FORMATO_STRING_PROXECTO) + "%n", "Num proxecto", "Nome proxecto", "Lugar");
            do {
                String datosProxecto = String.format(FORMATO_STRING_PROXECTO, proyectosDepartamento.getInt("Num_proxecto"), proyectosDepartamento.getString("Nome_proxecto"), proyectosDepartamento.getString("Lugar"));
                System.out.println(datosProxecto);

                proyectosDepartamento.updateInt("Num_departamento_pertenece", getNumDepartamentoByNome(nombreDepartamentoReasignar));
                proyectosDepartamento.updateRow();
            } while (proyectosDepartamento.next());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mostraryAsignarDatosEmpleadosDepartamento(String nombreDepartamento, String nombreDepartamentoReasignar) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM EMPREGADO WHERE Num_departamento_pertenece = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, getNumDepartamentoByNome(nombreDepartamento));
            ResultSet empleadosDepartamento = ps.executeQuery();

            if (!empleadosDepartamento.next()) {
                System.err.println("No hay empleados en el departamento " + nombreDepartamento);
                return;
            }
            System.out.println("Empleados pertenecientes al departamento " + nombreDepartamento + ":");
            System.out.printf((FORMATO_STRING_EMPREGADO) + "%n", "Nome e apelidos", "NSS", "Direccion", "Data Nacemento", "Salario", "Sexo", "NSS Supervisa");
            do {
                String datosEmpregado = String.format(FORMATO_STRING_EMPREGADO,
                        empleadosDepartamento.getString("Nome") + " " + empleadosDepartamento.getString("Apelido_1") + " " + empleadosDepartamento.getString("Apelido_2"),
                        empleadosDepartamento.getString("NSS"),
                        empleadosDepartamento.getString("Rua") + ", " + empleadosDepartamento.getString("Numero_rua") + ", " + empleadosDepartamento.getString("Piso") + " (" + empleadosDepartamento.getString("CP") + ", " + empleadosDepartamento.getString("Localidade") + ")",
                        empleadosDepartamento.getString("Data_nacemento"),
                        empleadosDepartamento.getDouble("Salario"),
                        empleadosDepartamento.getString("Sexo"),
                        empleadosDepartamento.getString("NSS_supervisa"));
                System.out.println(datosEmpregado);

                empleadosDepartamento.updateInt("Num_departamento_pertenece", getNumDepartamentoByNome(nombreDepartamentoReasignar));
                empleadosDepartamento.updateRow();
            } while (empleadosDepartamento.next());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mostrarDatosDepartamento(String nombreDepartamento) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM DEPARTAMENTO WHERE Nome_departamento = ?");
            ps.setString(1, nombreDepartamento);
            ResultSet datosDepartamento = ps.executeQuery();

            if (datosDepartamento.next()) {
                System.out.println("Datos del departamento " + nombreDepartamento + ":");
                System.out.println("Código: " + datosDepartamento.getInt("Num_departamento"));
                System.out.println("Nombre: " + datosDepartamento.getString("Nome_departamento"));
                System.out.println("NSS Dirige: " + datosDepartamento.getString("NSS_dirige"));
                System.out.println("Data direccion: " + datosDepartamento.getString("Data_direccion"));
            } else {
                System.err.println("No se ha encontrado el departamento " + nombreDepartamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
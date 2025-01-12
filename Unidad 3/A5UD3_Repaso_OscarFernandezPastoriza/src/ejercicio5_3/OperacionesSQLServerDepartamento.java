package ejercicio5_3;

import comunes.Operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperacionesSQLServerDepartamento extends Operaciones {
    static final String FORMATO_STRING_EMPREGADO = "%-30s%-10s%-40s%-20s%-10s%-7s%-20s";
    static final String FORMATO_STRING_PROXECTO = "%-20s%-20s%-20s";

    public OperacionesSQLServerDepartamento(Connection con) {
        super(con);
    }

    public void eliminarDepartamento(String nombreDepartamento, String nombreDepartamentoReasignar) {
        mostrarDatosDepartamento(nombreDepartamento);
        mostrarDatosEmpleadosDepartamento(nombreDepartamento);
        mostrarDatosProyectosDepartamento(nombreDepartamento);


    }

    private void mostrarDatosProyectosDepartamento(String nombreDepartamento) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM PROXECTO WHERE Num_departamento_pertenece IN (SELECT Num_departamento FROM DEPARTAMENTO WHERE Nome_departamento = ?)");
            ps.setString(1, nombreDepartamento);
            ResultSet proyectosDepartamento = ps.executeQuery();

            System.out.println("Proyectos pertenecientes al departamento " + nombreDepartamento + ":");
            System.out.printf((FORMATO_STRING_PROXECTO) + "%n", "Num proxecto", "Nome proxecto", "Lugar");
            while (proyectosDepartamento.next()) {
                String datosProxecto = String.format(FORMATO_STRING_PROXECTO, proyectosDepartamento.getInt("Num_proxecto"), proyectosDepartamento.getString("Nome_proxecto"), proyectosDepartamento.getString("Lugar"));
                System.out.print(datosProxecto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mostrarDatosEmpleadosDepartamento(String nombreDepartamento) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM EMPREGADO WHERE Num_departamento_pertenece IN (SELECT Num_departamento FROM DEPARTAMENTO WHERE Nome_departamento = ?)");
            ps.setString(1, nombreDepartamento);
            ResultSet empleadosDepartamento = ps.executeQuery();

            System.out.println("Empleados pertenecientes al departamento " + nombreDepartamento + ":");
            System.out.printf((FORMATO_STRING_EMPREGADO) + "%n", "Nome e apelidos", "NSS", "Direccion", "Data Nacemento", "Salario", "Sexo", "NSS Supervisa");
            while (empleadosDepartamento.next()) {
                String datosEmpregado = String.format(FORMATO_STRING_EMPREGADO,
                        empleadosDepartamento.getString("Nome") + " " + empleadosDepartamento.getString("Apelido_1") + " " + empleadosDepartamento.getString("Apelido_2"),
                        empleadosDepartamento.getString("NSS"),
                        empleadosDepartamento.getString("Rua") + ", " + empleadosDepartamento.getString("Numero_rua") + ", " + empleadosDepartamento.getString("Piso") + " (" + empleadosDepartamento.getString("CP") + ", " + empleadosDepartamento.getString("Localidade") + ")",
                        empleadosDepartamento.getString("Data_nacemento"),
                        empleadosDepartamento.getDouble("Salario"),
                        empleadosDepartamento.getString("Sexo"),
                        empleadosDepartamento.getString("NSS_supervisa"));
                System.out.println(datosEmpregado);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mostrarDatosDepartamento(String nombreDepartamento) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM DEPARTAMENTO WHERE Nome_departamento = ?");
            ps.setString(1, nombreDepartamento);
            ResultSet datosDepartamento = ps.executeQuery();

            System.out.println("Datos del departamento " + nombreDepartamento + ":");

            if (datosDepartamento.next()) {
                System.out.println("Código: " + datosDepartamento.getInt("Num_departamento"));
                System.out.println("Nombre: " + datosDepartamento.getString("Nome_departamento"));
                System.out.println("NSS Dirige: " + datosDepartamento.getString("NSS_dirige"));
                System.out.println("Data direccion: " + datosDepartamento.getString("Data_direccion"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

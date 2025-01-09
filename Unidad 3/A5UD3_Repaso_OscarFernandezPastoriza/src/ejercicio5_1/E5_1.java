package ejercicio5_1;

import java.sql.*;

public class E5_1 {
    /*
        Se quiere realizar un programa para que desde la línea de comando se pueda ejecutar cualquier instrucción sql en SQLSERVER. Recibirá cuatro argumentos: primero base de datos, el segundo el nombre de usuario, el tercero la contraseña y el cuarto un string que representa la sentencia o sentencias que se quieren ejecutar separadas por punto y coma.
        Ejemplo: c>Ejecutar Empresa_Clase sa abc123. Select * from proyecto;delete from departamento where num_departamento=44 Gestiona todos los posibles errores que se puedan producir. Nota: en un resultset cuando no se conoce el tipo de datos del campo, se puede utilizar el genérico getObject
    */

    public static void main(String[] args) {
        executeGeneralInstruction(args[0], args[1], args[2], args[3]);
    }

    public static void executeGeneralInstruction(String bbdd, String username, String password, String sentences) {
        String[] sentencias = sentences.split(";");
        ResultSetMetaData metaData;
        PreparedStatement ps;
        StringBuilder row;
        Connection con;
        ResultSet rs;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost\\MSQLSERVER:50043;database=" + bbdd + ";trustServerCertificate=true", username, password);
            for (String sentencia : sentencias) {
                ps = con.prepareStatement(sentencia);
                ps.execute();

                metaData = ps.getMetaData();
                rs = ps.getResultSet();

                if (rs != null) {
                    while (rs.next()) {
                        row = new StringBuilder();
                        for (int i = 1; i <= metaData.getColumnCount(); i++) {
                            row.append(rs.getObject(i)).append("\t");
                        }
                        System.out.println(row);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

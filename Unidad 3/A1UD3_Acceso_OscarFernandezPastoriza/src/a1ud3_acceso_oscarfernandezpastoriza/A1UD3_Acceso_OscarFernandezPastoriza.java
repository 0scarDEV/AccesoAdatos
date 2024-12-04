package a1ud3_acceso_oscarfernandezpastoriza;

import java.sql.*;
import java.util.Properties;

public class A1UD3_Acceso_OscarFernandezPastoriza {
    public static void main(String[] args) {
        Connection conexion;
        try {
            conexion = DriverManager.getConnection("jdbc:sqlserver://localhost\\MSQLSERVER:50043;trustServerCertificate=true", "sa", "abc123.");    // SQLServer
            //conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdempresa", "root", "abc123.");    // MySQL
            //conexion = org.sqlite.JDBC.createConnection("jdbc:sqlite:C:/Users/Usuario/Documents/SQLite/empresa.db", new Properties());    // SQLite
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Statement s = conexion.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM empregado");
            while (resultSet.next()) {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
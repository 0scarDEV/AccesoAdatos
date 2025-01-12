package comunes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    public enum SGDB {SQLServer, MySQL, SQLite}
    Connection con;

    public Conexion(String url, String user, String password) {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Conexion(SGDB sgdb) {
        switch (sgdb) {
            case SGDB.SQLServer -> {
                try {
                    con = DriverManager.getConnection("jdbc:sqlserver://localhost\\MSQLSERVER:50043;database=BDEmpresa;trustServerCertificate=true", "sa", "abc123.");  // SQL Server
                } catch (
                        SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case SGDB.MySQL -> {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdempresa", "root", "abc123.");    // MySQL
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case SGDB.SQLite -> {
                try {
                    con = org.sqlite.JDBC.createConnection("jdbc:sqlite:C:/Users/Usuario/Documents/SQLite/empresa.db", new Properties());   // SQLite
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Connection getConnection() {
        return con;
    }
}

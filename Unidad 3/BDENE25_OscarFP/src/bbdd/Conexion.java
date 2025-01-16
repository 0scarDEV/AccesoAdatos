package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* Óscar Fernández Pastoriza - 52862191D */
public class Conexion {
    Connection con;

    public Conexion(String url, String user, String password) {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return con;
    }
}

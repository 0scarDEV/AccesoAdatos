package Comunes;

import java.sql.Connection;

public class Operaciones {
    protected Connection con;

    public Operaciones(Connection con) {
        this.con = con;
    }

    public Connection getConnection() {
        return con;
    }
}

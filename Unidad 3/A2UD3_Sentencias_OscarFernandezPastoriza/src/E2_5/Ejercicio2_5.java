package E2_5;

import Comunes.Conexion;
import Comunes.OperacionesSqlServer;

public class Ejercicio2_5 {
    static OperacionesSqlServer db = new OperacionesSqlServer(new Conexion(Conexion.SGDB.SQLServer));

    public static void main(String[] args) {
        db.actualizarDireccionEmpregado("", "", "");
    }
}
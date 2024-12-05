package E2_1y2;

import Comunes.Conexion;
import Comunes.OperacionesSqlServer;

public class Ejercicio2_2 {
    public static void main(String[] args) {
        Conexion conexion = new Conexion(Conexion.SGDB.SQLServer);
        OperacionesSqlServer bd = new OperacionesSqlServer(conexion);
        bd.mostrarEmpleadosPorLocalidad("Vigo");
    }
}

package E2_1y2;

import Comunes.Conexion;
import Comunes.OperacionesBBDD;

public class Ejercicio2_2 {
    public static void main(String[] args) {
        Conexion conexion = new Conexion(Conexion.SGDB.SQLServer);
        OperacionesBBDD bd = new OperacionesBBDD(conexion);
        bd.mostrarEmpleadosPorLocalidad("Vigo");
    }
}

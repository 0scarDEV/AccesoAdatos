package E2_3y4;

import Comunes.Conexion;
import Comunes.OperacionesMySQL;

import java.util.ArrayList;

public class Ejercicio2_4 {
    static Conexion conexion;

    public static void main(String[] args) {
        conexion = new Conexion(Conexion.SGDB.MySQL);
        OperacionesMySQL bd = new OperacionesMySQL(conexion);

        ArrayList<Proxecto> listaProxectos = bd.getAllProxectos("INFORMÁTICA");

        for (Proxecto proxecto : listaProxectos) {
            System.out.println(proxecto);
        }
    }
}

package E2_3y4;

import Comunes.Conexion;
import Comunes.OperacionesMySQL;

public class Ejercicio2_3 {
    static Conexion conexion;

    public static void main(String[] args) {
        conexion = new Conexion(Conexion.SGDB.MySQL);
        OperacionesMySQL bd = new OperacionesMySQL(conexion);
        int numProxecto = 11;


        if (bd.cambiarDepartamentoProxecto("INFORMÁTICA", "PROXECTO Y")) {
            System.out.println("Departamento cambiado correctamente");
        } else {
            System.out.println("Error al cambiar el departamento");
        }

        Proxecto p = new Proxecto(numProxecto, "Proxecto7", "Lugar7", 5);

        if (bd.inserirProxecto(p)) {
            System.out.println("Proxecto insertado correctamente");
        } else {
            System.out.println("Error al insertar el proxecto");
        }

        // Insertamos un empleado para comprobar que se elimina correctamente el proxecto
        bd.insertarEmpleadoProxecto("0010010", numProxecto);

        if (bd.eliminarProxecto(numProxecto)) {
            System.out.println("Proxecto eliminado correctamente");
        } else {
            System.out.println("Error al eliminar el proxecto");
        }
    }
}

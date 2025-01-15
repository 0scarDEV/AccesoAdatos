package ejercicios;

import comunes.Conexion;
import comunes.OperacionesSQLServerFamiliares;

public class E7_3 {
    public static void main(String[] args) {
        /* Inserta los familiares de los empleados que vengan de un json.
         * Si hay varios familiares de un empleado con el mismo nss de familiar, se inserta el primero queque se encuentre.
         * Se creará un json con los familiares errorneos que no se han podido insertar
         * El json de los familiares a insertar tiene la estructura mostrada */

        OperacionesSQLServerFamiliares opFamiliares = new OperacionesSQLServerFamiliares(new Conexion(Conexion.SGDB.SQLServer).getConnection());
        opFamiliares.insertarFamiliarJson("src/Familiares.json","src/familiares-post.json");
    }
}
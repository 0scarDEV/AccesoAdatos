package ejercicios;
import clases.Familiar;
import comunes.OperacionesSQLServerFamiliares;
import comunes.Conexion;
public class E7_2 {
    /* Crea un metodo que permita insertar un familiar a un empleado. El número tendrá que seguir la secuencialidad 1,2,3, etc para cada empleado.
         Visualiza informacion de los errores que puedan producirse, visualizando mensajes representativos.
         Solo puede estar registrado una vez un familiar para cada empleado. Para la comprobación se creará un procedimiento que se le pase el empleado y el nss del familiar y devuelva en un parámetro de tipo bit si existe o no ( 0 no existe, 1 existe).
         Se deberá obtener el siguiente número secuencial de forma automática. Para ello se creará y llamará a una función en SQL Server que calcule y devuelva el último número de familiar registrado para un empleado,
        Pon ejemplo de varias llamadas. */
    public static void main(String[] args) {
        OperacionesSQLServerFamiliares opFamiliares = new OperacionesSQLServerFamiliares(new Conexion(Conexion.SGDB.SQLServer).getConnection());

        Familiar familiar = new Familiar("6000006", "Juan", "Perez","Hermano", Familiar.Sexo.Hombre);
        opFamiliares.insertarFamiliar("0010010", familiar);
        familiar = new Familiar("6000007", "Maria", "Perez","Hermana", Familiar.Sexo.Mujer);
        opFamiliares.insertarFamiliar("0010010", familiar);
        familiar = new Familiar("6000008", "Pedro", "Perez","Hermano", Familiar.Sexo.Hombre);
        opFamiliares.insertarFamiliar("0110010", familiar);
    }
}

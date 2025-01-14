package ejercicio5_4y5_5;

import comunes.Conexion;

public class E5_5 {
    /* Crea un metodo que reciba un número de proyecto y lo elimine de la tabla Proyecto. Ten en cuenta la acción definida en la clave foránea cuando se elimina la clave principal:
        • Si la acción es NoAction, primero debes eliminar en la tabla Empleado_Proyecto los registros que hacen referencia al proyecto que se quiere eliminar.
        • Una vez hecho esto, puedes proceder a eliminar el proyecto de la tabla Proyecto.
    Ambas acciones deben realizarse dentro de una transacción, de forma que se ejecuten ambas operaciones o ninguna en caso de que ocurra un error. */
    public static void main(String[] args) {
        OperacionesSQLServerProyecto opProyecto = new OperacionesSQLServerProyecto(new Conexion(Conexion.SGDB.SQLServer).getConnection());
        opProyecto.eliminarProyecto(10);
    }
}

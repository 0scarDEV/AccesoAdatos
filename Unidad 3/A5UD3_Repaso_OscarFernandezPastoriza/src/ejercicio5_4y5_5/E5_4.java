package ejercicio5_4y5_5;

import comunes.Conexion;

public class E5_4 {
    /* Agrega un metodo que reciba un array de objetos Proyectos y los añada a la tabla Proyecto. Utiliza procesamiento por lotes para enviar las sentencias de inserción al SGBD como una sola unidad de trabajo. */
    public static void main(String[] args) {
        OperacionesSQLServerProyecto opProyecto = new OperacionesSQLServerProyecto(new Conexion(Conexion.SGDB.SQLServer).getConnection());

        Proyecto p1 = new Proyecto(11, "Prueba 1", "Bueu", 7);
        Proyecto p2 = new Proyecto(12, "prueba 2", "Bueu", 7);

        opProyecto.addProyectos(new Proyecto[] {p1, p2});
    }
}

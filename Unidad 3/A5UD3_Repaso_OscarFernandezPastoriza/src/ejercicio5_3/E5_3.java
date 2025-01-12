package ejercicio5_3;

import comunes.Conexion;

public class E5_3 {
    /* Desarrolla un metodo en Java que permita a los usuarios gestionar la tabla DEPARTAMENTO en la base de datos BDEmpresa. La aplicación debe ser capaz de eliminar un departamento y reasignar los proyectos que controla y los empleados que pertenecen a este departamento a otro departamento, cuyo nombre se proporcionará. El metodo recibe el nombre del departamento a borrar y el nombre del departamento a reasignar.
    Requisitos:
    * Visualización de Datos:
         Visualizar los datos del departamento a borrar.
         Visualizar los datos de los empleados pertenecientes al departamento a borrar.
         Visualizar los datos de los proyectos que controla el departamento a borrar.
    * Procedimiento almacenado
         Para obtener el código de un departamento se hará con una llamada a un procedimiento almacenado que se le pase el nombre y en un parámetro de salida devuelva el código.
    * Operaciones de Actualización:
         Utilizar ResultSet dinámico para realizar las operaciones de actualización.
    * Control de Errores:
         Controlar los posibles errores, como la inexistencia de los departamentos proporcionados.
    * Generación de Archivo JSON:
         Crear un archivo JSON con la información de la actualización, con el formato especificado.
    *  */

    public static void main(String[] args) {
        String eliminarDepartamento = "PERSOAL";
        String departamentoReasignar = "";

        OperacionesSQLServerDepartamento operacionesSQLServerDepartamento = new OperacionesSQLServerDepartamento(new Conexion(Conexion.SGDB.SQLServer).getConnection());
        operacionesSQLServerDepartamento.eliminarDepartamento(eliminarDepartamento, departamentoReasignar);
    }
}
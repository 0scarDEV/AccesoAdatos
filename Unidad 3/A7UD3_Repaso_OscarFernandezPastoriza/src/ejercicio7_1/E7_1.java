package ejercicio7_1;

import comunes.Conexion;

public class E7_1 {
/* En la base de datos Empresa en SQL Server se quieren guardar los familiares de los empleados.
        Se guarda la siguiente informacion:
            * NSS_empregado varchar(15) obligatorio,
            * Numero smallint Obligatorio ( para cada faniliar empezará en 1, y se incrementará de 1 e 1),
            * NSS varchar(15) obligatorio ( es el nss del familliar),
            * Nome varchar(25) obligatorio,
            * Apelido1 varchar(25) obligatorio,
            * Apelido2 varchar(25) opcional.
            * DataNacemento date opcional,
            * Parentesco varchar(20) obligatorio,
            * Sexo char(1) (solo puede tomar los valores H o M y por defecto es M)
        Hay que tener en centa que para un empleado solo puede introducirse un NSS diferente de cada familliar.
    Desde Java, crea las estructuras necesaria para almacenar la anterior informacion teniendo en cuenta que las restricciones de clave primaria, claves alternativas y restricciones CHECK se creará cada una con una sentencia aparte y se mandarán todas en un lote.
    Despues de crear la tabla, desde Java comprueba que se ha creado correctamente.
 */
    public static void main(String[] args) {
        OperacionesSQLServerFamiliares opFamiliares = new OperacionesSQLServerFamiliares(new Conexion(Conexion.SGDB.SQLServer).getConnection());

        opFamiliares.crearTablas();
    }
}

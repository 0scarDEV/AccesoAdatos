package E2_5y6;

import Comunes.Conexion;
import Comunes.OperacionesSqlServer;
import Comunes.Proxecto;

public class Ejercicio2_6 {
    public static void main(String[] args) {
        OperacionesSqlServer db = new OperacionesSqlServer(new Conexion(Conexion.SGDB.SQLServer));

        // a) Crea un metodo para visualizar os tipos de ResultSet e a concorrencia soportada polo conectador JDBC de SQL Server.
        db.visualizarTiposResultSet();

        /* b) Crea un metodo que reciba como parámetro un obxecto proxecto e insira os seus datos na táboa proxecto. O obxecto proxecto conten os datos dun proxecto novo. A inserción do novo proxecto realizarase a través dun ResultSet dinámico, xerado mediante unha consulta a todos os datos da táboa proxectos. Para controlar os erros, tedes que implementar os seguintes métodos:
            – Metodo que devolva true si o número e o nome do proxecto novo existen no ResultSet e fale no caso contrario.
            – Metodo que devolva true si o número de departamento existe na táboa departamento e false no caso contrario.
        */
        boolean res = db.insertarNuevoProxecto(new Proxecto(77, "Proxecto 1", "Lugar 1", 1));
        System.out.println(res);

        // c) Crea un metodo que se lle pase por parámetro unha cantidade e un número de departamento e incremente o salario de todos os empregados dese departamento nesa cantidade. Utiliza a actualización dinámica por medio de ResultSet.

        /* d) Crea metodo que execute unha sentenza parametrizada para obter nss, nome completo (nome e apelidos), localidade e salario dos empregados que teñan asignado un número de proxectos maior que o que se introduce por parámetro. O ResultSet obtido debe ser de só lectura e con scroll para permitir movernos polo ResultSet en todas as direccións e a partir del, realiza o seguinte:
            – Visualiza a información da primeira fila do ResultSet.
            – Visualiza a información da última fila do ResultSet.
            – Visualiza a información da antepenúltima fila do ResultSet.
            – Visualiza toda a información do ResultSet en sentido contrario, é dicir, desde a última fila ata a primeira
         */


    }
}

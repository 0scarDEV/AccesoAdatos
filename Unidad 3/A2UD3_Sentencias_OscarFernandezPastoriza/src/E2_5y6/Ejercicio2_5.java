package E2_5y6;

import Comunes.Conexion;
import Comunes.OperacionesSqlServer;
import Comunes.Proxecto;

public class Ejercicio2_5 {
    static OperacionesSqlServer db = new OperacionesSqlServer(new Conexion(Conexion.SGDB.SQLServer));

    public static void main(String[] args) {
        /* a)
            – Primeiro, na base de datos BDEmpresa, crea un procedemento almacenado chamado pr_cambioDomicilio para que modifique a dirección dun empregado cos datos que se lle pasan por parámetro. O procedemento recibirá como parámetros o nss do empre-gado, e os novos datos: rúa, número, piso, código postal e localidade.
            – Crea un metodo que chame ao procedemento sp_cambioDomicilio. O metodo recibirá como parámetros o nss do empregado, a rúa, o número, o piso, o código postal e a localidade.
         */

        db.actualizarDireccionEmpregado("2221111", "Rua Caravela", 3, "1IZQ", "36937", "Pontevedra");

        /* b)
            – Crea un procedemento almacenado chamado pr_DatosProxectos que reciba un número de proxecto e devolva o nome, lugar e número de departamento de dito proxecto. O procedemento terá un parámetro de entrada e tres de saída.
            – Crea un metodo que chame ao procedemento pr_DatosProxectos. O metodo recibirá como parámetros o número de proxecto e devolverá un obxecto proxecto
         */

        Proxecto p = db.getProxecto(1);
        System.out.print(p);

        /* c)
            – Crea un procedemento almacenado chamado pr_DepartControlaProxec que mostre os datos dos departamentos que controlan un número de proxectos igual ou maior que un valor enteiro pasado por parámetro.
            – Crea un metodo que chame ao procedemento pr_DepartControlaProxec. O metodo recibirá como parámetros un número enteiro, e visualizará os datos dos departamentos que controlan un número de proxectos igual ou maior que o valor pasado por parámetro. Utiliza a instrución execute para a chamada ao procedemento. Visualiza tamén si se tratou dunha sentenza de actualización ou de selección.
         */

        db.mostrarDepartamentosConMas_X_Proxectos(2);

        /* d)
            – Crea unha función chamada fn_nEmpDepart que dado o nome do departamento, devolva o número de empregados de dito departamento.
            – Crea un metodo que dado o nome do departamento, execute a anterior función visualizando o resultado
         */

        db.mostrarNumEmpregadosPorDepartamento("PERSOAL");
    }
}
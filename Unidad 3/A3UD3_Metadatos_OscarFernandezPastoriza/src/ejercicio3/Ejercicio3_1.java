package ejercicio3;

import comunes.Conexion;
import comunes.OperacionesMySQL;

public class Ejercicio3_1 {
    /*
    Realiza un programa Java para establecer unha conexión co SXBD MySQL, que acceda á base de datos BDEmpresa, implemente e chame o seguinte metodo. Controla os posibles erros e separa a chamada aos métodos da implementación deles en clases diferentes.
    – Crea un metodo para que mostre a seguinte información: nome do SXBD, número de versión do SXBD, número de versión principal do SXBD, número de versión secundario do SXBD, nome do conectador JDBC utilizado, número da versión principal do conectador JDBC, número da versión secundaria do conectador JDBC, número de versión do conectador JDBC utilizado, URL da base de datos, nome do usuario actual conectado á base de datos e si a base de datos é de só lectura.
     */

    public static void main(String[] args) {
        OperacionesMySQL operacionesMySQL = new OperacionesMySQL(new Conexion(Conexion.SGDB.MySQL));
        operacionesMySQL.mostrarInformacion();
    }
}

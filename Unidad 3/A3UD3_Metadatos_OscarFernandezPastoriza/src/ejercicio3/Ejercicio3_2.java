package ejercicio3;

import comunes.Conexion;
import comunes.OperacionesMySQL;

public class Ejercicio3_2 {
    public static void main(String[] args) {
        OperacionesMySQL operacionesMySQL = new OperacionesMySQL(new Conexion(Conexion.SGDB.MySQL));
        //  a) Metodo que mostre información de todas as táboas de usuario da base de datos BDEmpresa.
        operacionesMySQL.mostrarInfoTablasUsuario();

        //  b) Metodo que reciba un esquema e unha táboa como parámetros e visualice as súas columnas. Mostrarase para cada columna, o nome, tipo de datos, tamaño e si admite valores nulos ou non.
        operacionesMySQL.mostrarColumnasTabla("dbo", "empregado");

        //  c) Metodo que mostre información de todos os procedementos da base de datos BDEmpresa.
        operacionesMySQL.mostrarProcedimientos();

        //  d) Metodo que reciba un esquema e unha táboa como parámetros e visualice as súas columnas de clave primaria.
        operacionesMySQL.mostrarColumnasClavePrimaria("dbo", "empregado");

        //  e) Metodo que reciba un esquema e unha táboa e visualice as súas columnas de clave foráneas. Mostrarase para cada columna de clave foránea a columna e a táboa a que referencia
        operacionesMySQL.mostrarColumnasClaveForanea("dbo", "empregado");
    }
}

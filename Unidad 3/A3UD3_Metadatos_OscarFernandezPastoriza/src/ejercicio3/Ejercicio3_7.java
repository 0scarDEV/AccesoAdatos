package ejercicio3;

import comunes.Conexion;
import comunes.OperacionesMySQL;

public class Ejercicio3_7 {
    /* Engade ao programa creado na tarefa1, un metodo que reciba unha consulta (por ex. SELECT * FROM proxecto) e imprima o número de columnas recuperadas, e por cada columna: o nome, tipo, tamaño e si admite ou non nulos. */
    public static void main(String[] args) {
        OperacionesMySQL operacionesMySQL = new OperacionesMySQL(new Conexion(Conexion.SGDB.MySQL));
        operacionesMySQL.getQueryMetadata("SELECT * FROM PROXECTO");
    }
}

package bdene25_oscarfp;

import bbdd.Conexion;
import bbdd.OperacionesAlojamientos;

/* Óscar Fernández Pastoriza - 52862191D */
public class Ejercicio3 {
    public static void main(String[] args) {
        OperacionesAlojamientos opAlojamientos = new OperacionesAlojamientos(new Conexion("jdbc:sqlserver://localhost\\MSQLSERVER:50043;database=BDALOJAMIENTOS25;trustServerCertificate=true", "sa", "abc123."));

        opAlojamientos.actualizarPrecios("src/AlojamientosPrecios.json");
    }
}
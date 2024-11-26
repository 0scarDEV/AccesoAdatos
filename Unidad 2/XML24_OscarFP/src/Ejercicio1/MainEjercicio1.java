package Ejercicio1;

/* Óscar Fernández Pastoriza - 53862191D */

import Comunes.OperacionesDOM;

import java.io.File;

public class MainEjercicio1 {
    public static void main(String[] args) {
        File mediciones = new File("medicionesRios.xml");
        OperacionesDOM operacionesDOM = new OperacionesDOM(mediciones);

        // Esto muestra true
        String codRio = "R002";
        String fecha = "15-ene-2023";

        boolean hayMedicion = operacionesDOM.hayMedicionEnRioFecha(codRio, fecha); // Devuelve true
        System.out.println("¿Existe una medición en el río " + codRio + " en la fecha " + fecha + "? " + hayMedicion);

        // Esto muestra false
        codRio = "R002";
        fecha = "25-ene-2030";
        hayMedicion = operacionesDOM.hayMedicionEnRioFecha(codRio, fecha); // Devuelve false
        System.out.println("¿Existe una medición en el río " + codRio + " en la fecha " + fecha + "? " + hayMedicion);
    }
}

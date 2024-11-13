package fich24.oscarfp;

import java.io.File;
import java.util.ArrayList;

public class Ejercicio2 {
    final static String RUTA_FICHERO_BIN = "Restaurantes.dat";
    final static String RUTA_FICHERO_COCINEROS = "Cocineros.dat";
    final static double LONGITUD_REGISTRO = 200.0;

    public static void main(String[] args) {
        ArrayList<InfoCocinero> infoCocineros = new ArrayList<>();
        infoCocineros.add(new InfoCocinero(1, "Jefe de cocina"));
        nuevoRestaurante("Casa Oscar", "Bueu", false, infoCocineros);

        infoCocineros.add(new InfoCocinero(2, "Cocinero internacional"));
        nuevoRestaurante("Casa Roco", "Bueu", false, infoCocineros);

        infoCocineros.add(new InfoCocinero(3, "Chef"));
        nuevoRestaurante("Casa Gaston", "Bueu", false, infoCocineros);

        infoCocineros.add(new InfoCocinero(4, "Ayudante"));
        nuevoRestaurante("Casa Kesto", "Bueu", false, infoCocineros);
    }

    public static void nuevoRestaurante(String nombre, String localidad, boolean baja, ArrayList<InfoCocinero> informacionCocineros) {
        File fichero = new File(RUTA_FICHERO_BIN);
        File ficheroCocineros = new File(RUTA_FICHERO_COCINEROS);

        Restaurante restaurante = new Restaurante(nombre, localidad, informacionCocineros, baja);
        RestauranteBinarioAleatorio.add(fichero, ficheroCocineros, LONGITUD_REGISTRO, restaurante);
        RestauranteBinarioAleatorio.mostrarTodos(fichero, LONGITUD_REGISTRO);
    }
}

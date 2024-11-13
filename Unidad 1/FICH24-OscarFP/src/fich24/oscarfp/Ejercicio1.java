package fich24.oscarfp;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author ofernpast
 * Óscar Fernández Pastoriza - 53862191D
 */

public class Ejercicio1 {
    final static String RUTA_FICHERO_BIN = "Cocineros.dat";

    public static void main(String[] args) {
        // Dar de alta a los cocineros pasando argumentos

        // Cocinero 1
        ArrayList<String> recetasPato = new ArrayList<>();
        recetasPato.add("Croquetas");
        darAltaCocinero("pato", "Oscar Fernandez Pastoriza", LocalDate.of(2005, 7, 17), recetasPato);

        // Cocinero 2
        ArrayList<String> recetasRoco = new ArrayList<>();
        recetasRoco.add("Paella");
        darAltaCocinero("roco", "Alvaro Raez Gallego", LocalDate.of(2005, 7, 17), recetasRoco);

        // Cocinero 3
        ArrayList<String> recetasGaston = new ArrayList<>();
        recetasGaston.add("Salmorejo");
        darAltaCocinero("gaston", "Jorge Lara Esquivel", LocalDate.of(2005, 7, 17), recetasGaston);

        // Cocinero 4
        ArrayList<String> recetasKesto = new ArrayList<>();
        recetasKesto.add("Cachopo");
        darAltaCocinero("kesto", "Iker Garcia Nuñez", LocalDate.of(2005, 7, 17), recetasKesto);

        // Dar de alta cocineros a partir de un fichero
        darAltaCocinero(new File("DatosCocineros.txt"));

        // Voy a volver intentar a insertar un cocinero con el apodo "pato", sale error.
        darAltaCocinero("pato", null, null, null);
    }

    private static void darAltaCocinero(String apodo, String nombre, LocalDate fechaNacimiento, ArrayList<String> recetas) {
        File file = new File(RUTA_FICHERO_BIN);

        // Comprobamos que el apodo no esté repetido
        if (CocineroBinarioSecuencial.esApodoRepetido(file, apodo)) {
            return;
        }

        Cocinero cocinero = new Cocinero(apodo, nombre, fechaNacimiento, recetas);
        // Le asignamos un número
        cocinero.setCodigo(CocineroBinarioSecuencial.obtenerUltCodigo(file) + 1);

        // Añadimos el cocinero al fichero binario
        CocineroBinarioSecuencial.add(file, cocinero);
        System.out.println(cocinero);
    }

    private static void darAltaCocinero(File fichero) {
        // Calculamos las lineas de texto que tiene el fichero
        int lineas = CocineroTexto.getLineas(fichero);

        for (int i = 0; i < lineas; i++) {
            // Leemos uno por uno los cocineros y los creamos
            Cocinero cocinero = CocineroTexto.getCocinero(fichero, i);

            // Lo damos de alta llamando al otro metodo.
            darAltaCocinero(cocinero.getApodo(), cocinero.getNombreCompleto(), cocinero.getFechaNacimiento(), cocinero.getRecetas());
        }
    }
}

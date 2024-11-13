package fich24.oscarfp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class Ejercicio3 {
    public static void main(String[] args) {
        // Metodo que genera un fichero de texto llamado cocineroPorEdad
        generarTxtPorEdad("Cocineros.dat");
    }

    private static void generarTxtPorEdad(String ruta) {
        File ficheroCocinerosDat = new File(ruta);
        File ficheroCocinerosPorEdad = new File("CocinerosporEdad.txt");

        // Obtenemos el mapa ordenador por edad
        LinkedHashMap<Integer, ArrayList<Cocinero>> mapaCocineros = getMapaOrdenadoPorEdad(ficheroCocinerosDat);

        // Obtenemos el texto que queremos insertar
        String contenido = String.valueOf(getTextoInsertar(mapaCocineros));

        // Escribimos el texto en el archivo
        try(BufferedWriter out = new BufferedWriter(new FileWriter(ficheroCocinerosPorEdad, true))) {
            out.write(contenido);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static LinkedHashMap<Integer, ArrayList<Cocinero>> getMapaOrdenadoPorEdad(File fichero) {
        LinkedHashMap<Integer, ArrayList<Cocinero>> mapaCocineros = new LinkedHashMap<>();
        ArrayList<Cocinero> allCocineros = CocineroBinarioSecuencial.getCocineros(fichero);

        // Ordenamos los cocineros
        allCocineros.sort(new EdadComparator());

        for (Cocinero c : allCocineros) {
            int edadCocinero = c.calcularEdad();
            ArrayList<Cocinero> cocinerosPorEdad = new ArrayList<>();

            if (mapaCocineros.containsKey(edadCocinero)) {
                cocinerosPorEdad = mapaCocineros.get(edadCocinero);
            }
            cocinerosPorEdad.add(c);
            mapaCocineros.put(edadCocinero, cocinerosPorEdad);
        }

        return mapaCocineros;
    }

    private static StringBuilder getTextoInsertar(LinkedHashMap<Integer, ArrayList<Cocinero>> mapaCocineros) {
        Set<Integer> setEdad = mapaCocineros.keySet();
        StringBuilder str = new StringBuilder();

        for (Integer edad : setEdad) {
            str.append("\nCocineros con Edad ").append(edad);
            str.append("\nCódigo\tApodo (Nombre)");
            for (int j = 0; j < mapaCocineros.get(edad).size(); j++) {
                Cocinero c = mapaCocineros.get(edad).get(j);
                str.append("\n" + c.getCodigo() + "\t" + c.getApodo() + " (" + c.getNombreCompleto() + ")");
            }
            str.append("\n--------------------------------------------------------------\n");
        }

        return str;
    }
}

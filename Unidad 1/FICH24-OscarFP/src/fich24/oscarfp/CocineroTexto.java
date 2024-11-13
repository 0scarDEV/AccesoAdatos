package fich24.oscarfp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author ofernpast
 * Óscar Fernández Pastoriza - 53862191D
 */

public class CocineroTexto {
    // Altas de los cocineros
    public static Cocinero getCocinero(File fichero, int i) {
        String linea;
        int j = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(fichero))) {
            while (in.ready()) {
                linea = in.readLine();
                if (j == i) {
                    String[] datos = linea.split(",");

                    String apodo = datos[0];
                    String nombreCompleto = datos[1];
                    LocalDate ldFechaNacimiento = getLocalDate(datos[2]);

                    ArrayList<String> listaRecetas = new ArrayList<>();
                    for (int k = 2; k < datos.length; k++) {
                        listaRecetas.add(datos[k]);
                    }

                    return new Cocinero(apodo, nombreCompleto, ldFechaNacimiento, listaRecetas);
                } else {
                    j++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static LocalDate getLocalDate(String strFecha) {
        String[] datosFecha = strFecha.split("/");
        int anho = Integer.parseInt(datosFecha[2]);
        int mes = Integer.parseInt(datosFecha[1]);
        int dia = Integer.parseInt(datosFecha[0]);
        return LocalDate.of(anho, mes, dia);
    }

    public static int getLineas(File fichero) {
        int numLineas = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(fichero))) {
            while (in.ready()) {
                in.readLine();
                numLineas++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return numLineas;
    }
}

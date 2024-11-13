package E3_4;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class E3_4 {
    public static void main(String[] args) {
        LinkedHashMap<Integer,Integer> mapa = new LinkedHashMap<>();
        ArrayList<Integer> numerosLeidos = obtenerDatosFichero("src/E3_3/Mezcla.dat");

        for (int numero : numerosLeidos) {
            mapa.put(numero, mapa.getOrDefault(numero, 1));
        }
    }

    private static ArrayList<Integer> obtenerDatosFichero(String nombreFichero) {
        ArrayList<Integer> lista = new ArrayList<>();
        String ruta = "src/E3_3/" + nombreFichero;
        try (DataInputStream entrada = new DataInputStream(new FileInputStream(ruta))) {
            int dato;
            while (true){
                dato=entrada.readInt();
                lista.add(dato);
            }
        } catch (EOFException e) {
            System.out.println("Se llegó al final del fichero \"" + nombreFichero + "\".");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}

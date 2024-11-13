package E31_P2;

import java.io.*;
/*
    Escribe un programa que cuente el número de líneas de cada fichero que se especifique en la línea de comandos (Nota: pueden especificarse varios archivos, como por ejemplo: "exercicio5-1 file1.txt file3.txt file2.txt"). Los archivos deben ser archivos de texto con la extensión txt. Escribe en un fichero de texto llamado Salida.txt: el nombre de cada fichero, junto con el número de líneas del fichero. Si ocurre un error al intentar leer uno de los ficheros, en el fichero salida.txt se graba un mensaje de error para el archivo, y se deben procesar todos los ficheros restantes
 */
public class E31_P2 {
    public static void main(String[] args) {

        try (FileWriter salida = new FileWriter("src/E31_P2/salida.txt");){
            for (int i = 0; i < args.length; i++) {
                try (BufferedReader lector = new BufferedReader(new FileReader(args[i]))) {
                    int numLineas = 0;
                    while (lector.ready()) {
                        lector.readLine();
                        numLineas++;
                    }
                    salida.write("El archivo \"" + args[i] + "\" tiene " + numLineas + " líneas.\n");
                } catch (IOException e) {
                    salida.write("Ha ocurrido un error al leer el archivo \"" + args[i] + "\"\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

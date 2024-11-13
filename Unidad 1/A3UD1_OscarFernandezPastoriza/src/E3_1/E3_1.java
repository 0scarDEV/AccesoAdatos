package E3_1;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/*
    Crear un programa que genere 150 números enteros aleatorios comprendidos entre 20 y 80 y los grabe en un fichero binario cuyo nombre será introducido por el usuario. A continuación visualiza en pantalla el contenido de este archivo. Debes controlar todos los posibles errores que se puedan generar. Si el fichero existe, se visualizará un mensaje, permitiendo elegir si se quiere remplazar el contenido o se quiere cancelar la operación de escritura
*/
public class E3_1 {
    public static void main(String[] args) {
        Random random = new Random();
        int numGenInicio = 20;
        int numGenFinal = 80;
        int cantidadNumAleatorios = 150;
        int[] numerosGenerados = new int[cantidadNumAleatorios];
        String ruta = "src/E3_1/";

        //Pedimos al usuario el nombre del archivo:
        Scanner scNombre = new Scanner(System.in);
        System.out.print("Introduce el nombre del archivo que contendrá los números: ");
        ruta += scNombre.nextLine();

        // Generamos de manera aleatoria los números.
        for (int i = 0; i < cantidadNumAleatorios; i++) {
            numerosGenerados[i] = random.nextInt(numGenInicio, numGenFinal);
        }

        // Verificamos si el fichero existe
        if (new File(ruta).exists()) {
            Scanner sc = new Scanner(System.in);
            boolean flag;

            do {
                System.out.print("\n¿Deseas sobreescribir el archivo (S) o cancelar (C)?");
                char opcion = sc.next().charAt(0);
                // Elegimos si sobreescrimos o dejamos de escribir.
                switch(opcion) {
                    case 'S' -> {
                        escribir(ruta, cantidadNumAleatorios, numerosGenerados);
                        flag = false;
                    }
                    case 'C' -> {
                        System.out.println("\nOperación cancelada.");
                        flag = false;
                    }
                    default -> {
                        System.out.println("\nError. Inténtalo de nuevo.");
                        flag = true;
                    }
                }
            } while (flag);
        } else {
            escribir(ruta, cantidadNumAleatorios, numerosGenerados);
        }

        // Leemos los datos del fichero.
        try (DataInputStream entrada = new DataInputStream(new FileInputStream(ruta))) {
            int datos;
            datos=entrada.readInt();
            System.out.println(datos);

            while (true){
                datos=entrada.readInt();
                System.out.println(datos + " ");
            }
        } catch (EOFException e) {
            System.out.println("\nFin del fichero.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribir(String ruta, int cantidadNumAleatorios, int[] numerosGenerados) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(ruta))) {
            for (int i = 0; i < cantidadNumAleatorios; i++) {
                out.writeInt(numerosGenerados[i]);
            }
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

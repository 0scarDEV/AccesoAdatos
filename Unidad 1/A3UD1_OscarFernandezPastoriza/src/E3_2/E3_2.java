package E3_2;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/*
* Escribir un programa para crear un archivo binario con valores numéricos enteros que sean consecutivos, es decir, que sea mayor o igual que el anterior. Los datos se irán solicitando al usuario. El proceso terminará cuando se introduzca cualquier carácter. A continuación visualiza en pantalla el contenido de este archivo. Debes controlar todos los posibles errores que se puedan generar. Si el fichero existe, se visualizará un mensaje, permitiendo elegir si se quiere remplazar el contenido o se quiere cancelar la operación de escritura. Crea tu propia excepción para controlar que el número introducido conserve la secuencia.
* */
public class E3_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ruta = "src/E3_2/numeros";
        boolean flag;

        // Verificaciones
        if (new File(ruta).exists()) {
            do {
                System.out.print("\n¿Deseas sobreescribir el archivo (S) o cancelar (C)? ");
                char opcion = sc.next().charAt(0);
                // Elegimos si sobreescrimos o dejamos de escribir.
                switch(opcion) {
                    case 'S' -> {
                        pedirNumeros(ruta);
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
            pedirNumeros(ruta);
        }

        leer(ruta);
    }

    private static void leer(String ruta) {
        System.out.println("Leyendo el fichero.... \n");

        try (DataInputStream entrada = new DataInputStream(new FileInputStream(ruta))) {
            int datos;
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

    private static void pedirNumeros(String ruta) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> listaEnteros = new ArrayList<>();

        System.out.println("\nEscribe números enteros, para terminar esribe un caracter.");
        while (true) {
            try {
                int entrada = sc.nextInt();
                if (listaEnteros.isEmpty() || listaEnteros.getLast() <= entrada) {
                    listaEnteros.add(entrada);
                } else {
                    throw new NumeroMenorException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un caracter en vez de un número, saliendo...");
                return;
            } catch (NumeroMenorException e) {
                System.out.println("No has escrito un número mayor o igual siguiendo la secuencia, saliendo...");
                return;
            } finally {
                escribir(ruta, listaEnteros);
            }
        }
    }

    private static void escribir(String ruta, ArrayList<Integer> listaEnteros) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(ruta))) {
            for (Integer entero : listaEnteros) {
                out.writeInt(entero);
            }
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package E3_3;

import java.io.*;

/*
* Utilizando el programa del ejercicio 17, crea dos ficheros de números enteros ordenados llamados Num1.dat y Num2.dat. Realizar un programa para crear un tercer archivo llamado Mezcla.dat que contenga los datos de los dos archivos ordenados.
    Se utilizará el algoritmo de mezcla de archivos que consiste en ir leyendo de los archivos de entrada e ir grabando directamente el numero correspondiente en el fichero de salida de tal manera que se conserve la ordenación (No se tiene que hacer leyendo los archivos de entrada, almacenarlos en una estructura en memoria, ordenar esta estructura y grabarla en el fichero de salida).
*/
public class E3_3 {
    public static void main(String[] args) {
        System.out.println("FICHERO NUM1.DAT");
        leerFichero("Num1.dat");
        System.out.println("FICHERO NUM2.DAT");
        leerFichero("Num2.dat");
        System.out.println("FICHERO MEZCLA.DAT");
        mezclaFicheros("Num1.dat", "Num2.dat");
        leerFichero("Mezcla.dat");
    }

    private static void mezclaFicheros(String nombreFichero1, String nombreFichero2) {
        String ruta1 = "src/E3_3/" + nombreFichero1;
        String ruta2 = "src/E3_3/" + nombreFichero2;
        String rutaSalida = "src/E3_3/Mezcla.dat";

        try (DataOutputStream salida = new DataOutputStream(new FileOutputStream(rutaSalida));
             DataInputStream entradaFile1 = new DataInputStream(new FileInputStream(ruta1));
             DataInputStream entradaFile2 = new DataInputStream(new FileInputStream(ruta2))) {
            int dato1 = entradaFile1.readInt();
            int dato2 = entradaFile2.readInt();
            while (true){
                if (dato2 < dato1) {
                    salida.writeInt(dato2);
                    try {
                        dato2 = entradaFile2.readInt();
                    } catch (EOFException e) {
                        while (true) {
                            try {
                                salida.writeInt(dato1);
                                salida.flush();
                                dato1 = entradaFile1.readInt();
                            } catch (EOFException b) {
                                salida.flush();
//                                System.out.println("Fin de ambos ficheros. El archivo 2 terminó primero");
                                return;
                            }
                        }
                    }
                } else {
                    salida.writeInt(dato1);
                    try {
                        dato1 = entradaFile1.readInt();
                    } catch (EOFException e) {
                        while (true) {
                            try {
                                salida.writeInt(dato2);
                                salida.flush();
                                dato2 = entradaFile2.readInt();
                            } catch (EOFException b) {
                                salida.flush();
//                                System.out.println("Fin de ambos ficheros. El archivo 1 terminó primero.");
                                return;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerFichero(String nombreFichero) {
        String ruta = "src/E3_3/" + nombreFichero;
        try (DataInputStream entrada = new DataInputStream(new FileInputStream(ruta))) {
            int dato;
            while (true){
                dato=entrada.readInt();
                System.out.println(dato);
            }
        } catch (EOFException e) {
            System.out.println("Se llegó al final del fichero \"" + nombreFichero + "\".");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

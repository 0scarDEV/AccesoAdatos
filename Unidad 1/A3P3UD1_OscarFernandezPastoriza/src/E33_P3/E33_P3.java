package E33_P3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * @author ofernpast
 */
public class E33_P3 {
    private final static String ruta = "src/E33_P3/corredores.dat";
    private static final File archivo = new File(ruta);
    private static LinkedHashMap<Integer, Participante> participantes = new LinkedHashMap<Integer, Participante>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mostrarMenu();
        int opcion = sc.nextInt();
        while (opcion != 7) {
            switch (opcion) {
                case 1 -> crearArchivo();
                case 2 -> addRegistro(true);
                case 3 -> {
                    System.out.print("\nIntroduce el registro a consultar: ");
                    int registro = sc.nextInt();
                    System.out.println(consultarRegistro(registro));
                }
                case 4 -> mostrarConsultaTodos();
                case 5 -> {
                    System.out.print("\nIntroduce el registro a modificar: ");
                    int registro = sc.nextInt();
                    modificarRegistro(registro);
                }
                case 6 -> {
                    System.out.print("\nIntroduce el registro a borrar: ");
                    int registro = sc.nextInt();
                    borrar(registro);
                }
                default -> System.out.println("Opción no valida");
            }
            mostrarMenu();
            opcion = sc.nextInt();
        }
        System.out.println("Saliendo del programa...");
        sc.close();
    }

    private static void mostrarConsultaTodos() {
        consultarTodos();
        if (participantes.isEmpty()) {
            System.out.println("\nNo hay registros en el archivo.\n");
            return;
        }
        for (Participante p : participantes.values()) {
            System.out.println(p);
        }
    }

    private static void crearCopia(boolean sustituir, int dorsal) {
        File copia = new File("src/E33_P3/copia.dat");
        try {
            copia.createNewFile();
            System.out.println("Archivo copia creado.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo copia: " + e.getMessage());
        }

        try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(ruta));
             ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(copia))) {
            while (true) {
                try {
                    Participante p = (Participante) lector.readObject();

                    if (p.getDorsal() == dorsal) {
                        if (sustituir) {
                            escritor.writeObject(participantes.get(dorsal));
                        }
                    } else {
                        escritor.writeObject(p);
                    }

                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Hubo un error en la escritura en la copia");
        }

        if (archivo.delete()) System.out.println(copia.renameTo(archivo)
                ? "El archivo ha sido reescrito"
                : "Hubo un error al reescribir el archivo.");
    }

    private static void borrar(int dorsal) {
        crearCopia(false, dorsal);
    }

    private static void modificarRegistro(int registro) {
        if (!archivo.exists()) {
            System.out.println("\nEl archivo al que le intentas modificar un registro no existe, créalo primero.\nSaliendo...\n");
            return;
        }

        Participante p = consultarRegistro(registro);
        System.out.println("\n" + p);
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Deseas modificar el registro? (S/n)");
        String respuesta = sc.nextLine().toLowerCase();
        if (respuesta.equals("s")) {
            Participante pNuevo = crearParticipante(registro);
            participantes.put(pNuevo.getDorsal(), pNuevo);
        } else {
            System.out.println("Modificación cancelada.");
        }

        crearCopia(true, registro);
    }

    private static void consultarTodos() {
        if (!archivo.exists()) {
            System.out.println("\nEl archivo al que intentas consultar registros no existe, créalo primero.\nSaliendo...\n");
            return;
        }

        boolean flag = false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            while (true) {
                try {
                    Participante p = (Participante) ois.readObject();
                    participantes.put(p.getDorsal(), p);
                    flag = true;
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            if (flag) {
                System.out.println("Error al consultar los registros: " + e.getMessage());
            }
        }
    }

    private static Participante consultarRegistro(int dorsal) {
        consultarTodos();
        return participantes.get(dorsal);
    }

    private static Participante crearParticipante(Integer dorsal) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el nombre del corredor: ");
        String nombre = sc.nextLine();
        if (nombre.equals("*")) {
            return null;
        }

        if (dorsal == null) {
            // Pedimos el dorsal del corredor, si lo contiene el ArrayList de dorsales entonces lo volvemos a pedir hasta que introduzca uno que no exista.
            System.out.print("Introduce el dorsal del corredor: ");
            dorsal = sc.nextInt();
            while (participantes != null && participantes.containsKey(dorsal)) {
                System.out.print("Ese dorsal ya existe, introduce otro: ");
                dorsal = sc.nextInt();
            }
        }

        System.out.print("Introduce el tiempo del corredor: ");
        double tiempo = sc.nextDouble();

        Participante p = new Participante(nombre, dorsal, tiempo);
        System.out.println("Creado el participante: " + p);
        return p;
    }

    private static void addRegistro(boolean append) {
        if (!archivo.exists()) {
            System.out.println("\nEl archivo al que intentas añadir registros no existe, créalo primero.\nSaliendo...");
            return;
        }

        consultarTodos();
        String nombre;

        try (FileOutputStream fos = new FileOutputStream(ruta, append);
             ObjectOutputStream oos = append && archivo.length() > 0
                     ? new AppendableObjectOutputStream(fos)
                     : new ObjectOutputStream(fos)) {
            do {
                Participante p = crearParticipante(null);
                if (p != null) {
                    oos.writeObject(p);
                    participantes.put(p.getDorsal(), p);
                    nombre = p.getNombre();
                } else {
                    nombre = "*";
                }
            } while (!nombre.equals("*"));

        } catch (IOException e) {
            System.out.println("Error al agregar registros: " + e.getMessage());
        }
    }

    private static void crearArchivo() {
        Scanner sc = new Scanner(System.in);
        String eleccion = "s";

        if (archivo.exists()) {
            System.out.println("El archivo ya existe. ¿Quiere sobreescribirlo? S/n: ");
            eleccion = sc.nextLine().toLowerCase();
        }

        if (eleccion.equals("s")) {
            try {
                archivo.createNewFile();
                System.out.println("Archivo creado.");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private static void mostrarMenu() {
        String str = "\nMenú de opciones" +
                "\n----------------" +
                "\n1.-Crear archivo" +
                "\n2.-Añadir registros" +
                "\n3.-Consultar un registro" +
                "\n4.-Consultar todos los registros" +
                "\n5.-Modificar un registro" +
                "\n6.-Borrar" +
                "\n7.-Salir" +
                "\nElige una opción <1-7>";
        System.out.println(str);
    }
}

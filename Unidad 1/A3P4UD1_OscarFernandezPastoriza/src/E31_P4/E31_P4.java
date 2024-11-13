package E31_P4;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class E31_P4 {
    private static final String RUTA_FICHERO = "src/E31_P4/Corredores.dat";
    private static final File FICHERO = new File(RUTA_FICHERO);
    private static final double TAMANHO_REGISTRO = 80.0;
    private static RandomAccessFile raf = null;

    private static void mostrarMenu() {
        String str = "\nMenú de opciones" +
                "\n----------------" +
                "\n1.-Añadir registros" +
                "\n2.-Consultar un registro" +
                "\n3.-Consultar todos los registros" +
                "\n4.-Modificar un registro" +
                "\n5.-Borrar" +
                "\n6.-Salir" +
                "\nElige una opción <1-6>";
        System.out.println(str);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        initFichero();
        while (opcion != 6) {
            mostrarMenu();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> addRegistro();
                case 2 -> {
                    System.out.print("\nIntroduzca el dorsal del registro a consultar: ");
                    consultarRegistro(sc.nextInt());
                }
                case 3 -> consultarTodos();
                case 4 -> {
                    System.out.print("\nIntroduzca el dorsal del registro a modificar: ");
                    modificarRegistro(sc.nextInt());
                }
                case 5 -> {
                    System.out.print("\nIntroduzca el dorsal del registro a borrar: ");
                    borrarRegistro(sc.nextInt());
                }
                default -> System.out.print("\nOpción no válida, inténtelo de nuevo: ");
            }
        }
    }

    private static void borrarRegistro(int dorsal) {
        if (consultarRegistro(dorsal)) {
            System.out.print("¿Deseas borrar el registro? (S/n): ");
            char eleccion = new Scanner(System.in).nextLine().toLowerCase().charAt(0);

            if (eleccion == 's') {
                try {
                    raf = new RandomAccessFile(FICHERO, "rw");
                    raf.seek((long) Math.ceil((dorsal - 1) * TAMANHO_REGISTRO));

                    String nombre = raf.readUTF();
                    raf.readInt();
                    double tiempo = raf.readDouble();

                    raf.seek((long) Math.ceil((dorsal - 1) * TAMANHO_REGISTRO));

                    raf.writeUTF(nombre);
                    raf.writeInt(dorsal);
                    raf.writeDouble(tiempo);
                    raf.writeBoolean(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void modificarRegistro(int dorsal) {
        if (consultarRegistro(dorsal)) {

            System.out.print("¿Deseas modificar el registro? (S/n): ");
            char eleccion = new Scanner(System.in).nextLine().toLowerCase().charAt(0);

            if (eleccion == 's') {
                try {
                    raf = new RandomAccessFile(FICHERO, "rw");
                    raf.seek((long) Math.ceil((dorsal - 1) * TAMANHO_REGISTRO));

                    System.out.print("Introduce el nombre del corredor: ");
                    String nombre = new Scanner(System.in).nextLine();
                    if (nombre.equals("*")) {
                        return;
                    }

                    System.out.print("\nIntroduce el tiempo del corredor: ");
                    double tiempo = new Scanner(System.in).nextDouble();

                    raf.writeUTF(nombre);
                    raf.writeInt(dorsal);
                    raf.writeDouble(tiempo);
                    raf.writeBoolean(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void consultarTodos() {
        for (int i = 1; i <= Math.ceil(FICHERO.length() / TAMANHO_REGISTRO); i++) {
            consultarRegistro(i);
        }
    }

    private static boolean consultarRegistro(int i) {
        try {
            if (i <= Math.ceil(FICHERO.length() / TAMANHO_REGISTRO)) {
                try {
                    raf = new RandomAccessFile(FICHERO, "r");

                    // Nos ponemos en la primera posición
                    raf.seek((long) ((i - 1) * TAMANHO_REGISTRO));

                    // Leemos
                    String nombre = raf.readUTF();
                    int dorsal = raf.readInt();
                    double tiempo = raf.readDouble();
                    boolean borrado = raf.readBoolean();

                    if (borrado) {
                        System.out.println("Registro " + dorsal + " dado de baja.");
                        return false;
                    }

                    Corredor corredor = new Corredor(nombre, dorsal, tiempo, borrado);
                    System.out.println(corredor);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                return true;
            } else {
                throw new DorsalNoRegistradoException();
            }
        } catch (DorsalNoRegistradoException e) {
            System.out.println("El dorsal introducido no existe, vuelve a introducir otro dorsal: ");
            return consultarRegistro(new Scanner(System.in).nextInt());
        }
    }

    private static void addRegistro() {
        String nombre;
        double tiempo;

        while (true) {
            int dorsal = (int) Math.ceil(FICHERO.length() / TAMANHO_REGISTRO) + 1;

            do {
                System.out.print("Introduce el nombre del corredor: ");
                nombre = new Scanner(System.in).nextLine();
                if (nombre.equals("*")) {
                    return;
                }
                if (esNombreInvalido(nombre)) {
                    System.out.println("El nombre del corredor introducido excede el tamaño permitido.");
                }
            } while (esNombreInvalido(nombre));

            System.out.print("\nIntroduce el tiempo del corredor: ");
            tiempo = new Scanner(System.in).nextDouble();

            try {
                raf = new RandomAccessFile(FICHERO, "rw");
                raf.setLength((long) (dorsal * TAMANHO_REGISTRO));
                raf.seek((long) ((dorsal - 1) * TAMANHO_REGISTRO));
                raf.writeUTF(nombre);
                raf.writeInt(dorsal);
                raf.writeDouble(tiempo);
                raf.writeBoolean(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static boolean esNombreInvalido(String nombre) {
        final int TAMANHO_INT = 4;
        final int TAMANHO_DOUBLE = 8;
        final int TAMANHO_BOOLEAN = 1;
        int tamanhoNombreMasCabecera = nombre.length() + 2;

        return tamanhoNombreMasCabecera + TAMANHO_INT + TAMANHO_DOUBLE + TAMANHO_BOOLEAN  > TAMANHO_REGISTRO;
    }

    private static void initFichero() {
        try {
            if (FICHERO.createNewFile()) {
                System.out.println("El archivo no existía, se ha creado.");
            } else {
                int numRegistros = (int) Math.ceil(FICHERO.length() / TAMANHO_REGISTRO);
                System.out.println("El archivo ya existía, con " + numRegistros + " registros.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

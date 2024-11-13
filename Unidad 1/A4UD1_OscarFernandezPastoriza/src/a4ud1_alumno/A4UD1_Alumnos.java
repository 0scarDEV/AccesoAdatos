package a4ud1_alumno;

import CLASESDATOS.Alumno;
import CLASESDATOS.Nombre;
import CLASESDATOS.NotaAlumno;
import CLASESDATOS.NotaModulo;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class A4UD1_Alumnos {
    final static File FICHERO_DATOS = new File("alumnos.dat");
    final static File FICHERO_NOTAS = new File("NotasAlumnos.dat");
    final static double LONGITUD_REGISTRO_ALUMNO = 100.0;
    static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    static LinkedHashMap<Integer, Alumno> alumnos = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        mostrarMenu();
        opcion = sc.nextInt();
        while (opcion != 7) {
            switch (opcion) {
                case 1 -> listarDatos();
                case 2 -> listarNotas();
                case 3 -> anhadirAlumno();
                case 4 -> {
                    obtenerAlumnos();
                    System.out.print("Introduce el número del alumno: ");
                    System.out.println(alumnos.get(sc.nextInt()).toString());
                    System.out.println();
                }
                case 5 -> {
                    System.out.print("Introduce un número de alumno: ");
                    int numAlumno = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Introduce un número de teléfono: ");
                    String telefono = sc.nextLine();
                    gestionTelefonos(numAlumno, telefono);
                }
                case 6 -> exportar();
                default -> System.out.println("Opción no válida");
            }
            mostrarMenu();
            opcion = sc.nextInt();
        }
    }

    /* A42_UD1_Alumno */
    public static LinkedHashMap<Integer, Alumno> getAlumnos() {
        obtenerAlumnos();
        return alumnos;
    }

    /* Opción 6 -> Escribir en un fichero */
    private static void exportar() {
        obtenerAlumnos();
        try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("Alumnos.txt"))) {
            out.write("----------------------DATOS ALUMNOS ----------------------------------------");
            for (int i = 0; i < alumnos.size(); i++) {
                Alumno alumno = alumnos.get(i+1);
                if (alumno.isBorrado()) {
                    continue;
                }
                out.write("\nALUMNO NÚMERO: " + alumno.getNumero());
                out.write("\nNOMBRE: " + alumno.getNombre());
                out.write("\nFECHA NACIMIENTO: " + alumno.getFechaNacimiento());
                out.write("\nTELÉFONOS (S):");
                ArrayList<String> telefonos = alumno.getTelefono();
                for (String telefono : telefonos) {
                    out.write(" " + telefono);
                }

                String formato = "%-20s %10s";
                out.write("\n\n" + String.format(formato, "MODULO", "NOTA"));
                out.write("\n--------------------------------------------------");
                double sumandoNotas = 0;
                ArrayList<NotaModulo> notas = obtenerNotaAlumno(alumno.getNumero()).getNotas();
                for (NotaModulo nota : notas) {
                    out.write( "\n" + String.format(formato, nota.getAsignatura(), nota.getNota()));
                    sumandoNotas += nota.getNota();
                }

                double notaMedia = sumandoNotas / notas.size();
                out.write("\n" + String.format(formato, "NOTA MEDIA:", notaMedia));

                out.write("\n------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static NotaAlumno obtenerNotaAlumno(int numero) {
        obtenerAlumnos();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FICHERO_NOTAS))) {
            while (true) {
                NotaAlumno notaAlumno = (NotaAlumno) in.readObject();

                if (notaAlumno.getNumero() == numero) {
                    return notaAlumno;
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin de fichero");
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /* Opción 5 -> Gestión de teléfonos */
    private static void gestionTelefonos(int numAlumno, String telefono) {
        Scanner sc = new Scanner(System.in);
        boolean cambios = false;

        obtenerAlumnos();
        Alumno alumno = alumnos.get(numAlumno);
        ArrayList<String> telefonos = alumno.getTelefono();
        System.out.println(alumno);

        if (telefonos.contains(telefono)) {
            System.out.print("El teléfono ya existe. ¿Desea eliminarlo? (s/N): ");
            if (sc.next().equalsIgnoreCase("s")) {
                telefonos.remove(telefono);
                System.out.println("Teléfono eliminado");
                cambios = true;
            }
        } else {
            System.out.print("El teléfono introducido no se encuentra entre los teléfonos del alumno. ¿Desea añadirlo? (s/N): ");
            if (sc.next().equalsIgnoreCase("s")) {
                telefonos.add(telefono);
                System.out.println("Teléfono añadido");
                cambios = true;
            }
        }
        if (cambios) {
            System.out.println("Los datos del alumno han sido modificados: ");
            System.out.println(alumno);
            escribirAlumno(alumno, numAlumno);
        } else {
            System.out.println("No se han llevado a cabo cambios");
        }
    }

    /* Opción 3 -> Añadir alumno */
    private static void anhadirAlumno() {
        long tamanho_fichero = FICHERO_DATOS.length();

        // Crear un nuevo objeto Alumno y calcular su número
        Alumno alumno = crearNuevoAlumno();
        int numAlumno = (int) Math.ceil(tamanho_fichero / LONGITUD_REGISTRO_ALUMNO) + 1;
        alumno.setNumero(numAlumno);

        // Añadir las notas
        NotaAlumno nota = new NotaAlumno();
        nota.setNumero(numAlumno);
        nota.setNotas(crearNotas());

        // Añadir a alumnos.dat
        escribirAlumno(alumno, numAlumno);

        // Añadir a NotasAlumnos.dat
        try (ObjectOutputStream out = new AppendableObjectOutputStream(new FileOutputStream(FICHERO_NOTAS, true))) {
            out.writeObject(nota);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<NotaModulo> crearNotas() {
        ArrayList<NotaModulo> notaModulos = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el número de módulos: ");
        int numModulos = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numModulos; i++) {
            System.out.print("Introduce el nombre del módulo: ");
            String asignatura = sc.nextLine();
            System.out.print("Introduce la nota del módulo: ");
            Double nota = sc.nextDouble();
            sc.nextLine();
            notaModulos.add(new NotaModulo(asignatura, nota));
        }

        return notaModulos;
    }

    private static Alumno crearNuevoAlumno() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> listaTelefonos = new ArrayList<>();

        System.out.print("Introduce el nombre del alumno: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce el primer apellido: ");
        String apellido1 = sc.nextLine();
        System.out.print("Introduce el segundo apellido: ");
        String apellido2 = sc.nextLine();

        Date fechaNac;
        do {
            System.out.print("Introduce la fecha de nacimiento (dd/MM/yyyy): ");
            String fechaNacimiento = sc.nextLine();
            try {
                fechaNac = formato.parse(fechaNacimiento);
            } catch (ParseException e) {
                System.out.print("Error al introducir la fecha, inténtalo de nuevo: ");
                fechaNac = null;
            }
        } while (fechaNac == null);

        System.out.print("Introduce los teléfonos (separados por espacios): ");
        ArrayList<String> telefonos = new ArrayList<>(List.of(sc.nextLine().split(" ")));
        for (String telefono : telefonos) {
            if (telefonos.contains(telefono)) {
                do {
                    System.out.print("El teléfono " + telefono + " está repetido. Escribe un nuevo número de teléfono a continuación: ");
                    telefono = sc.nextLine();
                } while (telefonos.contains(telefono));
                listaTelefonos.add(telefono);
            } else {
                listaTelefonos.add(telefono);
            }
        }

        return new Alumno(new Nombre(nombre, apellido1, apellido2), fechaNac, listaTelefonos, false);
    }

    private static void escribirAlumno(Alumno alumno, int numAlumno) {
        try (RandomAccessFile raf = new RandomAccessFile(FICHERO_DATOS, "rw")) {
            raf.setLength((long) (numAlumno * LONGITUD_REGISTRO_ALUMNO));
            raf.seek((long) Math.ceil((numAlumno - 1) * LONGITUD_REGISTRO_ALUMNO));


            raf.writeInt(alumno.getNumero());
            raf.writeUTF(alumno.getNombre().getNombre());
            raf.writeUTF(alumno.getNombre().getApellido1());
            raf.writeUTF(alumno.getNombre().getApellido2());
            raf.writeLong(alumno.getFechaNac().getTime());
            raf.writeInt(alumno.getTelefono().size());
            for (String telefono : alumno.getTelefono()) {
                raf.writeUTF(telefono);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* Opción 2 -> Listar notas */
    private static void listarNotas() {
        obtenerAlumnos();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FICHERO_NOTAS))) {
            while (true) {
                NotaAlumno notaAlumno = (NotaAlumno) in.readObject();
                Alumno alumno = alumnos.get(notaAlumno.getNumero());

                if (!alumno.isBorrado()) {
                    System.out.println("Alumno número " + notaAlumno.getNumero() + ".\n" +
                                "Nombre completo: " + alumnos.get(notaAlumno.getNumero()).getNombre().toString());
                    System.out.println("Notas: ");
                    for (NotaModulo notaModulo : notaAlumno.getNotas()) {
                        System.out.println(notaModulo.getAsignatura() + ": " + notaModulo.getNota());
                    }
                } else {
                    System.out.println("Alumno número " + alumno.getNumero() + " está borrado.");
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin de fichero");
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    /* Opción 1 -> Listar datos */
    private static void listarDatos() {
        obtenerAlumnos();
        System.out.println("Listado de datos de todos los alumnos");
        for (int i = 1; i <= alumnos.size(); i++) {
            System.out.println(alumnos.get(i).toString());
        }
    }

    /* GENERAL - Funcionalidad del programa */
    private static void obtenerAlumnos() {
        try (RandomAccessFile raf = new RandomAccessFile(FICHERO_DATOS, "r")) {
            long tamanho_fichero = FICHERO_DATOS.length();
            int numero_registros = (int) Math.ceil(tamanho_fichero / LONGITUD_REGISTRO_ALUMNO);

            for (int i = 0; i < numero_registros; i++) {
                raf.seek((long) Math.ceil(i * LONGITUD_REGISTRO_ALUMNO));
                int numero = raf.readInt();

                String nombreUTF = raf.readUTF();
                String apellido1 = raf.readUTF();
                String apellido2 = raf.readUTF();
                Nombre nombre = new Nombre(nombreUTF, apellido1, apellido2);

                long fechaNacimiento = raf.readLong();
                Date fechaNac = new Date(fechaNacimiento);


                ArrayList<String> telefonos = new ArrayList<>();
                int numTelefonos = raf.readInt();
                for (int j = 0; j < numTelefonos; j++) {
                    String telefono = raf.readUTF();
                    telefonos.add(telefono);
                }

                Alumno alumno = new Alumno(nombre, fechaNac, telefonos, false);
                alumno.setNumero(numero);

                alumnos.put(numero, alumno);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void mostrarMenu() {
        String str = "\nMenú de opciones" +
                "\n----------------" +
                "\n1.-Listar datos de todos los alumnos" +
                "\n2.-Listar las notas de todos los alumnos" +
                "\n3.-Añadir un nuevo alumno" +
                "\n4.-Mostrar la información de un alumno" +
                "\n5.-Gestión de teléfonos" +
                "\n6.-Exportar" +
                "\n7.-Salir" +
                "\nElige una opción <1-7>";
        System.out.println(str);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4ud1_alumno;

import CLASESDATOS.Alumno;
import CLASESDATOS.Nombre;
import CLASESDATOS.NotaAlumno;
import CLASESDATOS.NotaModulo;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class A4UD1_Alumnos {
    final static double LONGITUD_REGISTRO = 100.0;
    static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    static File ficheroAlumnos = new File("alumnos.dat");
    static File ficheroNotas = new File("NotasAlumnos.dat");

    public static void main(String[] args) {
        // Listar los datos de todos los alumnos
        listarTodos(ficheroAlumnos);

        // Listar las notas ( modulo y nota) de todos los alumnos junto al número y nombre completo
        listarNotas(ficheroNotas);

        // Añadir un nuevo alumno y sus notas que ha obtenido en los diferentes módulos. El numero de alumno se genera automáticamente en secuencia a los números ya introducidos). Los números de teléfonos tienen que ser todos distintos.
        Nombre n = new Nombre();
        n.setNombre("Pepe");
        n.setApellido1("Pérez");
        n.setApellido2("García");
        Alumno a = new Alumno(n, new Date(), new ArrayList<>(), false);
        a.setNumero((int) Math.ceil(ficheroAlumnos.length() / LONGITUD_REGISTRO) + 1);

        ArrayList<NotaModulo> notasPorModulo = new ArrayList<>();
        notasPorModulo.add(new NotaModulo("Mates", 5.0));
        NotaAlumno notaAlumno = new NotaAlumno(a.getNumero(), notasPorModulo);

        addNewAlumno(a, notaAlumno);

        listarTodos(ficheroAlumnos);

        // A partir de un número de alumno, visualice toda su información ( datos y las notas)
        Alumno alumno = leerRegistro(ficheroAlumnos, 1);
        System.out.println(alumno);
    }

    private static void addNewAlumno(Alumno a, NotaAlumno notaAlumno) {
        escribirAlumno(a, a.getNumero());
        escribirNotaAlumno(ficheroNotas, notaAlumno);
    }

    private static void escribirAlumno(Alumno a, int idAlumno) {
        try (RandomAccessFile raf = new RandomAccessFile(ficheroAlumnos, "rw")) {
            raf.setLength((long) (idAlumno * LONGITUD_REGISTRO));           // Aumentas la longitud del archivo para que quepa el registro
            raf.seek((long) Math.ceil((idAlumno - 1) * LONGITUD_REGISTRO)); // Calculas la posición en la que empezarás a escribir tu archivo

            raf.writeInt(a.getNumero());
            raf.writeUTF(a.getNombre().getNombre());
            raf.writeUTF(a.getNombre().getApellido1());
            raf.writeUTF(a.getNombre().getApellido2());
            raf.writeLong(a.getFechaNac().getTime());
            raf.writeInt(a.getTelefono().size());
            for (String t : a.getTelefono()) {
                raf.writeUTF(t);
            }
            raf.writeBoolean(a.isBorrado());

            System.out.println("NUEVO ALUMNO AÑADIDO");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirNotaAlumno(File file, NotaAlumno na) {
        if (file.exists()) {
            try (AppendableObjectOutputStream appendableObjectOutputStream = new AppendableObjectOutputStream(new FileOutputStream(file, true))) {
                appendableObjectOutputStream.writeObject(na);
                System.out.println("Nueva nota de alumno añadida");
            } catch (Exception e) {
                System.out.println("Error al escribir el objeto.");
                e.printStackTrace();
            }
        } else {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                objectOutputStream.writeObject(na);
            } catch (Exception e) {
                System.out.println("Error al escribir el objeto.");
                e.printStackTrace();
            }
        }
    }


    private static void listarNotas(File ficheroNotas) {
        ArrayList<NotaAlumno> notasAlumnos = leerNotas(ficheroNotas);
        for (NotaAlumno n : notasAlumnos) {
            Alumno a = leerRegistro(ficheroAlumnos, n.getNumero() - 1);
            if (a != null && !a.isBorrado()) {
                System.out.println("Alúmno número " + a.getNumero());
                System.out.println("Nombre: " + a.getNombre());

                ArrayList<NotaModulo> notaModulos = n.getNotas();
                for (NotaModulo nm : notaModulos) {
                    System.out.println(nm);
                }
            }
        }
    }

    private static ArrayList<NotaAlumno> leerNotas(File ficheroNotas) {
        ArrayList<NotaAlumno> notasPorAlumno = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ficheroNotas))) {
            while (true) {
                notasPorAlumno.add((NotaAlumno) in.readObject());
            }
        } catch (EOFException e) {
            System.out.println("Fin del archivo.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return notasPorAlumno;
    }

    private static void listarTodos(File fichero) {
        ArrayList<Alumno> listadoAlumnos = leerTodos(fichero);
        for (Alumno a : listadoAlumnos) {
            if (!a.isBorrado()) {
                System.out.println(a);
                System.out.println();
            }
        }
    }

    private static ArrayList<Alumno> leerTodos(File fichero) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        long file_size = fichero.length();
        int numero_registros = (int) Math.ceil(file_size / LONGITUD_REGISTRO);

        for (int i = 0; i < numero_registros; i++) {
            alumnos.add(leerRegistro(fichero, i));
        }

        return alumnos;
    }

    private static Alumno leerRegistro(File fichero, int i) {
        Alumno a;

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            long file_size = fichero.length();
            int numero_registros = (int) Math.ceil(file_size / LONGITUD_REGISTRO);

            if (i < numero_registros) {
                // Nos posicionamos en el registro i
                raf.seek((long) i * (long) LONGITUD_REGISTRO);

                // Construimos el alumno
                int num = raf.readInt(); // Leemos para poder seguir leyendo
                Nombre nombre = new Nombre(raf.readUTF(), raf.readUTF(), raf.readUTF());

                long fechaNac = raf.readLong();
                Date fechaNacDate = new Date(fechaNac);

                int num_telefonos = raf.readInt();
                ArrayList<String> telefonos = new ArrayList<>();
                for (int j = 0; j < num_telefonos; j++) {
                    telefonos.add(raf.readUTF());
                }

                a = new Alumno(nombre, fechaNacDate, telefonos, raf.readBoolean());
                a.setNumero(num);
            } else {
                return null;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return a;
    }
}
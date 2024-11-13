package a4ud1_alumno;

import CLASESDATOS.Alumno;

import java.time.LocalDate;
import java.util.*;

public class A42_UD1_Alumno {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mostrarMenu();
        int opcion = sc.nextInt();
        while (opcion != 4) {
            switch (opcion) {
                case 1 -> listarOrdenadoPor(getAlumnosOrdenados(new FechasComparator()));
                case 2 -> listarNumAlumnosPorEdad(getAlumnosOrdenados(new EdadComparator()));
                case 3 -> listarOrdenadoPorAnho(getAlumnosOrdenados(new AnhosComparator()));
                default -> System.out.println("Opción no válida.");
            }

            mostrarMenu();
            opcion = sc.nextInt();
        }
    }

    private static void listarOrdenadoPorAnho(ArrayList<Alumno> alumnosOrdenados) {
        LinkedHashMap<Integer, ArrayList<Alumno>> mapaAnhos = new LinkedHashMap<>();
        for (Alumno alumno : alumnosOrdenados) {
            LocalDate d = alumno.getFechaNacimientoLocalDate();
            ArrayList<Alumno> alumnosPorAnho = new ArrayList<>();
            if (mapaAnhos.containsKey(d.getYear())) {
                alumnosPorAnho = mapaAnhos.get(d.getYear());
            }
            alumnosPorAnho.add(alumno);
            mapaAnhos.put(d.getYear(), alumnosPorAnho);
        }

        Set<Integer> setAnhos = mapaAnhos.keySet();
        for (Integer anho : setAnhos) {
            System.out.println("Año " + anho + ":");
            System.out.println("_______________________________________________________________________________");
            for (Alumno alumno : mapaAnhos.get(anho)) {
                System.out.println(alumno);
                System.out.println("_______________________________________________________________________________");
            }
        }
    }

    private static void listarNumAlumnosPorEdad(ArrayList<Alumno> alumnosOrdenados) {
        LinkedHashMap<Integer, ArrayList<Alumno>> mapaEdad = new LinkedHashMap<>();
        for (Alumno alumno : alumnosOrdenados) {

            int edadAlumno = alumno.getEdad();
            ArrayList<Alumno> alumnosPorEdad = new ArrayList<>();

            if (mapaEdad.containsKey(edadAlumno)) {
                alumnosPorEdad = mapaEdad.get(edadAlumno);
            }
            alumnosPorEdad.add(alumno);
            mapaEdad.put(edadAlumno, alumnosPorEdad);
        }

        Set<Integer> setEdad = mapaEdad.keySet();
        for (Integer edad : setEdad) {
            int numAlumnos = 0;
            for (int i = 0; i < mapaEdad.get(edad).size(); i++) {
                numAlumnos++;
            }
            System.out.println("Hay " + numAlumnos + " alumnos con " + edad + " años.");
        }
    }

    private static void listarOrdenadoPor(ArrayList<Alumno> alumnosOrdenados) {
        for (Alumno alumno : alumnosOrdenados) {
            System.out.println(alumno);
        }
    }

    private static ArrayList<Alumno> getAlumnosOrdenados(Comparator<Alumno> comparador) {
        LinkedHashMap<Integer, Alumno> mapaAlumnos = A4UD1_Alumnos.getAlumnos();
        ArrayList<Alumno> listaAlumnos = new ArrayList<>(mapaAlumnos.values());
        listaAlumnos.sort(comparador);
        return listaAlumnos;
    }

    private static void mostrarMenu() {
        System.out.println("1. Listar la información de todos los alumnos ordenados por fechas de mayor a menor.");
        System.out.println("2. Listar el número de alumnos que hay por edad.");
        System.out.println("3. Listar la información de cada alumno por cada año de nacimiento.");
    }
}

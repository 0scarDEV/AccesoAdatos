package ejercicio1;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainPaciente {
    public static void main(String[] args) throws ParseException {
        CrearPacientes();

        // Añadir pacientes de lista
        Operaciones.addTxt();
    }

    public static void CrearPacientes() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Paciente 1
        ArrayList<String> telefonos = new ArrayList<>();
        telefonos.add("123543534");
        telefonos.add("1231543345");

        Date fnac1 = sdf.parse("05/12/2000");

        Datos datos1 = new Datos("5123f", "Pepe", "Gonzalez", "Alvarez", fnac1);

        Operaciones.addPaciente(new Paciente(datos1, telefonos));

        // Paciente 2 con mismo dni
        ArrayList<String> telefonos2 = new ArrayList<>();
        telefonos.add("12343534");

        Date fnac2 = sdf.parse("05/12/2001");

        Datos datos2 = new Datos("5123f", "Juan", "Antonio", "Alvarez", fnac2);

        Operaciones.addPaciente(new Paciente(datos2, telefonos2));

        // Paciente 3
        ArrayList<String> telefonos3 = new ArrayList<>();

        Date fnac3 = sdf.parse("05/12/1999");

        Datos datos3 = new Datos("123414j", "Alberto", "Gonzalez", "Alvarez", fnac3);

        Operaciones.addPaciente(new Paciente(datos3, telefonos3));

        // Paciente 4
        ArrayList<String> telefonos4 = new ArrayList<>();

        Date fnac4 = sdf.parse("15/12/1999");

        Datos datos4 = new Datos("5475647a", "Antonio", "Alvarez", "Perez", fnac4);

        Operaciones.addPaciente(new Paciente(datos4, telefonos4));

        // Paciente 5
        ArrayList<String> telefonos5 = new ArrayList<>();
        telefonos5.add("54645645645656");
        telefonos5.add("12312312313");

        Date fnac5 = sdf.parse("15/12/1999");

        Datos datos5 = new Datos("123123e", "Marian", "Gonzalez", "Gonzalez", fnac5);

        Operaciones.addPaciente(new Paciente(datos5, telefonos5));
    }

}

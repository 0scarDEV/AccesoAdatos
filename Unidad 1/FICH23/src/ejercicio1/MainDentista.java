package ejercicio1;

import java.util.ArrayList;

public class MainDentista {
    public static void main(String[] args) {
        // OperacionesDentista.mostrarDentista(1);

        // OperacionesDentista.darBajaDentista(1);

        OperacionesDentista.darBajaPacienteDentista(2, 1);
        // addDentistas();

    }

    public static void addDentistas() {
        // Dentista 1
        ArrayList<Integer> pacientes = new ArrayList<>();
        pacientes.add(1);
        pacientes.add(2);

        OperacionesDentista.addDentista("Pepe Gonzalez", "12345B", pacientes, false);
        OperacionesDentista.mostrarDentistas();

        // Dentista 2 repite el numColegiado
        ArrayList<Integer> pacientes2 = new ArrayList<>();
        pacientes2.add(1);
        pacientes2.add(2);
        OperacionesDentista.addDentista("Alberto Gonzalez", "12345B", pacientes2, false);
        OperacionesDentista.mostrarDentistas();

        // Dentista 3 repite el paciente
        ArrayList<Integer> pacientes3 = new ArrayList<>();
        pacientes3.add(1);
        pacientes3.add(2);
        pacientes3.add(2);

        OperacionesDentista.addDentista("Antonio Huerta", "12312345a", pacientes3, false);
        OperacionesDentista.mostrarDentistas();
        // Dentista 4 pone un paciente inexistente
        ArrayList<Integer> pacientes4 = new ArrayList<>();
        pacientes4.add(1);
        pacientes4.add(2);
        pacientes4.add(25);

        OperacionesDentista.addDentista("Xabier Pastoriza", "4324234a", pacientes4, false);
        OperacionesDentista.mostrarDentistas();
    }

}

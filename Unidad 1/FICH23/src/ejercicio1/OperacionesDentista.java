package ejercicio1;

import java.io.IOException;
import java.util.ArrayList;

public class OperacionesDentista {
    static LeerEscribirAleatorio lea = new LeerEscribirAleatorio("Dentista.dat");
    static LeerFichero lf = new LeerFichero("Pacientes.dat");

    public static void addDentista(String nombre, String numColegiado, ArrayList<Integer> pacientes, boolean baja) {
        lea.abrirArchivo();
        boolean existeNumColegiado = lea.existeNumColegiado(numColegiado);
        lea.cerrarArchivo();

        if (existeNumColegiado) {
            System.out.println("No se ha añadido ya que existe el numero de colegiado");
            return;
        }

        if (pacientes.size() != 0) {
            for (int i = 0; i < pacientes.size(); i++) {
                lf.abrirArchivo();
                if (!lf.numPacienteExistente(pacientes.get(i))) {
                    System.out.println("Has agregado algun paciente que no existe");
                    return;
                }
                lf.cerrarArchivo();

                // Comprobar si el paciente existe
                lf.abrirArchivo();
                if (!lf.numPacienteExistente(pacientes.get(i))) {
                    System.out.println("No existe algun paciente. ");
                    return;
                }
                lf.cerrarArchivo();

                // Comprobar si el paciente existe mas de una vez
                for (int j = 0; j < pacientes.size(); j++) {

                    if (pacientes.get(i) == pacientes.get(j) && i != j) {
                        System.out.println("Tienes pacientes repetidos");
                        return;
                    }

                    lf.cerrarArchivo();
                }
            }
        }

        lea.abrirArchivo();
        int numero = lea.numRex() + 1;
        lea.cerrarArchivo();

        Dentista d = new Dentista(numero, nombre, numColegiado, pacientes, baja);

        lea.abrirArchivo();
        try {
            lea.addDentista(d);
        } catch (IOException e) {
            System.out.println("Error al añadir el dentista");
        }
        lea.cerrarArchivo();

    }

    public static void mostrarDentistas() {
        lea.abrirArchivo();
        lea.mostrarDentistas();
        lea.cerrarArchivo();
    }

    public static void mostrarDentista(int numeroDentista) {
        lea.abrirArchivo();
        lea.mostrarDentista(numeroDentista);
        lea.cerrarArchivo();
    }

    public static void darBajaDentista(int numeroDentista) {
        lea.abrirArchivo();
        lea.darBajaDentista(numeroDentista);
        lea.cerrarArchivo();
    }

    public static void darBajaPacienteDentista(int dentista, int paciente) {
        lea.abrirArchivo();
        lea.darBajaPacienteDentista(dentista, paciente);
        lea.cerrarArchivo();
    }

}

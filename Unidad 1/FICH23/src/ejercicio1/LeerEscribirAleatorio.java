package ejercicio1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class LeerEscribirAleatorio extends Archivo {
    private RandomAccessFile raf;
    private static LeerFichero lf = new LeerFichero("Pacientes.dat");
    private final int TAM_REGISTRO = 150;

    public LeerEscribirAleatorio(String f) {
        super(f);
    }

    @Override
    void abrirArchivo() {
        try {
            raf = new RandomAccessFile(f, "rw");
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    void cerrarArchivo() {
        try {
            raf.close();
        } catch (IOException e) {
            System.out.println("Error de entrada o salida.");
        }
    }

    public void mostrarDentistas() {
        int numRegistros = (int) Math.ceil((double) f.length() / TAM_REGISTRO);

        try {
            System.out.println("----------------------DATOS DENTISTAS----------------------------------------");
            for (int i = 1; i <= numRegistros; i++) {
                Dentista dentista = buscarDentista(i);

                if (dentista != null && !dentista.isBaja()) {
                    System.out.println(dentista.toString());
                    System.out.print("Pacientes:");

                    for (int j = 0; j < dentista.getPacientes().size(); j++) {
                        int numeroPaciente = dentista.getPacientes().get(j);
                        lf.abrirArchivo();
                        Paciente paciente = lf.bucarPaciente(numeroPaciente);
                        lf.cerrarArchivo();
                        System.out.print("[" + paciente.getNumPaciente() + "-" + paciente.getNombreCompleto() + "-]");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void mostrarDentista(int numeroDentista) {
        try {
            Dentista dentista = buscarDentista(numeroDentista);

            if (dentista == null) {
                System.out.println("Dentista no existe.");
            } else {
                System.out.println(dentista.toString());
                System.out.print("Pacientes:");

                for (int j = 0; j < dentista.getPacientes().size(); j++) {
                    int numeroPaciente = dentista.getPacientes().get(j);
                    lf.abrirArchivo();
                    Paciente paciente = lf.bucarPaciente(numeroPaciente);
                    lf.cerrarArchivo();
                    System.out.print("[" + paciente.getNumPaciente() + "-" + paciente.getNombreCompleto() + "-]");
                }
            }
        } catch (IOException e) {
        }

    }

    public Dentista buscarDentista(int numDentista) throws IOException {
        Dentista dentista = new Dentista();

        raf.seek((numDentista - 1) * TAM_REGISTRO);

        if (raf.getFilePointer() < raf.length()) {
            dentista.setNumero(raf.readInt());
            dentista.setNombre(raf.readUTF());
            dentista.setNumColegiado(raf.readUTF());

            int numPacientes = raf.readInt();

            ArrayList<Integer> pacientes = new ArrayList<>();
            for (int i = 0; i < numPacientes; i++) {
                pacientes.add(raf.readInt());
            }
            dentista.setPacientes(pacientes);

            dentista.setBaja(raf.readBoolean());

            return dentista;
        } else {
            return null;
        }
    }

    public boolean existeNumColegiado(String numColegiado) {
        int numRegistros = (int) Math.ceil((double) f.length() / TAM_REGISTRO);
        boolean esExistente = false;

        try {
            for (int i = 1; i <= numRegistros; i++) {
                Dentista dentista = buscarDentista(i);

                if (dentista != null && dentista.getNumColegiado().equals(numColegiado)) {
                    esExistente = true;
                }
            }
            return esExistente;

        } catch (IOException e) {
            return esExistente;
        }
    }

    public void addDentista(Dentista den) throws IOException {
        raf.seek((den.getNumero() - 1) * TAM_REGISTRO);
        raf.writeInt(den.getNumero());
        raf.writeUTF(den.getNombre());
        raf.writeUTF(den.getNumColegiado());
        raf.writeInt(den.getPacientes().size());

        for (int i = 0; i < den.getPacientes().size(); i++) {
            raf.writeInt(den.getPacientes().get(i));
        }

    }

    public int numRex() {
        try {
            return (int) Math.ceil(raf.length() / (TAM_REGISTRO * 1.0));
        } catch (IOException e) {
            return 0;
        }
    }

    public void darBajaDentista(int numero) {
        try {
            Dentista dentista = buscarDentista(numero);

            dentista.setBaja(true);

            addDentista(dentista);
            System.out.println("Se ha dado de baja al dentista: " + dentista.getNombre());
        } catch (IOException e) {
            System.out.println("Error al buscar el dentista.");
        }

    }

    public void darBajaPacienteDentista(int dentista, int paciente) {
        try {
            Dentista dentista1 = buscarDentista(dentista);

            try {
                dentista1.getPacientes().remove(paciente);
            } catch (Exception e) {
                System.out.println("El paciente no existe");
            }

            addDentista(dentista1);
            System.out.println("Se eliminado al paciente correctamente.");

        } catch (IOException e) {
            System.out.println("Error al buscar dentista");
        }

    }
}

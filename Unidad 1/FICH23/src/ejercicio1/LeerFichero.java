package ejercicio1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeerFichero extends Archivo {
    private ObjectInputStream ois;

    public LeerFichero(String f) {
        super(f);
    }

    @Override
    void abrirArchivo() {
        try {
            ois = new ObjectInputStream(new FileInputStream(f));
        } catch (IOException e) {
        }
    }

    @Override
    void cerrarArchivo() {
        if (ois != null) {
            try {
                ois.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo.");
            }
        }
    }

    public boolean dniExistente(String dni) {
        if (ois != null) {

            while (true) {
                try {
                    Paciente paciente = (Paciente) ois.readObject();
                    if (paciente.getDatosPaciente().getDni().equals(dni)) {
                        return true;
                    }
                } catch (IOException e) {
                    return false;
                } catch (ClassNotFoundException cnf) {
                    System.out.println("Clase no encontrada.");
                    return true;
                }
            }
        }
        return false;

    }

    public boolean numPacienteExistente(int num) {
        if (ois != null) {

            while (true) {
                try {
                    Paciente paciente = (Paciente) ois.readObject();
                    if (paciente.getNumPaciente() == num) {
                        return true;
                    }
                } catch (IOException e) {
                    return false;
                } catch (ClassNotFoundException cnf) {
                    System.out.println("Clase no encontrada.");
                    return true;
                }
            }
        }
        return false;

    }

    public int ultimoDorsal() {
        int ultimoDorsal = 0;
        if (ois != null) {
            while (true) {
                try {
                    Paciente paciente = (Paciente) ois.readObject();
                    ultimoDorsal = paciente.getNumPaciente();
                } catch (IOException e) {
                    return ultimoDorsal;
                } catch (ClassNotFoundException cnf) {
                    System.out.println("Clase no encontrada.");
                    return 0;
                }
            }
        }
        return ultimoDorsal;

    }

    public Paciente bucarPaciente(int numPaciente) {
        if (ois != null) {
            while (true) {
                try {
                    Paciente pacienteTmp = (Paciente) ois.readObject();
                    if (pacienteTmp.getNumPaciente() == numPaciente) {
                        return pacienteTmp;
                    }
                } catch (IOException e) {
                    return null;
                } catch (ClassNotFoundException cnf) {
                    System.out.println("Clase no encontrada.");
                    return null;
                }
            }
        }
        return null;
    }

}

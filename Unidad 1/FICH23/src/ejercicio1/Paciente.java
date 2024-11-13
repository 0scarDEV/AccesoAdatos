// Xabier Pastoriza Rodriguesz 53860349f
package ejercicio1;

import java.io.Serializable;
import java.util.ArrayList;

public class Paciente implements Serializable {
    static final long serialVersionUID = 120L;
    private int numPaciente;
    private Datos datosPaciente;
    private ArrayList<String> telefonos;

    public Paciente(Datos datosPaciente, ArrayList<String> telefonos) {
        this.datosPaciente = datosPaciente;
        this.telefonos = telefonos;
    }

    public Datos getDatosPaciente() {
        return datosPaciente;
    }

    public void setNumPaciente(int numPaciente) {
        this.numPaciente = numPaciente;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getNumPaciente() {
        return numPaciente;
    }

    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    public String getNombreCompleto() {
        return datosPaciente.getNombre() + " " + datosPaciente.getApellido1() + " " + datosPaciente.getApellido2();
    }

    @Override
    public String toString() {
        return "Nombre: " + datosPaciente.getNombre()
                + " " + datosPaciente.getApellido1() + " "
                + datosPaciente.getApellido2() + " DNI: " + datosPaciente.getDni() + "\nFecha Nacimiento: "
                + datosPaciente.getFechanacimiento();
    }

}

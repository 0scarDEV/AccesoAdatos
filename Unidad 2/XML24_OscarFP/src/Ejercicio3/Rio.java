package Ejercicio3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

/* Óscar Fernández Pastoriza - 53862191D */
public class Rio {
    private String codigo;
    private String nombre;
    private ArrayList<Medicion> mediciones;
    private double oxigenoTotal = 0;
    private double temperaturaTotal = 0;

    public Rio() {
        mediciones = new ArrayList<>();
    }

    @XmlAttribute(name = "codigo")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlAttribute(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "Medicion")
    public ArrayList<Medicion> getMediciones() {
        return mediciones;
    }

    public void setMediciones(ArrayList<Medicion> mediciones) {
        this.mediciones = mediciones;
    }

    @Override public String toString() {
        StringBuilder salida = new StringBuilder();

        salida.append("Rio: ").append(nombre).append(" (").append(codigo).append(")");

        for (Medicion medicion : mediciones) {
            salida.append("\n\t\t").append(medicion);
            oxigenoTotal += medicion.getOxigeno();
            temperaturaTotal += medicion.getTemperatura();
        }

        double mediaOxigeno = oxigenoTotal / mediciones.size();
        double mediaTemperatura = temperaturaTotal / mediciones.size();

        salida.append("\n---------------------------------------------------------------------------------------------------");
        salida.append("\t\t\t\t\t").append("\nMedia del oxígeno disuelto: ").append(mediaOxigeno).append(" mg/l  Media de la Temperatura: ")
                .append(mediaTemperatura).append("º");
        // No me acuerdo como se limitaba el número de decimales de un double :(

        return String.valueOf(salida);
    }

    public double getOxigenoTotal() {
        return oxigenoTotal;
    }

    public double getTemperaturaTotal() {
        return temperaturaTotal;
    }
}

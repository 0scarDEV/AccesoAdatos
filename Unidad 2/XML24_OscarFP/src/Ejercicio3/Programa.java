package Ejercicio3;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

/* Óscar Fernández Pastoriza - 53862191D */
@XmlRootElement(name = "Programa")
public class Programa {
    private String denominacion;
    private String pais;
    private ArrayList<Entidad> entidades;
    private ArrayList<Rio> rios;

    private double oxigenoTotal;
    private double temperaturaTotal;

    public Programa() {
        entidades = new ArrayList<>();
        rios = new ArrayList<>();
        oxigenoTotal = 0;
        temperaturaTotal = 0;
    }

    @XmlElementWrapper(name = "Entidades")
    @XmlElements({
            @XmlElement(name = "Laboratorio", type = Laboratorio.class),
            @XmlElement(name = "Universidad", type = Universidad.class),
            @XmlElement(name = "Instituto", type = Instituto.class)
    })
    public ArrayList<Entidad> getEntidades() {
        return entidades;
    }

    @XmlElementWrapper(name = "Rios")
    @XmlElement(name = "Rio")
    public ArrayList<Rio> getRios() {
        return rios;
    }

    @XmlAttribute(name = "denominacion")
    public String getDenominacion() {
        return denominacion;
    }

    @XmlAttribute(name = "pais")
    public String getPais() {
        return pais;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override public String toString() {
        StringBuilder salida = new StringBuilder();

        salida.append("Programa: ").append(denominacion).append("\t\tPais: ").append(pais).append("\n");
        salida.append("\nEntidades:");
        salida.append("\n------------------------------------------------------------------------------------------------------------------");

        for (Entidad entidad : entidades) {
            salida.append("\n\n").append(entidad);

        }

        salida.append("\nRIOS:");
        salida.append("\n------------------------------------------------------------------------------------------------------------------");

        for (Rio rio : rios) {
            salida.append("\n\n").append(rio);
            oxigenoTotal += rio.getOxigenoTotal();
            temperaturaTotal += rio.getTemperaturaTotal();
        }

        double mediaTotalOxigeno = oxigenoTotal / rios.size();
        double mediaTemperaturaTotal = temperaturaTotal / rios.size();

        salida.append("\n\n------------------------------------------------------------------------------------------------------------------");
        salida.append("\n\t\t\tMedia del oxígeno disuelto: ").append(mediaTotalOxigeno).append("mg/l  Media de la Temperatura Total: ")
                .append(mediaTemperaturaTotal).append("º");
        // No me acuerdo como se limitaba el número de decimales de un double :(

        return String.valueOf(salida);
    }
}

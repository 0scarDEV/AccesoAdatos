package EJ5_A4REPASOUD2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement (name = "Pesca")
public class Pesca {
    ArrayList<Especie> especies = new ArrayList<Especie>();
    ArrayList<Xornada> xornadas = new ArrayList<Xornada>();

    public Pesca() {
    }

    public Pesca(ArrayList<Especie> especies, ArrayList<Xornada> xornadas) {
        this.especies = especies;
        this.xornadas = xornadas;
    }

    @XmlElementWrapper(name = "Especies")
    @XmlElement(name = "Especie")
    public ArrayList<Especie> getEspecies() {
        return especies;
    }

    public void setEspecies(ArrayList<Especie> especies) {
        this.especies = especies;
    }

    @XmlElement (name = "Xornada")
    public ArrayList<Xornada> getXornadas() {
        return xornadas;
    }

    public void setXornadas(ArrayList<Xornada> xornadas) {
        this.xornadas = xornadas;
    }

    @Override public String toString() {
        StringBuilder salida = new StringBuilder();

        for (Especie especie : especies) {
            salida.append(especie).append("\n");
        }
        for (Xornada xornada : xornadas) {
            salida.append(xornada).append("\n");
        }

        return salida.toString();
    }
}

package EJ5_A4REPASOUD2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

public class Xornada {
    private String lugar;
    private String data;
    private ArrayList<Captura> capturas;
    private String descripcion;

    public Xornada() {
    }

    public Xornada(String lugar, String data, ArrayList<Captura> captura, String descripcion) {
        this.lugar = lugar;
        this.data = data;
        this.capturas = captura;
        this.descripcion = descripcion;
    }

    @XmlAttribute (name = "lugar")
    public String getLugar() {return lugar;}

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @XmlAttribute (name = "data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @XmlElement (name = "Captura")
    public ArrayList<Captura> getCapturas() {
        return capturas;
    }

    public void setCapturas(ArrayList<Captura> captura) {
        this.capturas = captura;
    }

    @XmlElement (name = "Descricion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override public String toString() {
        StringBuilder salida = new StringBuilder();
        salida.append("Xornada día: ").append(data).append(" (Lugar - ").append(lugar).append(")\n");

        if (descripcion == null) {
            salida.append("Descrición: Ninguna.\n");
        } else {
            salida.append("Descrición: ").append(descripcion).append("\n");
        }
        salida.append("\tCapturas:\n");

        if (capturas == null) {
            salida.append("\t\t-Ninguna.\n");
        } else {
            for (Captura captura : capturas) {
                salida.append("\t\t").append(captura).append("\n");
            }
        }

        return salida.toString();
    }
}

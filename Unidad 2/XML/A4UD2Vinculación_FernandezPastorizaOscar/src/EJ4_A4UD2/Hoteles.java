package EJ4_A4UD2;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;

@XmlRootElement(name = "CadenaHotelera")
@XmlType(propOrder = {"nombre", "cif", "hoteles"})
public class Hoteles implements Serializable {
    private String nombre;
    private String cif;
    private ArrayList<Hotel> hoteles = new ArrayList<>();

    public Hoteles() {
    }

    public Hoteles(String nombre, String cif, ArrayList<Hotel> hoteles) {
        this.nombre = nombre;
        this.cif = cif;
        this.hoteles = hoteles;
    }

    @XmlAttribute(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlAttribute(name = "CIF")
    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @XmlElement(name = "Hotel")
    public ArrayList<Hotel> getHoteles() {
        return hoteles;
    }

    public void setHoteles(ArrayList<Hotel> hoteles) {
        this.hoteles = hoteles;
    }
}

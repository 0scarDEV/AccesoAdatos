package EJ4_A4UD2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType (propOrder = {"nombre", "telefonos", "direccion"})
public class Hotel implements Serializable {
    private int codHotel;
    private String nombre;
    private String telefonos;
    private Direccion direccion;

    public Hotel() {
    }

    public Hotel(int codHotel, String nombre, String telefonos, Direccion direccion) {
        this.codHotel = codHotel;
        this.nombre = nombre;
        this.telefonos = telefonos;
        this.direccion = direccion;
    }

    @XmlAttribute(name = "id")
    public int getCodHotel() {
        return codHotel;
    }

    public void setCodHotel(int codHotel) {
        this.codHotel = codHotel;
    }

    @XmlElement(name = "Nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "Telefonos")
    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    @XmlElement(name = "Direccion")
    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}

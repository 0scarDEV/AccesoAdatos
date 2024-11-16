package EJ3_A4UD2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlSeeAlso({Estudiante.class, Trabajador.class})
@XmlType(name = "Persona", propOrder = {"nombre", "edad", "informacionContacto"})
public class Persona {
    private String nombre;
    private int edad;
    private InformacionContacto informacionContacto;

    @XmlElement(name = "Nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "Edad")
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @XmlElement (name = "Informacion_Contacto")
    public InformacionContacto getInformacionContacto() {
        return informacionContacto;
    }

    public void setInformacionContacto(InformacionContacto informacionContacto) {
        this.informacionContacto = informacionContacto;
    }

    public Persona() {
    }

    public Persona(String nombre, int edad, Direccion direccion, ArrayList<String> telefonos) {
        this.nombre = nombre;
        this.edad = edad;
        this.informacionContacto = new InformacionContacto(direccion, telefonos);
    }

    public static class InformacionContacto {
        private Direccion direccion;
        private String telefonos;

        public InformacionContacto() {}

        public InformacionContacto(Direccion direccion, ArrayList<String> telefonos) {
            this.direccion = direccion;
            this.telefonos = String.join(" ", telefonos);
        }

        @XmlElement(name = "Direccion")
        public Direccion getDireccion() {
            return direccion;
        }

        public void setDireccion(Direccion direccion) {
            this.direccion = direccion;
        }

        @XmlElement(name = "Telefonos")
        public String getTelefonos() {
            return telefonos;
        }

        public void setTelefonos(String telefonos) {
            this.telefonos = telefonos;
        }
    }
}

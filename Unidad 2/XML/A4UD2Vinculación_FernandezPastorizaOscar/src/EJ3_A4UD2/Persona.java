package EJ3_A4UD2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;

@XmlSeeAlso({Estudiante.class, Trabajador.class})
@XmlRootElement
public class Persona {
    @XmlElement(name = "Nombre")
    private String nombre;
    @XmlElement(name = "Edad")
    private int edad;
    @XmlElement (name = "Informacion_Contacto")
    private InformacionContacto informacionContacto;

    public Persona() {
    }

    public Persona(String nombre, int edad, Direccion direccion, ArrayList<String> telefonos) {
        this.nombre = nombre;
        this.edad = edad;
        this.informacionContacto = new InformacionContacto(direccion, telefonos);
    }

    public static class InformacionContacto {
        @XmlElement(name = "Direccion")
        private Direccion direccion;
        @XmlElement(name = "Telefonos")
        private String telefonos;

        public InformacionContacto() {}

        public InformacionContacto(Direccion direccion, ArrayList<String> telefonos) {
            this.direccion = direccion;
            this.telefonos = String.join(" ", telefonos);}
    }
}

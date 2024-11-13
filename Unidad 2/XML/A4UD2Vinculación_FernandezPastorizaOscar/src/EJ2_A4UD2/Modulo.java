package EJ2_A4UD2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "Modulo")
@XmlType(propOrder = {"nombre", "bloquesDeContenidos"})
public class Modulo {
    @XmlElement(name = "Nome")
    private String nombre;
    @XmlElementWrapper(name = "Contidos")
    @XmlElement(name = "Bloque")
    private ArrayList<BloqueContenidos> bloquesDeContenidos;

    public Modulo() {
        bloquesDeContenidos = new ArrayList<>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBloquesDeContenidos(ArrayList<BloqueContenidos> bloquesDeContenidos) {
        this.bloquesDeContenidos = bloquesDeContenidos;
    }

    @Override public String toString() {
        StringBuilder salida = new StringBuilder();
        salida.append(nombre).append('\n');

        for (BloqueContenidos bloqueContenido : bloquesDeContenidos) {
            salida.append(bloqueContenido).append('\n');
        }

        return salida.toString();
    }
}

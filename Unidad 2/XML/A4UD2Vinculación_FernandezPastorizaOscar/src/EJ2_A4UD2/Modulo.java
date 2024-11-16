package EJ2_A4UD2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "Modulo")
@XmlType(propOrder = {"nombre", "bloquesDeContenidos"})
public class Modulo {
    private String nombre;
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

    @XmlElement(name = "Nome")
    public String getNombre() {
        return nombre;
    }

    @XmlElementWrapper(name = "Contidos")
    @XmlElement(name = "Bloque")    public ArrayList<BloqueContenidos> getBloquesDeContenidos() {
        return bloquesDeContenidos;
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

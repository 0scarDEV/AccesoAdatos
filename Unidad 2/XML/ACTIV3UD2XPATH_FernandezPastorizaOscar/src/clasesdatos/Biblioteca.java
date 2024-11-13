package clasesdatos;

import java.util.List;

/**
 *
 * @author usuario
 */
public class Biblioteca {
    private String facultad;
    private String campus;
    private List<Seccion> secciones;

    public Biblioteca() {
    }

    public Biblioteca(String facultad, String campus, List<Seccion> secciones) {
        this.facultad = facultad;
        this.campus = campus;
        this.secciones = secciones;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }
     @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Facultad: ").append(facultad).append("\n");
        sb.append("Campus: ").append(campus).append("\n");
        sb.append("Secciones:\n");
        for (Seccion seccion : secciones) {
            sb.append(seccion.toString());
        }
        return sb.toString();
    }
}

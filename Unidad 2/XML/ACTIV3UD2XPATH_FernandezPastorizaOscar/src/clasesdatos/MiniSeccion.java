package clasesdatos;

public class MiniSeccion {
    private String nombre;
    private int numLibros;

    public MiniSeccion() {
    }

    public MiniSeccion(String nombre, int numLibros) {
        this.nombre = nombre;
        this.numLibros = numLibros;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumLibros() {
        return numLibros;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumLibros(int numLibros) {
        this.numLibros = numLibros;
    }

    @Override
    public String toString() {
        return "Sección: " + nombre + ", Número de libros: " + numLibros + "\n";
    }
}

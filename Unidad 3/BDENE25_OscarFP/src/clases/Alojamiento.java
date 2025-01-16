package clases;

/* Óscar Fernández Pastoriza - 52862191D */
public abstract class Alojamiento {
    String nombre;
    String direccion;
    String localidad;
    String telefono;
    double precioHabitacion;
    double camaExtra;
    int numHabitaciones;

    public Alojamiento(String nombre, String direccion, String localidad, String telefono, double precioHabitacion, double camaExtra, int numHabitaciones) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.telefono = telefono;
        this.precioHabitacion = precioHabitacion;
        this.camaExtra = camaExtra;
        this.numHabitaciones = numHabitaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getPrecioHabitacion() {
        return precioHabitacion;
    }

    public void setPrecioHabitacion(double precioHabitacion) {
        this.precioHabitacion = precioHabitacion;
    }

    public double getCamaExtra() {
        return camaExtra;
    }

    public void setCamaExtra(double camaExtra) {
        this.camaExtra = camaExtra;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }
}

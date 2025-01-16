package clases;

/* Óscar Fernández Pastoriza - 52862191D */
public class CasaRural extends Alojamiento {
    char alquilerCompleta;

    public CasaRural(String nombre, String direccion, String localidad, String telefono, double precioHabitacion, double camaExtra, int numHabitaciones, char alquilerCompleta) {
        super(nombre, direccion, localidad, telefono, precioHabitacion, camaExtra, numHabitaciones);
        this.alquilerCompleta = alquilerCompleta;
    }

    public char getAlquilerCompleta() {
        return alquilerCompleta;
    }

    public void setAlquilerCompleta(char alquilerCompleta) {
        this.alquilerCompleta = alquilerCompleta;
    }
}

package clases;

/* Óscar Fernández Pastoriza - 52862191D */
public class Hotel extends Alojamiento {
    int numEstrellas;
    int hotelSede;

    public Hotel(String nombre, String direccion, String localidad, String telefono, double precioHabitacion, double camaExtra, int numHabitaciones, int numEstrellas) {
        super(nombre, direccion, localidad, telefono, precioHabitacion, camaExtra, numHabitaciones);
        this.numEstrellas = numEstrellas;
    }

    public Hotel(String nombre, String direccion, String localidad, String telefono, double precioHabitacion, double camaExtra, int numHabitaciones, int numEstrellas, int hotelSede) {
        super(nombre, direccion, localidad, telefono, precioHabitacion, camaExtra, numHabitaciones);
        this.numEstrellas = numEstrellas;
        this.hotelSede = hotelSede;
    }

    public int getNumEstrellas() {
        return numEstrellas;
    }

    public void setNumEstrellas(int numEstrellas) {
        this.numEstrellas = numEstrellas;
    }

    public int getHotelSede() {
        return hotelSede;
    }

    public void setHotelSede(int hotelSede) {
        this.hotelSede = hotelSede;
    }
}

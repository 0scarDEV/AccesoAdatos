package clases;

/* Óscar Fernández Pastoriza - 52862191D */
public class HotelSpa extends Hotel {
    char gorro;
    int capacidad;

    public HotelSpa(String nombre, String direccion, String localidad, String telefono, double precioHabitacion, double camaExtra, int  numHabitaciones, int numEstrellas, int hotelSede, char gorro, int capacidad) {
        super(nombre, direccion, localidad, telefono, precioHabitacion, camaExtra, numHabitaciones, numEstrellas, hotelSede);
        this.gorro = gorro;
        this.capacidad = capacidad;
    }

    public char getGorro() {
        return gorro;
    }

    public void setGorro(char gorro) {
        this.gorro = gorro;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}

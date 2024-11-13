package EJ4_A1UD2;

import java.io.Serializable;
import java.time.LocalDate;

public class Hotel implements Serializable {
    int codHotel;
    String nombre;
    LocalDate fechaInauguracion;
    String telefono;
    Direccion direccion;

    public Hotel(int codHotel, String nombre, LocalDate fechaInauguracion, String telefono, Direccion direccion) {
        this.codHotel = codHotel;
        this.nombre = nombre;
        this.fechaInauguracion = fechaInauguracion;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getCodHotel() {
        return codHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
    public Direccion getDireccion() {
        return direccion;
    }
}

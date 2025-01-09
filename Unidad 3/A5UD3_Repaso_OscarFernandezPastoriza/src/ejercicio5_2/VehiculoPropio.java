package ejercicio5_2;

import java.sql.Date;

public class VehiculoPropio extends Vehiculo {
    Date fechaCompra;
    double precioCompra;

    public VehiculoPropio(String matricula, String marca, String modelo, char combustible, Date fechaCompra, double precioCompra) {
        super(matricula, marca, modelo, combustible);
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
}

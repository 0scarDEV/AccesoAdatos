package ejercicio5_2;

import java.sql.Date;

public class VehiculoRenting extends Vehiculo {
    Date fechaInicio;
    double precioMensual;
    int meses;

    public VehiculoRenting(String matricula, String marca, String modelo, char combustible, Date fechaInicio, double precioMensual, int meses) {
        super(matricula, marca, modelo, combustible);
        this.fechaInicio = fechaInicio;
        this.precioMensual = precioMensual;
        this.meses = meses;
    }

    public java.sql.Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public double getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(double precioMensual) {
        this.precioMensual = precioMensual;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }
}

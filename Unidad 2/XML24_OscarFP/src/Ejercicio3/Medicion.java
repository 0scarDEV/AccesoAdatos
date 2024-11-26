package Ejercicio3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

/* Óscar Fernández Pastoriza - 53862191D */
public class Medicion {
    private static final String formato = "%-20s%-20s%-20s%-20s%-20s";
    private String fecha;
    private String calidad;
    private PH ph;
    private double oxigeno;
    private double temperatura;


    public Medicion() {
    }

    @XmlAttribute(name = "fecha")
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @XmlAttribute(name = "calidad")
    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    @XmlElement(name = "pH")
    public PH getPh() {
        return ph;
    }

    public void setPh(PH ph) {
        this.ph = ph;
    }

    @XmlElement(name = "Oxigeno")
    public double getOxigeno() {
        return oxigeno;
    }

    public void setOxigeno(double oxigeno) {
        this.oxigeno = oxigeno;
    }

    @XmlElement(name = "Temperatura")
    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public static class PH {
        private String tipo;
        private double valor;

        public PH() {
        }

        @XmlAttribute(name = "tipo")
        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        @XmlValue
        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }

        @Override public String toString() {
            return valor + "\t(" + tipo + ")";
        }
    }


    @Override public String toString() {
        return String.format(formato, "Fecha: " + fecha,"calidad: " + calidad, "Ph: " + ph , "Oxigeno: " + oxigeno + " mg/l" , "Temperatura: " + temperatura + "º");
    }
}
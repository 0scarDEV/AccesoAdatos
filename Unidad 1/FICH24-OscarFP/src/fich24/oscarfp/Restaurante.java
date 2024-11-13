package fich24.oscarfp;

import java.util.ArrayList;

public class Restaurante {
    private int numero; // Código único
    private String nombre; // Nombre único
    private String localidad;
    private int numCocineros;
    private ArrayList<InfoCocinero> cocineros;
    private boolean baja;

    public Restaurante(String nombre, String localidad, ArrayList<InfoCocinero> cocineros, boolean baja) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.numCocineros = cocineros.size();
        this.cocineros = cocineros;
        this.baja = baja;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public ArrayList<InfoCocinero> getInfoCocineros() {
        return cocineros;
    }

    public int getNumero() {
        return numero;
    }

    public int getNumCocineros() {
        return numCocineros;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setNumCocineros(int numCocineros) {
        this.numCocineros = numCocineros;
    }

    public void borrarCocinero(int codCocinero) {
        // Operamos con listaInfoCocineros
        System.out.println("Eliminando el cocinero de la lista...");
        int posCocineroEliminar = 0;

        // Buscamos el index del cocinero que queremos eliminar
        for (int i = 0; i < cocineros.size(); i++) {
            if (cocineros.get(i).getNumero() == codCocinero) {
                posCocineroEliminar = i;
            }
        }

        // Borramos ese cocinero
        cocineros.remove(posCocineroEliminar);

        // Reescribimos el restaurante
        System.out.println("Reescribiendo el restaurante sin el cocinero seleccionado...");
        setNumCocineros(getNumCocineros() - 1);
    }

    @Override public String toString() {
        String string = "-----------------------------------------------------------" +
                "\nRestaurante código: " + numero +
                "\n----------------------------------------" +
                "\nNombre: " + nombre + " (" + localidad + ")" +
                "\nCocineros: " + cocineros.toString();

        return string;
    }

    public void setCocineros(ArrayList<InfoCocinero> infoCocineros) {
        cocineros = infoCocineros;
    }
}

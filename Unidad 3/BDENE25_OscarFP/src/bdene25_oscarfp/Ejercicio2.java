package bdene25_oscarfp;

import bbdd.Conexion;
import bbdd.OperacionesAlojamientos;
import clases.CasaRural;
import clases.Hotel;
import clases.HotelSpa;

/* Óscar Fernández Pastoriza - 52862191D */
public class Ejercicio2 {
    public static void main(String[] args) {
        OperacionesAlojamientos opAlojamientos = new OperacionesAlojamientos(new Conexion("jdbc:sqlserver://localhost\\MSQLSERVER:50043;database=BDALOJAMIENTOS25;trustServerCertificate=true", "sa","abc123."));

        // Casa rural con datos correctos
        CasaRural casaRural = new CasaRural("A pradeira","Rúa ollomol, 2","A Coruña","981233288",58.00,20.00,6, 'N');
        opAlojamientos.darBajaALojamiento(casaRural);

        // Hotel rural y hotel sede con datos correctos
        Hotel hotel = new Hotel("A solaina","Rúa alberca, 14","Pontevedra","986245242",74.50,23.00,15, 3,10);
        opAlojamientos.darBajaALojamiento(hotel);

        // Hotel rural con spa y hotel sede con datos correctos
        HotelSpa hotelSpa = new HotelSpa("Lucus Augusti","Plaza del sol muriente, 2","Lugo","982882882",104.80,30.00,15, 3,10, 'N', 20);
        opAlojamientos.darBajaALojamiento(hotelSpa);

        // No existe esta
        CasaRural noExiste = new CasaRural("NO EXISTEEE","Rúa ollomol, 2","A Coruña","981233288",58.00,20.00,6, 'N');
        opAlojamientos.darBajaALojamiento(noExiste);
    }
}

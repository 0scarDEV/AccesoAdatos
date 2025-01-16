package bdene25_oscarfp;

import bbdd.Conexion;
import bbdd.OperacionesAlojamientos;
import clases.CasaRural;
import clases.Hotel;
import clases.HotelSpa;

import java.sql.SQLException;

/* Óscar Fernández Pastoriza - 52862191D */

public class Ejercicio1 {
    public static void main(String[] args) throws SQLException {
        OperacionesAlojamientos opAlojamientos = new OperacionesAlojamientos(new Conexion("jdbc:sqlserver://localhost\\MSQLSERVER:50043;database=BDALOJAMIENTOS25;trustServerCertificate=true", "sa", "abc123."));

        // Casa rural con datos correctos
        CasaRural casaRural = new CasaRural("O bo pastor","Rúa ollomol, 2","A Coruña","981233288",58.00,20.00,6, 'N');
        opAlojamientos.insertarAlojamiento(casaRural);

        // Hotel rural y hotel sede con datos correctos
        Hotel hotel = new Hotel("Muralla Griega","Plaza Viento del Este, 15","Lugo","982982982",178.80,32.00,20, 2, 10);
        opAlojamientos.insertarAlojamiento(hotel);

        // Hotel rural con spa y hotel sede con datos correctos
        HotelSpa hotelSpa = new HotelSpa("Lucus Setembro","Plaza del sol muriente, 2","Lugo","982882882",104.80,30.00,15, 3,10, 'N', 20);
        opAlojamientos.insertarAlojamiento(hotelSpa);

        // Hotel rural con nombre repetido
        Hotel hotelRepe = new Hotel("Muralla Romana","Plaza Viento del Este, 15","Lugo","982982982",178.80,32.00,20, 2, 10);
        opAlojamientos.insertarAlojamiento(hotelRepe);

        // Hotel rural con spa y hotel sede no existente
        Hotel hotelSedeMal = new Hotel("Muralla China","Plaza Viento del Este, 15","Lugo","982982982",178.80,32.00,20, 2, 7);
        opAlojamientos.insertarAlojamiento(hotelSedeMal);

    }
}

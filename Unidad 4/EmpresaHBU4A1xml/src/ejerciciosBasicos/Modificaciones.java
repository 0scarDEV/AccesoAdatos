package ejerciciosBasicos;

import pojos.Telefono;
import utiles.OperacionesHBtelefono;

import java.util.HashSet;

public class Modificaciones {
    public static void main(String[] args) {
        OperacionesHBtelefono opHb = new OperacionesHBtelefono();
        //System.out.println(opHb.modificarSalarioEmpleado("12345678B", 2000));

        HashSet<Telefono> telefonos = new HashSet<>();
        telefonos.add(new Telefono("123456789"));
        telefonos.add(new Telefono("123456788"));
        opHb.setTelefonosEmpleado(opHb.getSession(),"12345678A",  telefonos);

        opHb.removeTelefonoEmpleado(opHb.getSession(), "12345678A", new Telefono("123456788"));

        opHb.liberarRecursos();
        System.exit(0);
    }
}

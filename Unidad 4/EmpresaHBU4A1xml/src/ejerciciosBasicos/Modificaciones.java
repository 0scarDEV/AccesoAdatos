package ejerciciosBasicos;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Telefono;
import utiles.OperacionesHBtelefono;

import java.util.HashSet;

public class Modificaciones {
    public static void main(String[] args) {
        OperacionesHBtelefono opHb = new OperacionesHBtelefono();
        Session s = opHb.getSession();
        Transaction t = s.beginTransaction();

        try {
            //System.out.println(opHb.modificarSalarioEmpleado("12345678B", 2000));

            HashSet<Telefono> telefonos = new HashSet<>();
            telefonos.add(new Telefono("123456789"));
            telefonos.add(new Telefono("123456788"));
            opHb.setTelefonosEmpleado(s, "12345678A", telefonos);

            opHb.removeTelefonoEmpleado(s, "12345678A", new Telefono("123456788"));
            t.commit();
        } catch (HibernateException e) {
            t.rollback();
            System.out.println("Error al modificar empleado");
        }


        opHb.liberarRecursos();
        System.exit(0);
    }
}

package ejercicios;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Telefono;
import utiles.OperacionesHBtelefono;

import java.util.HashSet;
import java.util.Set;

public class Modificaciones {
    public static void main(String[] args) {
        OperacionesHBtelefono opHb = new OperacionesHBtelefono();
        Session s = opHb.openSession();
        Transaction t = s.beginTransaction();

        try {
            System.out.println(opHb.modificarSalarioEmpleado(s,"12345678A", 2000));
            HashSet<Telefono> telefonos = new HashSet<>();
            telefonos.add(new Telefono("666666666"));
            telefonos.add(new Telefono("666666667"));
            opHb.setTelefonosEmpleado(s, "12345678A", telefonos);
            opHb.removeTelefonoEmpleado(s, "12345678A", new Telefono("666666666"));
            t.commit();
        } catch (HibernateException e) {
            System.out.println("Error al modificar: " + e.getMessage());
            t.rollback();
        }

        opHb.liberarRecursos();
        System.exit(0);
    }
}

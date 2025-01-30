package utiles;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Empregado;
import pojos.Telefono;

import java.util.HashSet;

public class OperacionesHBtelefono extends OperacionesHB {
    public OperacionesHBtelefono() {
        super();
    }

    public boolean setTelefonosEmpleado(Session s, String nss, HashSet<Telefono> telefonos) {
        s = getSession();
        boolean flagModificacion = false;

        Transaction t = s.beginTransaction();
        try {
            Empregado e = (Empregado) s.get(Empregado.class, nss);

            if (e != null) {
                e.setTelefonos(telefonos);
                t.commit();
                System.out.println(e);
                flagModificacion = true;
            }
        } catch (HibernateException he) {
            t.rollback();
            System.out.println("Error al establecer los teléfonos de los empleados");
        }

        s.close();

        return flagModificacion;
    }

    public boolean removeTelefonoEmpleado(Session s, String nss, Telefono telefono) {
        s = getSession();
        Transaction t = s.beginTransaction();
        boolean flagModificacion = false;

        try {
            Empregado e = (Empregado) s.get(Empregado.class, nss);
            e.getTelefonos().remove(telefono);
            System.out.println(e);
            t.commit();
            flagModificacion = true;
        } catch (HibernateException he) {
            t.rollback();
        }

        s.close();

        return flagModificacion;
    }
}

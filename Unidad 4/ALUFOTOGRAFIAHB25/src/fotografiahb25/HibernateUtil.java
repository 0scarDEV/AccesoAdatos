
package fotografiahb25;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/*  Óscar Fernández Pastoriza -- 53862191D   */

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static StandardServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration().configure(); // Cargar configuración
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry); // Usar la misma configuración para construir la SessionFactory
        } catch (HibernateException ex) {
            // Log the exception.
            System.err.println("Fallo al crear la conexión" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

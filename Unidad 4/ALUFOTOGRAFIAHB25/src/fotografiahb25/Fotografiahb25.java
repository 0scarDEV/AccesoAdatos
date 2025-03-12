
package fotografiahb25;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Contacto;
import pojos.Evento;
import pojos.Fotografo;
import pojos.Licencia;

import java.util.*;

/*  Óscar Fernández Pastoriza -- 53862191D   */

public class Fotografiahb25 {
    private static Session session;

    public static void main(String[] args) {
        // Ejercicio 1. Gestión de Fotografías
        mostrarListaFotografos();

        // Ejercicio 2. Gestión de Materiales de Fotografía
        eliminarMaterialFotografo("TRI-3001");

        // Ejercicio 3. Gestión de Eventos Fotográficos y Asistencia
        setFotografoToEvento("Carlios", 14);
/*
        // Ejercicio 4. Gestión de Fotógrafos, Licencias e Idiomas
        Map<String, String> idiomas = new HashMap<>();
        idiomas.put("Inglés", "B2");
        idiomas.put("Francés", "B1");

        // Alta correcta sin influencer.
        darAltaFotografo("Oscar", "Fernandez Pastoriza", "Oski", "Bueu",
                "ofernpast@ieschandomonte.edu.gal", "986123123", "123121212", null,
                "LIC-2125", new Date(2020, Calendar.JULY, 17), new Date(2025, Calendar.JULY, 17), idiomas
        );

        // Alta incorrecta por seudónimo duplicado.
        darAltaFotografo("Oscar", "Fernandez Pastoriza", "Jorgito", "Bueu",
                "ofernpast@ieschandomonte.edu.gal", "986123123", "123121212", null,
                "LIC-2125", new Date(2020, Calendar.JULY, 17), new Date(2025, Calendar.JULY, 17), idiomas
        );

        // Alta correcta con influencer existente.
        darAltaFotografo("Oscar", "Fernandez Pastoriza", "Oski2", "Bueu",
                "ofernpast@ieschandomonte.edu.gal", "986123123", "123121212", "Jorgito",
                "LIC-2328", new Date(2020, Calendar.JULY, 17), new Date(2025, Calendar.JULY, 17), idiomas
        );

        // Alta incorrecta por influencer inexistente.
        darAltaFotografo("Oscar", "Fernandez Pastoriza", "Oski3", "Bueu",
                "ofernpast@ieschandomonte.edu.gal", "986123123", "123121212", "incorrecto",
                "LIC-2328", new Date(2020, Calendar.JULY, 17), new Date(2025, Calendar.JULY, 17), idiomas
        );
*/
        System.exit(0);
    }

    public static void iniciarSesion() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static void cerrarSesion() {
        session.close();
    }

    public static void mostrarListaFotografos() {
        iniciarSesion();
        Query query = session.createQuery(
                "select f.idFotografo, concat(f.nombre,' ',f.apellidos), count(fotos) from Fotografo f left join f.fotografias fotos" +
                        " GROUP BY f.idFotografo, f.nombre, f.apellidos ORDER BY count(fotos) desc, f.apellidos desc, f.nombre desc");
        List<Object[]> resultados = query.list();
        for (Object[] fila : resultados) {
            int id = (int) fila[0];
            String nombre = (String) fila[1];
            long numFotografias = (long) fila[2];
            System.out.printf("%-7s%-30s%-5s%n", id, nombre, numFotografias);
        }
        cerrarSesion();
    }

    public static void eliminarMaterialFotografo(String numSerieEquipo) {
        iniciarSesion();

        Query query = session.createQuery("select f, m.numeroSerie," +
                " concat(m.material,' ', m.marca,' ',m.modelo) as strMaterial " +
                " from Fotografo f left join f.materiales m WHERE m.numeroSerie = :numSerieEquipo");
        query.setParameter("numSerieEquipo", numSerieEquipo);

        List<Object[]> resultados = query.list();

        if (!resultados.isEmpty()) {
            Transaction tx = session.beginTransaction();

            // Como el numero de serie es único, la consulta solo nos puede arrojar un resultado.
            Object[] fila = resultados.get(0);

            // Recorremos los resultados
            Fotografo fotografo = (Fotografo) fila[0];
            String numSerie = (String) fila[1];
            String material = (String) fila[2];
            System.out.println(
                    "Fotógrafo: " + fotografo.getIdFotografo() + " - " + fotografo.getNombre() + " " + fotografo.getApellidos() +
                            "\nEquipo a eliminar: " + numSerie + " - " + material
            );

            // Eliminamos del arrya los materiales cuyo número de serie coincida con nuestro número de serie.
            fotografo.getMateriales().removeIf(m -> m.getNumeroSerie().equals(numSerieEquipo));

            // Como el fotógrafo está en persistencia, no tener que guardar la sesión, solo comiteamos la transacción.
            tx.commit();
        } else {
            System.out.println("El equipo con número de serie " + numSerieEquipo + " no existe.");
        }

        cerrarSesion();
    }

    public static void setFotografoToEvento(String seudonimo, int idEvento) {
        iniciarSesion();

        // Obtenemos el fotógrafo a través de su seudónimo
        Query query = session.createQuery("From Fotografo where seudonimo=:seudonimo");
        query.setParameter("seudonimo", seudonimo);
        Fotografo fotografo = (Fotografo) query.uniqueResult();

        query = session.createQuery("From Evento where idEvento=:idEvento");
        query.setParameter("idEvento", idEvento);
        Evento evento = (Evento) query.uniqueResult();

        // Prueba para comprobar la primera rama del if, en la que el fotógrafo ya está en el evento. DESCOMENTAR PARA PROBAR
        //evento.getFotografosAsistentes().add(fotografo);

        // Guardamos el evento
        Transaction tx = session.beginTransaction();

        session.save(evento);

        // ¿El fotógrafo ya está en el evento?
        if (evento.getFotografosAsistentes().contains(fotografo) || fotografo.getEventosAsistidos().contains(evento)) {
            System.out.println("El fotógrafo ya está inscrito/registrado en este evento");
        } else {
            fotografo.getEventosAsistidos().add(evento);
            evento.getFotografosAsistentes().add(fotografo);
            System.out.println("El fotógrafo ha sido inscrito al evento");
        }

        tx.commit();

        cerrarSesion();
    }

    public static void darAltaFotografo(String nombre, String apellidos, String seudonimo, String direccion, String email, String telefonoFijo,
                                        String telefonoMovil, String seudonimoInfluencer, String numeroLicencia, Date fechaExpedicion,
                                        Date fechaVencimiento, Map<String, String> idiomas) {
        iniciarSesion();
        Transaction tx = session.beginTransaction();

        // CONTROL DE ERRORES ¿El seudónimo ya está en uso?
        Query query = session.createQuery("From Fotografo where seudonimo=:seudonimo");
        query.setParameter("seudonimo", seudonimo);
        Fotografo fotografo = (Fotografo) query.uniqueResult();
        if (fotografo != null) {
            System.out.println(
                    "ERROR. El seudónimo " + seudonimo + " ya está en uso por otro fotógrafo. Dos fotógrafos no pueden tener el mismo seudónimo");
            cerrarSesion();
            return;
        }

        // Conseguimos el fotógrafo influencer si está especificado
        Fotografo influencer = null;
        if (seudonimoInfluencer != null) {
            query = session.createQuery("From Fotografo where seudonimo=:seudonimo");
            query.setParameter("seudonimo", seudonimoInfluencer);
            influencer = (Fotografo) query.uniqueResult();

            // CONTROL DE ERRORES ¿Existe el influencer?
            if (influencer == null) {
                System.out.println("ERROR. El influencer con seudónimo " + seudonimoInfluencer + " no existe.");
                cerrarSesion();
                return;
            }
        }

        // Creamos el fotógrafo (incluyendo su contacto)
        fotografo = new Fotografo(nombre, apellidos, seudonimo, new Contacto(direccion, email, telefonoFijo, telefonoMovil));

        if (influencer != null) {
            // Asignamos al influencer el fotógrafo como influenciado y al influenciado le asignamos su influencer
            influencer.getInfluenciados().add(fotografo);
            fotografo.setInfluencer(influencer);
        }

        // Construimos la licencia
        Licencia licencia = new Licencia();
        licencia.setIdFotografo(fotografo.getIdFotografo());
        licencia.setNumeroLicencia(numeroLicencia);
        licencia.setFechaExpedicion(fechaExpedicion);
        licencia.setFechaVencimiento(fechaVencimiento);
        licencia.setFotografo(fotografo);
        // Le asignamos al fotógrafo su licencia. Al estar el fotógrafo en persistencia, no hace falta guardar.
        fotografo.setLicencia(licencia);

        // Le asignamos al fotógrafo su colección de idiomas. Al estar el fotógrafo en persistencia, no hace falta guardar.
        fotografo.getIdiomas().putAll(idiomas);

        session.save(fotografo);
        tx.commit();
        cerrarSesion();
    }
}

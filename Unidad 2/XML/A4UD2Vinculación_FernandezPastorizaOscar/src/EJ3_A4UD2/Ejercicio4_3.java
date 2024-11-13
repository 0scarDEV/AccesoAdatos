package EJ3_A4UD2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;

public class Ejercicio4_3 {
    public static void main(String[] args) {
        Direccion de1 = new Direccion("Luna", 6, "Pontevedra", "Pontevedra");
        ArrayList<String> telefonosE1 = new ArrayList<>();
        telefonosE1.add("222222222");
        telefonosE1.add("222221199");
        telefonosE1.add("222221111");
        Estudiante e1 = new Estudiante("Angel Rodríguez", 23, de1, telefonosE1, "Santiago", "Informática");

        Direccion dt1 = new Direccion("Sol", 3, "Marín", "Pontevedra");
        ArrayList<String> telefonosT1 = new ArrayList<>();
        telefonosT1.add("111111222");
        telefonosT1.add("111111111");
        Trabajador t1 = new Trabajador("Carlos Alba", 44, dt1, telefonosT1, "La Colmena", 8000.0, "Director");

        Direccion dt2 = new Direccion("Nube", 16, "Pontevedra", "Pontevedra");
        ArrayList<String> telefonosT2 = new ArrayList<>();
        telefonosT2.add("333333333");
        telefonosT2.add("333333111");
        Trabajador t2 = new Trabajador("Manuel Sánchez", 45, dt2, telefonosT2, "El tornillo", 2000.0, "Gerente");

        Personas personas = new Personas();
        personas.getPersonas().add(e1);
        personas.getPersonas().add(t1);
        personas.getPersonas().add(t2);

        try {
            Marshaller marshaller = JAXBContext.newInstance(Personas.class).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(personas, System.out);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
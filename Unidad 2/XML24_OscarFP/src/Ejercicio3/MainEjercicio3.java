package Ejercicio3;

/* Óscar Fernández Pastoriza - 53862191D */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MainEjercicio3 {
    public static void main(String[] args) {
        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance(Programa.class).createUnmarshaller();
            Programa programa = (Programa) unmarshaller.unmarshal(new File("medicionesRios.xml"));
            System.out.println(programa);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}

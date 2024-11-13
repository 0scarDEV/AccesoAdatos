package EJ2_A4UD2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Ejercicio4_2 {
    public static void main(String[] args) {
        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance(Modulo.class).createUnmarshaller();
            Modulo modulo = (Modulo) unmarshaller.unmarshal(new File("src/EJ2_A4UD2/modulo.xml"));
            System.out.println(modulo);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
package EJ5_A4REPASOUD2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio5 {
    public static void main(String[] args) {
        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance(Pesca.class).createUnmarshaller();
            Pesca pesca = (Pesca) unmarshaller.unmarshal(new File("src/EJ5_A4REPASOUD2/Pesca.xml"));
            System.out.println(pesca);
            escribirArchivoTXT("src/EJ5_A4REPASOUD2/Pesca.txt", pesca.toString());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean escribirArchivoTXT(String ruta, String contenido) {
        File file = new File(ruta);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, true))) {
            out.write(contenido);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}

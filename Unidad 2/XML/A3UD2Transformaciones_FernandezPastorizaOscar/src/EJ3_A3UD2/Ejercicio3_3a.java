package EJ3_A3UD2;

import org.xml.sax.InputSource;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio3_3a {
    public static void main(String[] args) {
        String xsl = "src/EJ3_A3UD2/Alumnos.xsl";
        String alumnos = "src/EJ3_A3UD2/Alumnos.xml";
        String notas = "src/EJ3_A3UD2/Notas.xml";

        TransformerFactory transFact;
        transFact = TransformerFactory.newInstance();

        // Generamos Notas.xml
        try {
            Transformer trans;
            SAXSource sourceXsl = new SAXSource(new InputSource(xsl));
            SAXSource sourceAlumnos = new SAXSource(new InputSource(alumnos));
            trans = transFact.newTransformer(sourceXsl);

            // escribimos el arbol en el fichero notas.xml
            StreamResult sr = new StreamResult();
            sr.setWriter(new FileWriter(notas));
            trans.transform(sourceAlumnos, sr);
        } catch (TransformerFactoryConfigurationError e) {
            System.out.println("ERROR a la hora de implementar la transformación");
        } catch (TransformerException | IOException e) {
            throw new RuntimeException(e);
        }

        // Generamos la salida por consola
        NotasHandler manejador = new NotasHandler();
        try {
            Transformer transformer = transFact.newTransformer();
            SAXSource sourceNotas = new SAXSource(new InputSource(notas));
            SAXResult result = new SAXResult(manejador);
            transformer.transform(sourceNotas, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}

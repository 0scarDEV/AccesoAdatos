package EJ2_A2UD2;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class EJ2_A2UD2 {
    private static final String RUTA_ARCHIVO_MAREAS = "mareas.xml";

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        SAXParser saxParser;
        ManejadorMareas manejador = new ManejadorMareas();

        try {
            saxParser = factory.newSAXParser();
            saxParser.parse(RUTA_ARCHIVO_MAREAS, manejador);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder descDoc = manejador.getStringBuilder();
        System.out.println(descDoc);
    }
}

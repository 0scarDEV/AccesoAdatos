package Comunes;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/* Óscar Fernández Pastoriza - 53862191D */

public class OperacionesSAX {
    private static final String val="http://xml.org/sax/features/validation";
    private static final String namespaces="http://xml.org/sax/features/namespaces";
    private SAXParser parser;

    public OperacionesSAX() throws ParserConfigurationException, SAXException {
        SAXParserFactory factoria = SAXParserFactory.newInstance();
        factoria.setNamespaceAware(true);
        this.parser = factoria.newSAXParser();;
    }

    public void parseValidatingBySchema(DefaultHandler manejador, String ruta) {
        String esquemas="http://apache.org/xml/features/validation/schema";

        try {
            XMLReader procesador = parser.getXMLReader();

            procesador.setFeature(val, true);
            procesador.setFeature(esquemas, true);
            procesador.setFeature(namespaces,true);

            procesador.setContentHandler(manejador);

            procesador.parse(new InputSource(new FileInputStream(ruta)));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

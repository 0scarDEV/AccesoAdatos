package javalib;

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

public class OperacionesSAX {
    private static final String val="http://xml.org/sax/features/validation";
    private static final String namespaces="http://xml.org/sax/features/namespaces";

    public static SAXParser getNewSaxParser() throws ParserConfigurationException, SAXException {
        return SAXParserFactory.newInstance().newSAXParser();
    }

    public static void parse(SAXParser saxParser, DefaultHandler manejador, String rutaArchivo) {
        try {
            saxParser.parse(rutaArchivo, manejador);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void parseValidatingByDTD(DefaultHandler manejador, String ruta) {
        SAXParser parser;
        try {
            parser = getNewSaxParser();
            XMLReader procesador = parser.getXMLReader();

            procesador.setFeature(val, true);
            procesador.setFeature(namespaces,true);

            procesador.setContentHandler(manejador);

            procesador.parse(new InputSource(new FileInputStream(ruta)));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void parseValidatingBySchema(DefaultHandler manejador, String ruta) {
        String esquemas="http://apache.org/xml/features/validation/schema";

        SAXParser parser;
        try {
            parser = getNewSaxParser();
            XMLReader procesador = parser.getXMLReader();

            procesador.setFeature(val, true);
            procesador.setFeature(esquemas, true);
            procesador.setFeature(namespaces,true);

            procesador.setContentHandler(manejador);

            procesador.parse(new InputSource(new FileInputStream(ruta)));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void parseByURL(SAXParser saxParser, DefaultHandler manejador, String ruta) throws IOException, SAXException, URISyntaxException {
        saxParser.parse(new URI(ruta).toURL().openStream(), manejador);
    }
}

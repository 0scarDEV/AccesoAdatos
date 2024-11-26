package lunas;

import javalib.OperacionesSAX;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import java.io.IOException;
import java.net.URISyntaxException;

public class Ejercicio {
    public static void main(String[] args) {
        ManejadorLunas manejador = new ManejadorLunas("src/lunas/FasesLunas.xml");

        try {
            SAXParser saxParser = OperacionesSAX.getNewSaxParser();
            OperacionesSAX.parseByURL(saxParser, manejador, "https://servizos.meteogalicia.gal/mgrss/predicion/rssLuas.action?request_locale=gl");
        } catch (ParserConfigurationException | SAXException | IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

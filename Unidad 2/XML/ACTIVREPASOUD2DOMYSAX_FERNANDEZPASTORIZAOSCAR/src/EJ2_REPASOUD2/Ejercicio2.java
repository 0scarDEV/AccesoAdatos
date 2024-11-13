package EJ2_REPASOUD2;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static Comunes.ComunesDOM.grabarNuevoArbol;

public class Ejercicio2 {
    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        SAXParser saxParser;

        try {
            saxParser = factory.newSAXParser();
            ManejadorLunas manejador = new ManejadorLunas();
            saxParser.parse(new URI("https://servizos.meteogalicia.gal/mgrss/predicion/rssLuas.action?request_locale=gl").toURL().openStream(), manejador);

            grabarNuevoArbol(manejador.getDocument(), "FasesLunas.xml");
        } catch (ParserConfigurationException | SAXException | IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

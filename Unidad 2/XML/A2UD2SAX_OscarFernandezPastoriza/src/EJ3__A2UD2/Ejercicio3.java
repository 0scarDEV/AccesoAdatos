package EJ3__A2UD2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Ejercicio3 {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            URL url = new URL("https://feeds.elpais.com/mrss-s/pages/ep/site/elpais.com/portada");

            saxParser.parse(url.openStream(), new NoticiasHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class NoticiasHandler extends DefaultHandler {
    private StringBuilder currentValue = new StringBuilder();
    private List<String> categorias = new ArrayList<>();
    private String titulo, autor, descripcion, fechaPublicacion;
    private boolean dentroItem = false;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentValue.setLength(0);  // Limpia el valor actual

        if (qName.equalsIgnoreCase("item")) {
            dentroItem = true;
            categorias.clear();
        } else if (qName.equalsIgnoreCase("category") && dentroItem) {
            categorias.add(""); // Prepara una entrada para cada categoría
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (dentroItem) {
            switch (qName) {
                case "title":
                    titulo = currentValue.toString();
                    break;
                case "dc:creator":
                    autor = currentValue.toString();
                    break;
                case "description":
                    descripcion = currentValue.toString();
                    break;
                case "pubDate":
                    fechaPublicacion = currentValue.toString();
                    break;
                case "category":
                    categorias.set(categorias.size() - 1, currentValue.toString());
                    break;
                case "item":
                    dentroItem = false;
                    mostrarNoticia();
                    break;
            }
        }
    }

    private void mostrarNoticia() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Fecha de publicación: " + fechaPublicacion);

        // Calcular tiempo transcurrido desde la publicación usando ZonedDateTime
        LocalDateTime fechaPublicacionDate = LocalDateTime.parse(fechaPublicacion, formatter);
        Duration duracion = Duration.between(fechaPublicacionDate, LocalDateTime.now());
        long minutos = duracion.toMinutes();

        // Determinar el tiempo transcurrido en minutos, horas o días
        if (minutos < 60) {
            System.out.println("Tiempo transcurrido: " + minutos + " minutos");
        } else if (minutos < 1440) {
            System.out.println("Tiempo transcurrido: " + (minutos / 60) + " horas");
        } else {
            System.out.println("Tiempo transcurrido: " + (minutos / 1440) + " días");
        }

        // Mostrar categorías
        System.out.println("Categorías: " + String.join(", ", categorias));
        System.out.println("-----------------------------------------");
    }

}

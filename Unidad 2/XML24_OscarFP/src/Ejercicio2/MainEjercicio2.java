package Ejercicio2;

import Comunes.OperacionesSAX;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/* Óscar Fernández Pastoriza - 53862191D */
public class MainEjercicio2 {
    public static void main(String[] args) {
        OperacionesSAX opSAX;
        ManejadorActualizaciones manejador = new ManejadorActualizaciones();

        try {
            opSAX = new OperacionesSAX();
            opSAX.parseValidatingBySchema(manejador, "Actualizaciones.xml");
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}

package EJ4_A1UD2;

import org.w3c.dom.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static comunes.ComunesDOM.getNewDocument;
import static comunes.ComunesDOM.grabarNuevoArbol;

public class E1_4 {
    final static String NOMBRE_ARCHIVO = "src/ejercicio1_4/Hoteles.xml";
    static ArrayList<HotelDOM> hoteles = new ArrayList<>();
    public static void main(String[] args) {
        crearHoteles();

        // Creamos el document y el raíz
        Document doc = getNewDocument("Hoteles");

        // Añadimos cada hotel de Hoteles al documento.
        for (HotelDOM hotel : hoteles) {
            hotel.addToDocument(doc);
        }

        grabarNuevoArbol(doc.getDocumentElement(), NOMBRE_ARCHIVO);
    }

    private static void crearHoteles() {
        hoteles.add(new HotelDOM(1, "num1", LocalDate.of(2024, 10, 16), "1231234", new Direccion("Calle 1", 1, 36930)));
        hoteles.add(new HotelDOM(2, "num2", LocalDate.of(2024, 10, 16), "1231234", new Direccion("Calle 2", 2, 36930)));
        hoteles.add(new HotelDOM(3, "num3", LocalDate.of(2024, 10, 16), "1231234", new Direccion("Calle 3", 3, 36930)));

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/ejercicio1_4/hoteles.dat"))) {
            for (Hotel hotel : hoteles) {
                out.writeObject(hotel);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

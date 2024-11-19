package EJ5_2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class Ejercicio5_2 {
    public static void main(String[] args) {
        File xml = new File("src/EJ5_2/BibliotecaInformatica.xml");
        try {
            XmlMapper mapeadorXML = new XmlMapper();
            JsonNode nodo = mapeadorXML.readTree(xml);
            ObjectMapper mapeadorJSON = new ObjectMapper();
            String json = mapeadorJSON.writerWithDefaultPrettyPrinter().writeValueAsString(nodo);
            mapeadorJSON.writeValue(new File("src/EJ5_2/BibliotecaInformatica.json"), nodo);

            System.out.println(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

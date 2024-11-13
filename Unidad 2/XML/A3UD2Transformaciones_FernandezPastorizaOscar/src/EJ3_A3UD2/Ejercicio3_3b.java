package EJ3_A3UD2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

public class Ejercicio3_3b {
    public static void main(String[] args) {
        TransformerFactory transFact = TransformerFactory.newInstance();
        Transformer transformer;
        Document document;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("src/EJ3_A3UD2/Alumnos.xml");
            anhadirNewAlumno(document, "Oscar", 9);

            transformer = transFact.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult("src/EJ3_A3UD2/Alumnos.xml");

            transformer.transform(source, result);
        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            throw new RuntimeException(e);
        }

        Ejercicio3_3a.main(null);
    }

    private static void anhadirNewAlumno(Document document, String nombre, int nota) {
        Element nuevoAlumno = document.createElement("alumno");
        Element elementoNombre = document.createElement("nome");
        elementoNombre.setTextContent(nombre);
        Element elementoNota = document.createElement("nota");
        elementoNota.setTextContent(String.valueOf(nota));
        nuevoAlumno.appendChild(elementoNombre);
        nuevoAlumno.appendChild(elementoNota);

        Node alumnos = document.getElementsByTagName("alumnos").item(0);
        alumnos.appendChild(nuevoAlumno);
    }
}

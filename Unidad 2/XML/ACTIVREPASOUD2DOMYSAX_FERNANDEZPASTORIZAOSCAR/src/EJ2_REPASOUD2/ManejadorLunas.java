package EJ2_REPASOUD2;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ManejadorLunas extends DefaultHandler {
    private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private Document doc;
    private Element lunas;
    private Element luna;
    private String elementoActual = "";

    @Override
    public void startDocument() {
        dbf.setValidating(true);
        try {
            doc = dbf.newDocumentBuilder().newDocument();
            lunas = doc.createElement("Lunas");
            lunas.setAttribute("Descripcion", "Fases lunares");
            lunas.setAttribute("Servicio", "MeteoGalicia");
            doc.appendChild(lunas);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) {
        switch (qName) {
            case "item" -> luna = doc.createElement("Luna");
            case "Luas:data" -> elementoActual = "data";
            case "Luas:estado" -> elementoActual = "estado";
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("item")) {
            lunas.appendChild(luna);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        switch (elementoActual) {
            case "estado" -> {
                String estado = new String(ch, start, length).toUpperCase();
                Attr attr = doc.createAttribute("Estado");
                attr.setValue(estado);
                luna.setAttributeNode(attr);
            }
            case "data" -> {
                String linea = new String(ch, start, length);
                String[] partes = linea.split(" ");
                String fecha = partes[0];
                Node nodoFecha = doc.createElement("Fecha");
                nodoFecha.setTextContent(fecha);
                luna.appendChild(nodoFecha);

                String hora = partes[1];
                Node nodoHora = doc.createElement("Hora");
                nodoHora.setTextContent(hora);
                luna.appendChild(nodoHora);
            }
        }
        elementoActual = "";
    }

    public Document getDocument() {
        return doc;
    }
}
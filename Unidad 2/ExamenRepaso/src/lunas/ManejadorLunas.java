package lunas;

import javalib.OperacionesDOM;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManejadorLunas extends DefaultHandler {
    enum Elemento {Luna, Fecha, Estado}

    private OperacionesDOM operacionesDOM;
    private String rutaNewXML;
    private Elemento elementoActual;
    private Element lunaActual;

    public ManejadorLunas(String rutaNuevoXML) {
        this.rutaNewXML = rutaNuevoXML;
    }

    @Override
    public void startDocument() throws SAXException {
        operacionesDOM = new OperacionesDOM("Lunas");
        operacionesDOM.addAtributoToRoot("Descripcion", "Fases Lunas");
        operacionesDOM.addAtributoToRoot("Servicio", "MeteoGalicia");
    }

    @Override
    public void endDocument() throws SAXException {
        OperacionesDOM.grabarNuevoArbol(operacionesDOM.getDocument(), rutaNewXML);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "item" -> elementoActual = Elemento.Luna;
            case "Luas:estado" -> elementoActual = Elemento.Estado;
            case "Luas:data" -> elementoActual = Elemento.Fecha;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (elementoActual == Elemento.Estado) {
            lunaActual = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementoActual != null) {
            switch (elementoActual) {
                case Elemento.Luna -> lunaActual = operacionesDOM.appendNewElemento("Luna");
                case Elemento.Estado -> {
                    String valorAtributo = String.valueOf(ch, start, length).toUpperCase();
                    operacionesDOM.addAtributoToElemento(lunaActual, "Estado", valorAtributo);
                }
                case Elemento.Fecha -> {
                    String fechaCompleta = String.valueOf(ch, start, length);
                    String[] partes = fechaCompleta.split(" ");
                    String prettyFecha = prettyUnaFecha(partes[0]);
                    lunaActual.appendChild(operacionesDOM.getNewElemento("Fecha", prettyFecha));
                    lunaActual.appendChild(operacionesDOM.getNewElemento("Hora", partes[1]));
                }
            }
            elementoActual = null;
        }
    }

    private String prettyUnaFecha(String fecha) {
        LocalDate fechaDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return fechaDate.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
    }
}

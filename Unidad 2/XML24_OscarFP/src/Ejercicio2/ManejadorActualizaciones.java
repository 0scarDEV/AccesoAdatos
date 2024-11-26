package Ejercicio2;

import Comunes.OperacionesDOM;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/* Óscar Fernández Pastoriza - 53862191D */
public class ManejadorActualizaciones extends DefaultHandler {
    enum Etiquetas {Rio, Fecha, Calidad}
    private OperacionesDOM operacionesDOM;

    private final DateTimeFormatter formatoActualizacion;
    private final DateTimeFormatter formatoMediciones;

    // SAX
    private Etiquetas etiquetaActual;
    private Element rioActual;
    private Element medicionActual;
    private String codRio;

    public ManejadorActualizaciones() {
        formatoActualizacion = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatoMediciones = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    }

    @Override
    public void startDocument() {
        File mediciones = new File("medicionesRios.xml");
        operacionesDOM = new OperacionesDOM(mediciones);
    }

    @Override
    public void endDocument() {
        operacionesDOM.grabarNuevoArbol("medicionesRiosOUT.xml");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName)  {
            case "Rio" -> {
                codRio = attributes.getValue("codigo");
                rioActual = operacionesDOM.getRioPorCodigo(codRio);
                etiquetaActual = Etiquetas.Rio;
            }
            case "Fecha" -> etiquetaActual = Etiquetas.Fecha;
            case "calidad" -> etiquetaActual = Etiquetas.Calidad;
            case "pH" -> {
                String tipoPh = attributes.getValue("tipo");
                String porcentajePH = attributes.getValue("porcentaje");

                Element elementoPH = operacionesDOM.addElementoToElemento(medicionActual, "pH", porcentajePH);
                operacionesDOM.addAtributoToElemento(elementoPH, "tipo", tipoPh);
            }
            case "Oxigeno" -> {
                String valorOx = attributes.getValue("valor");
                operacionesDOM.addElementoToElemento(medicionActual, "Oxigeno", valorOx);
            }
            case "Temperatura" -> {
                String gradosT = attributes.getValue("grados");
                operacionesDOM.addElementoToElemento(medicionActual, "Temperatura", gradosT);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (etiquetaActual == null) {
            return;
        }

        switch (etiquetaActual) {
            case Etiquetas.Rio -> {
                if (rioActual == null) {
                    String nombreRio = String.valueOf(ch, start, length);

                    rioActual = operacionesDOM.getNewRio(codRio, nombreRio);
                    System.out.println("Se ha añadio un nuevo río.");
                }
            }
            case Etiquetas.Fecha -> {
                String fecha = String.valueOf(ch, start, length);

                // Formatear la fecha
                LocalDate fechaDate = LocalDate.parse(fecha, formatoActualizacion);
                String fechaFormateada = fechaDate.format(formatoMediciones);
                //

                medicionActual = operacionesDOM.getMedicion(codRio, fechaFormateada); // Si devuelve null, es que no existe

                if (medicionActual == null) {
                    // Creamos la medición
                    medicionActual = operacionesDOM.addElementoToElemento(rioActual, "Medicion");
                } else {
                    // Borramos los datos de la medición que ya existía para actualizarlos
                    operacionesDOM.eliminarDatosMedicion(medicionActual);
                }

                operacionesDOM.addAtributoToElemento(medicionActual, "fecha", fechaFormateada);
            }
            case Etiquetas.Calidad -> {
                String calidad = String.valueOf(ch, start, length);
                operacionesDOM.addAtributoToElemento(medicionActual, "calidad", calidad);
            }
        }
    }
}

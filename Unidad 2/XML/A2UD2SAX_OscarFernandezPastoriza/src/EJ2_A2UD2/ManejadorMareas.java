package EJ2_A2UD2;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorMareas extends DefaultHandler {
    private StringBuilder str = new StringBuilder();
    private boolean esElementoImprimible = false;
    private boolean esUnPuerto = false;
    private String puerto;
    @Override
    public void startDocument() {
        str.append("======================");
        str.append("\nTáboa de mareas");
        str.append("\n======================");
    }

    @Override
    public void endDocument() {}

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) {
        switch (localName) {
            case "item" -> {}
            case "dataPredicion" -> {
                str.append("\nFecha de la predicción: ");
                esElementoImprimible = true;
            }
            case "nomePorto" -> {
                esElementoImprimible = false;
                esUnPuerto = true;
            }
            case "mareas" -> {
                //Recorremos los atributos
                for(int i=0;i<attributes.getLength();i++){
                    switch (attributes.getQName(i)) {
                        case "estado":
                            if (puerto != null) {
                                str.append("\t\tPuerto: ").append(puerto);
                                puerto = null;
                            }
                            str.append("\n");
                        case "hora":
                        case "altura":
                            str.append("\t\t")
                                .append(attributes.getQName(i)).append(": ")
                                .append(attributes.getValue(i));
                    }
                }
                esElementoImprimible = true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (esElementoImprimible && !str.isEmpty()) {
            str.append(String.valueOf(ch, start, length));
        }
        if (esUnPuerto) {
            puerto = String.valueOf(ch, start, length);
            esUnPuerto = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String name) {
        if (localName.equals("item")) {
            str.append("\n");
        }
        esElementoImprimible = false;
    }

    public StringBuilder getStringBuilder() {
        return str;
    }
}

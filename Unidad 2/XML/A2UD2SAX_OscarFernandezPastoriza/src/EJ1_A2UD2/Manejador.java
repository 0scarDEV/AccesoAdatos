package EJ1_A2UD2;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {
    private StringBuilder str = new StringBuilder();
    private static int numActores = 0;

    @Override
    public void startDocument() {
        str.append("Información de actores");
        str.append("\n======================");
    }

    @Override
    public void endDocument() {
        str.append("\nTotal de actores: ").append(numActores);
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) {
        if (localName.equals("Actores")) {
            str.append("\nActores");
        } else if (localName.equals("Actor")) {
            numActores++;
            str.append("\nActor ").append(numActores);
            str.append("\n**************\n");
            for (int i = 0; i < attributes.getLength(); i++) {
                str.append(attributes.getQName(i)).append(": ").append(attributes.getValue(i)).append("\n");
            }
        } else {
            str.append(localName).append(": ");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (!str.isEmpty()) {
            str.append(String.valueOf(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String name) {
        if (localName.equals("Actor")) {
            str.append("\n------------------------------");
        } else {
            str.append("\n");
        }
    }

    public StringBuilder getStringBuilder() {
        return str;
    }
}

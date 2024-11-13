package EJ3_A3UD2;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class NotasHandler extends DefaultHandler {
    StringBuilder sb;
    String nome;
    String elementoActual;
    int numAlumnos;
    int totalAlumnos;
    
    public NotasHandler() {
        super();
    }

    @Override 
    public void startDocument() {
        totalAlumnos = 0;
        elementoActual = "";
        sb = new StringBuilder();

        sb.append("NOTAS");
        sb.append("\n================================");
    }

    @Override
    public void endDocument() {
        sb.append("\n---------------------------------");
        sb.append("\nTotal de alumnos: ").append(totalAlumnos);
        System.out.println(sb);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "nota" -> {
                numAlumnos = 0;
                String nota = attributes.getValue("valor");
                sb.append("\n").append(getStringNota(nota));
            }
            case "alumno" -> {
                elementoActual = "nome";
                numAlumnos++;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("nota")) {
            sb.append("\nNum. de alumnos ").append(numAlumnos);
            sb.append("\n---------------------------------");
            totalAlumnos += numAlumnos;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (elementoActual.equals("nome")) {
            nome = new String(ch, start, length);
            sb.append("\n\t\t").append(nome);
        }
        elementoActual = "";
    }

    private String getStringNota(String nota) {
        return switch (nota) {
            case "9", "10" -> "SOBRESALIENTE";
            case "7", "8" -> "NOTABLE " + nota;
            case "6" -> "BIEN";
            case "5" -> "SUFICIENTE";
            case "4", "3", "2", "1", "0" -> "INSUFICIENTE " + nota;
            default -> "";
        };
    }
}

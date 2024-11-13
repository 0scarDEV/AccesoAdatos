package comunes;

import org.w3c.dom.*;

public class ComunesActor {
    public static StringBuilder obtenerArbolDOM(Node nodo) {
        StringBuilder output = new StringBuilder();
        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) nodo;
            if (element.getTagName().equalsIgnoreCase("Actor")) {
                output.append("\n-----------------------------");
            }

            output.append("\n").append(element.getTagName());
            visualizarAtributos(element, output);
        } else if (nodo.getNodeType() == Node.TEXT_NODE) {
            String texto = nodo.getNodeValue().trim();
            if (!texto.isEmpty()) {
                output.append(":").append(texto);
            }
        }

        NodeList children = nodo.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            output.append(obtenerArbolDOM(children.item(i)));
        }

        return output;
    }
    private static void visualizarAtributos(Element element, StringBuilder output) {
        NamedNodeMap atributos = element.getAttributes();
        for (int i = 0, len = atributos.getLength(); i < len; i++) {
            Node atributo = atributos.item(i);
            output.append(" ").append(atributo.getNodeName()).append(" ").append(atributo.getNodeValue());
        }
    }
}
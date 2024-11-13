package EJ3_A1UD2;

import comunes.ComunesDOM;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import static comunes.ComunesDOM.grabarNuevoArbol;

public class E1_3 {
    final static String DOC_EMPLEADOS_xml = "src/EJ3_A1UD2/Empleados.xml";
    final static String ID = "ID";
    final static String NOMBRE_ARCHIVO = "src/EJ3_A1UD2/EmpleadoCambio.xml";


    public static void main(String[] args) {
        // Realizar un programa java que cargue el fichero Empleados.xml en memoria mediante un árbol DOM validándolo con un esquema.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        dbf.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        Document documento = ComunesDOM.generarDoc(dbf, DOC_EMPLEADOS_xml);
        Node raiz = documento.getDocumentElement();

        // a) Crea un méttodo recursivo para visualizar toda la información.
        visualizarInfo(raiz);

        // b) Crea un méttodo para añadir a todos los nodos empleado un atributo id con valores consecutivos empezando por 1.
        crearID(raiz);

        // c) Añade el siguiente nodo al final del documento.
        /*
         * <Empleado id=10>
         * <Nombre>Carmen Gil Villa </Nombre>
         * <Cargo>Secretaria </Cargo>
         * <Direccion>Del Mar, 4 36004 Pontevedra </Direccion>
         * <Aumento>200 euros</Aumento>
         * </Empleado>
         */
        crearNuevoEmpleado(documento);

        // d) Añade el siguiente nodo al principio del documento
        /*
         * <Empleado id=11>
         * <Nombre>Lucia Martín Martín </Nombre>
         * <Cargo>Gerente </Cargo>
         * <Direccion>Avda Vigo, 7 36911 Marin –Pontevedra- </Direccion>
         * <Aumento>200 euros</Aumento>
         * </Empleado>
         */
        crearEmpleadoAlPrincipio(documento);

        // e) Reemplaza el nodo 1 por este nodo:
        /*
         * <Empleado id=14>
         * <Nombre>Carolina Sanchez Jimenez </Nombre>
         * <Cargo>Trabajador </Cargo>
         * <Direccion>Oriente, 6 35200 Vigo Pontevedra </Direccion>
         * <Aumento>100 euros</Aumento>
         * </Empleado>
         */
        reemplazarPrimerEmpleado(documento);

        //f) Borra el segundo nodo. 3
        borrarNodo(documento,  2);

        //g) Clona el nodo 3 y sitúalo al final del documento. Cambia el valor del atributo por id=15 y el nombre por Maria Rivas Rivas. 5
        clonarAnhadirAlFin(documento, 3);

        //h) Haz un metodo para grabar el árbol modificado en un fichero XML llamado EmpleadoCambio.xml.
        grabarNuevoArbol(raiz, NOMBRE_ARCHIVO);

        //Visualízalo también por pantalla.
        visualizarInfo(raiz);
    }

    private static void clonarAnhadirAlFin(Document doc, int index) {
        Node raiz = doc.getDocumentElement();
        Node nuevoNodo = raiz.getChildNodes().item(index - 1).cloneNode(true);
        nuevoNodo.getAttributes().getNamedItem(ID).setNodeValue("15");
        nuevoNodo.getFirstChild().setTextContent("Maria Rivas Rivas");
        raiz.appendChild(nuevoNodo);
    }

    private static void borrarNodo(Document doc, int index) {
        Node raiz = doc.getDocumentElement();
        Node nodoBorrar = raiz.getChildNodes().item(index - 1);
        raiz.removeChild(nodoBorrar);
    }

    private static void reemplazarPrimerEmpleado(Document doc) {
        Element nuevoEmpleado = doc.createElement("Empleado");

        nuevoEmpleado.setAttribute("ID", "14");

        Element elemento = crearElemento(doc,"Nombre", "Carolina Sanchez Jimenez");
        nuevoEmpleado.appendChild(elemento);

        elemento = crearElemento(doc,"Cargo", "Trabajador");
        nuevoEmpleado.appendChild(elemento);

        elemento = crearElemento(doc, "Direccion", "Oriente, 6 35200 Vigo Pontevedra");
        nuevoEmpleado.appendChild(elemento);

        elemento = crearElemento(doc, "Aumento", "100 euros");
        nuevoEmpleado.appendChild(elemento);

        Node raiz =  doc.getFirstChild();
        raiz.replaceChild(nuevoEmpleado, raiz.getFirstChild().getNextSibling());
    }

    private static void crearEmpleadoAlPrincipio(Document doc) {
        Element nuevoEmpleado = doc.createElement("Empleado");

        nuevoEmpleado.setAttribute("ID", "11");

        Element elemento = crearElemento(doc,"Nombre", "Lucia Martín Martín");
        nuevoEmpleado.appendChild(elemento);

        elemento = crearElemento(doc,"Cargo", "Gerente");
        nuevoEmpleado.appendChild(elemento);

        elemento = crearElemento(doc, "Direccion", "Avda Vigo, 7 36911 Marin –Pontevedra");
        nuevoEmpleado.appendChild(elemento);

        elemento = crearElemento(doc, "Aumento", "200 euros");
        nuevoEmpleado.appendChild(elemento);

        doc.getFirstChild().insertBefore(nuevoEmpleado, doc.getFirstChild().getFirstChild());
    }

    private static void crearNuevoEmpleado(Document doc) {
        Element nuevoEmpleado = doc.createElement("Empleado");

        nuevoEmpleado.setAttribute("ID", "10");

        Element elemento = crearElemento(doc,"Nombre", "Carmen Gil Villa");
        nuevoEmpleado.appendChild(elemento);

        elemento = crearElemento(doc,"Cargo", "Secretaria");
        nuevoEmpleado.appendChild(elemento);

        elemento = crearElemento(doc, "Direccion", "Del Mar, 4 36004 Pontevedra");
        nuevoEmpleado.appendChild(elemento);

        elemento = crearElemento(doc, "Aumento", "200 euros");
        nuevoEmpleado.appendChild(elemento);

        doc.getFirstChild().appendChild(nuevoEmpleado);
    }

    private static Element crearElemento(Document doc, String tag, String text) {
        Element elemento = doc.createElement(tag);
        Text texto = doc.createTextNode(text);
        elemento.appendChild(texto);
        return elemento;
    }

    private static void crearID(Node raiz) {
        NodeList nodos = raiz.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i);
            int ultID;

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                // Creamos el atributo
                Attr atributoID = raiz.getOwnerDocument().createAttribute(ID);

                // Obtenemos el valor anterior si lo hay, e incrementamos.
                Node hermanoAnterior = nodo.getPreviousSibling();
                if (hermanoAnterior != null && hermanoAnterior.hasAttributes()) {
                    ultID = Integer.parseInt(hermanoAnterior.getAttributes().getNamedItem(ID).getNodeValue());
                    int idIncrementado = ultID + 1;
                    atributoID.setNodeValue(Integer.toString(idIncrementado));
                } else {
                    atributoID.setNodeValue(Integer.toString(1));
                }

                // Le asignamos al nodo ese atributo
                element.setAttributeNode(atributoID);
            }
        }
    }

    private static void visualizarInfo(Node raiz) {
        StringBuilder stringBuilder = obtenerArbolDOM(raiz);
        System.out.println(stringBuilder);
    }

    public static StringBuilder obtenerArbolDOM(Node nodo) {
        StringBuilder output = new StringBuilder();
        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) nodo;
            if (element.getTagName().equalsIgnoreCase("Empleado")) {
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

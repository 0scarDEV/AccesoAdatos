package EJ4_A1UD2;

import org.w3c.dom.*;

import java.time.LocalDate;

public class HotelDOM extends Hotel {
    final private static String NOMBRE_NODO_HOTEL = "Hotel";
    final private static String ID = "id";
    final private static String NOMBRE = "Nombre";
    final private static String TELEFONO = "Telefono";
    final private static String DIRECCION = "Direccion";
    final private static String CALLE = "Calle";
    final private static String NUMERO = "Numero";
    final private static String CODPOSTAL = "CodigoPostal";

    public HotelDOM(int codHotel, String nombre, LocalDate fechaInauguracion, String telefono, Direccion direccion) {
        super(codHotel, nombre, fechaInauguracion, telefono, direccion);
    }

    public void addToDocument(Document doc) {
        // Creamos un nodo "HOTEL" y lo añadimos al documento */
        Element elemento = doc.createElement(NOMBRE_NODO_HOTEL);

        // Obtenemos su ID
        setElementWithID(doc, elemento);

        // Generamos un nodo hijo (elemento) con otro nodo hijo (texto) por cada atributo del objeto Hotel.
        Element elementoHijo;
        elementoHijo = crearElemento(doc, NOMBRE, this.getNombre());
        elemento.appendChild(elementoHijo);

        elementoHijo = crearElemento(doc, TELEFONO, this.getTelefono());
        elemento.appendChild(elementoHijo);

        elementoHijo = obtenerDireccion(doc);
        elemento.appendChild(elementoHijo);

        // Añadimos al final el elemento creado y lo devolvemos.
        doc.getDocumentElement().appendChild(elemento);
    }

    private Element obtenerDireccion(Document doc) {
        Element direccion = doc.createElement(DIRECCION);
        Element elementoHijo;

        elementoHijo = crearElemento(doc, CALLE, this.getDireccion().getCalle());
        direccion.appendChild(elementoHijo);

        elementoHijo = crearElemento(doc, NUMERO, String.valueOf(this.getDireccion().getNumero()));
        direccion.appendChild(elementoHijo);

        elementoHijo = crearElemento(doc, CODPOSTAL, String.valueOf(this.getDireccion().getCodPostal()));
        direccion.appendChild(elementoHijo);

        return direccion;
    }

    private static Element crearElemento(Document doc, String tag, String text) {
        Element elemento = doc.createElement(tag);
        Text texto = doc.createTextNode(text);
        elemento.appendChild(texto);
        return elemento;
    }

    private void setElementWithID(Document doc, Element elemento) {
        // Creamos el atributo
        Attr atributoID = doc.createAttribute(ID);

        // Asignamos al atributo el valor del código del Hotel.
        atributoID.setNodeValue(String.valueOf(this.getCodHotel()));

        // Le asignamos al nodo ese atributo
        elemento.setAttributeNode(atributoID);
    }
}

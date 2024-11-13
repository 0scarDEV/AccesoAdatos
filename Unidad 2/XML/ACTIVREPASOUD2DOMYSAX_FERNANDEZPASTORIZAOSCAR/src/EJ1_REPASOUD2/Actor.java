package EJ1_REPASOUD2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Actor {
    private int id;
    private String nome;
    private String sexo;
    private String dataNacemento;

    public Actor(int id, String nome, String sexo, String dataNacemento) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNacemento = dataNacemento;
    }

    public Node appendToXML(Document documento) {
        Element actor = documento.createElement("Actor");
        actor.setAttribute("id", "A" + id);
        actor.setIdAttribute("id", true);

        Node nome = documento.createElement("Nome");
        nome.setTextContent(this.nome);
        actor.appendChild(nome);

        Node sexo = documento.createElement("Sexo");
        sexo.setTextContent(this.sexo);
        actor.appendChild(sexo);

        Element dataNacemento = documento.createElement("DataNacemento");
        dataNacemento.setTextContent(this.dataNacemento);
        dataNacemento.setAttribute("formato", "dd/mm/aaaa");
        actor.appendChild(dataNacemento);

        documento.getDocumentElement().appendChild(actor);
        return actor;
    }
}

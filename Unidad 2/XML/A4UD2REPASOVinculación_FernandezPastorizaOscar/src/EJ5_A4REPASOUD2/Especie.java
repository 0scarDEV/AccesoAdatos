package EJ5_A4REPASOUD2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlType(propOrder = {"nome", "valor", "habitat", "nomeCientifico", "outrosNomes", "capturaMinima", "notas"})
public class Especie {
    private String nome;
    private String valor;
    private String habitat;
    private String nomeCientifico;
    private String outrosNomes;
    private CapturaMinima capturaMinima;
    private ArrayList<String> notas;

    public Especie() {
    }

    public Especie(String nome, String valor, String habitat, String nomeCientifico, String outrosNomes, CapturaMinima capturaMinima, ArrayList<String> notas) {
        this.nome = nome;
        this.valor = valor;
        this.habitat = habitat;
        this.nomeCientifico = nomeCientifico;
        this.outrosNomes = outrosNomes;
        this.capturaMinima = capturaMinima;
        this.notas = notas;
    }

    @XmlAttribute (name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlAttribute (name = "valor")
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @XmlElement(name = "Habitat")
    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    @XmlElement(name = "NomeCientifico")
    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    @XmlElement(name = "OutrosNomes")
    public String getOutrosNomes() {
        return outrosNomes;
    }

    public void setOutrosNomes(String outrosNomes) {
        this.outrosNomes = outrosNomes;
    }

    @XmlElement(name = "CapturaMinima")
    public CapturaMinima getCapturaMinima() {
        return capturaMinima;
    }

    public void setCapturaMinima(CapturaMinima capturaMinima) {
        this.capturaMinima = capturaMinima;
    }

    @XmlElement(name = "Nota")
    public ArrayList<String> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<String> notas) {
        this.notas = notas;
    }

    @Override public String toString() {
        StringBuilder salida = new StringBuilder();

        salida.append("La especie ").append(nome).append(" tiene un valor ").append(valor).append("\n");
        salida.append("\tCaptura mínima: ").append(capturaMinima).append("\n");
        salida.append("\tOtros Nombres:").append("\n");

        if (outrosNomes == null) {
            salida.append("\t\t-").append("Ninguno.\n");
        } else {
            for (String nome : outrosNomes.split(",")) {
                salida.append("\t\t-").append(nome).append("\n");
            }
        }

        salida.append("\tNotas: ").append("\n");
        if (notas == null) {
            salida.append("\t\t-").append("Ninguna").append("\n");
        } else {
            for (String nota : notas) {
                salida.append("\t\t-").append(nota).append("\n");
            }
        }

        return salida.toString();
    }
}

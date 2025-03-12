package pojos;

/* Óscar Fernández Pastoriza */

public class Material {
    private int idFotografo;
    private int numero;
    private String numeroSerie;
    private String material;
    private String marca;
    private String modelo;

    public Material() {
    }

    public Material(int idFotografo, int numero, String numeroSerie, String material, String marca, String modelo) {
        this.idFotografo = idFotografo;
        this.numero = numero;
        this.numeroSerie = numeroSerie;
        this.material = material;
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getIdFotografo() {
        return idFotografo;
    }

    public void setIdFotografo(int idFotografo) {
        this.idFotografo = idFotografo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}

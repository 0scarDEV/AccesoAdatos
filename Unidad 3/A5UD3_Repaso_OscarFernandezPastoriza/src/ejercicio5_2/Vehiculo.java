package ejercicio5_2;

public abstract class Vehiculo {
    int id;
    String matricula, marca, modelo;
    char combustible;

    public Vehiculo(String matricula, String marca, String modelo, char combustible) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.combustible = combustible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public String getCombustible() {
        return String.valueOf(combustible);
    }

    public void setCombustible(char combustible) {
        this.combustible = combustible;
    }
}

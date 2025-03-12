package pojos;

/* Óscar Fernández Pastoriza */

public class Contacto {
    private String direccion;
    private String email;
    private String telefonoFijo;
    private String telefonoMovil;

    public Contacto() {}

    public Contacto(String direccion, String email, String telefonoFijo, String telefonoMovil) {
        this.direccion = direccion;
        this.email = email;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }
}

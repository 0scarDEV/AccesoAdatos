package EJ3_A4UD2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;

@XmlSeeAlso({Estudiante.class, Trabajador.class})
@XmlRootElement(name = "Personas")
public class Personas {
    @XmlElements({
            @XmlElement(name = "Estudiante", type = Estudiante.class),
            @XmlElement(name = "Trabajador", type = Trabajador.class)
    }) private ArrayList<Persona> personas;

    public Personas(){
        personas = new ArrayList<>();
    }

    public Personas(Persona[] personas) {
        this.personas = new ArrayList<>();
        this.personas.addAll(Arrays.asList(personas));
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }
}

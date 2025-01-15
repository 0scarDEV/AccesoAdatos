package clases;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorEmpleado implements JsonDeserializer<Empleado> {
    @Override public Empleado deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject objectoEmpleado = jsonElement.getAsJsonObject();
        String NSS_empleado = objectoEmpleado.get("NSS_empleado").getAsString();
        List<Familiar> listaFamiliares = new ArrayList<>();

        JsonArray arrayFamiliar = objectoEmpleado.getAsJsonArray("familiares");
        for (JsonElement elemento : arrayFamiliar) {
            JsonObject objetoFamiliar = elemento.getAsJsonObject();
            String NSS = objetoFamiliar.get("NSS").getAsString();
            String nombre = objetoFamiliar.get("Nome").getAsString();
            String apellido1 = objetoFamiliar.get("Apelido1").getAsString();
            String apellido2 = objetoFamiliar.get("Apelido2").getAsString();
            String parentesco = objetoFamiliar.get("Parentesco").getAsString();
            String sexStr = objetoFamiliar.get("Sexo").getAsString();
            Familiar.Sexo sexo;
            if (sexStr.charAt(0) == 'H') {
                sexo = Familiar.Sexo.Hombre;
            } else {
                sexo = Familiar.Sexo.Mujer;
            }

            listaFamiliares.add(new Familiar(NSS_empleado, NSS, nombre, apellido1, apellido2, null, parentesco, sexo));
        }

        return new Empleado(NSS_empleado, listaFamiliares);
    }
}
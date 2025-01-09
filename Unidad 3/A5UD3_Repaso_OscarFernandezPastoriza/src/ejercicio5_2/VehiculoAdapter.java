package ejercicio5_2;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.sql.Date;

public class VehiculoAdapter implements JsonDeserializer<Vehiculo> {
    @Override
    public Vehiculo deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext  jsonDeserializationContext)
            throws JsonParseException {
        JsonObject objetoVehiculo = jsonElement.getAsJsonObject();

        String matricula = objetoVehiculo.get("matricula").getAsString();
        String marca = objetoVehiculo.get("marca").getAsString();
        String modelo = objetoVehiculo.get("modelo").getAsString();
        String combustible = objetoVehiculo.get("tipo").getAsString();

        if (objetoVehiculo.has("vehiculoRenting")) {
            JsonObject objetoVehPropio = objetoVehiculo.getAsJsonObject("vehiculoRenting");
            Date fechaInicio = Date.valueOf(objetoVehPropio.get("fechaInicio").getAsString());
            double precioMensual = objetoVehPropio.get("precioMensual").getAsDouble();
            int meses = objetoVehPropio.get("meses").getAsInt();

            return new VehiculoRenting(matricula, marca, modelo, combustible.charAt(0), fechaInicio, precioMensual, meses);
        } else if (objetoVehiculo.has("vehiculoPropio")) {
            JsonObject objetoVehRenting = objetoVehiculo.getAsJsonObject("vehiculoPropio");
            Date fechaCompra = Date.valueOf(objetoVehRenting.get("fechaCompra").getAsString());
            double precioCompra = objetoVehRenting.get("precio").getAsDouble();

            return new VehiculoPropio(matricula, marca, modelo, combustible.charAt(0), fechaCompra, precioCompra);
        }

        throw new JsonParseException("Tipo desconocido de vehículo");
    }
}
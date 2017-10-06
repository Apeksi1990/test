package ru.asemenov.servlets.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.asemenov.models.Apartment;

import java.lang.reflect.Type;

/**
 * Json адаптер для Apartment.
 */
public class ApartmentAdapter implements JsonSerializer<Apartment> {
    @Override
    public JsonElement serialize(Apartment apartment, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", apartment.getId());
        jsonObject.addProperty("name", apartment.getName());
        jsonObject.addProperty("house", apartment.getHouse().getName());
        return jsonObject;
    }
}

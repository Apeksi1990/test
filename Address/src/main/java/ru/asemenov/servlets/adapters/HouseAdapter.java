package ru.asemenov.servlets.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.asemenov.models.House;

import java.lang.reflect.Type;

/**
 * Json адаптер для House.
 */
public class HouseAdapter implements JsonSerializer<House> {
    @Override
    public JsonElement serialize(House house, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", house.getId());
        jsonObject.addProperty("name", house.getName());
        jsonObject.addProperty("street", house.getStreet().getName());
        return jsonObject;
    }
}

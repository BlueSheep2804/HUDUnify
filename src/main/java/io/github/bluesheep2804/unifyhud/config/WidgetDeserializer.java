package io.github.bluesheep2804.unifyhud.config;

import com.google.gson.*;
import io.github.bluesheep2804.unifyhud.api.widget.IWidget;
import io.github.bluesheep2804.unifyhud.widget.TextWidget;

import java.lang.reflect.Type;

public class WidgetDeserializer implements JsonDeserializer<IWidget> {
    @Override
    public IWidget deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        String type = obj.get("type").getAsString();

        return switch (type) {
            case "text" -> context.deserialize(obj, TextWidget.class);
            default -> throw new JsonParseException("Unknown widget type: " + type);
        };
    }
}

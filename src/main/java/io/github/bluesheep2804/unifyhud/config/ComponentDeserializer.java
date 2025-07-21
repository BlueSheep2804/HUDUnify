package io.github.bluesheep2804.unifyhud.config;

import com.google.gson.*;
import io.github.bluesheep2804.unifyhud.api.component.IComponent;
import io.github.bluesheep2804.unifyhud.component.ItemCountComponent;
import io.github.bluesheep2804.unifyhud.component.ItemDamageComponent;
import io.github.bluesheep2804.unifyhud.component.LiteralComponent;

import java.lang.reflect.Type;

public class ComponentDeserializer implements JsonDeserializer<IComponent<?>> {
    @Override
    public IComponent<?> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        String type = obj.get("type").getAsString();

        return switch (type) {
            case "item_count" -> context.deserialize(obj, ItemCountComponent.class);
            case "item_damage" -> context.deserialize(obj, ItemDamageComponent.class);
            case "literal" -> context.deserialize(obj, LiteralComponent.class);
            default -> throw new JsonParseException("Unknown component type: " + type);
        };
    }
}

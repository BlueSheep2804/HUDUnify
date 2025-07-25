package dev.bluesheep.hudunify.config;

import com.google.gson.*;
import dev.bluesheep.hudunify.api.component.IComponent;
import dev.bluesheep.hudunify.component.ComponentRegistry;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;
import java.util.Objects;

public class ComponentDeserializer implements JsonDeserializer<IComponent<?>> {
    @Override
    public IComponent<?> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        String type = obj.get("type").getAsString();

        IComponent<?> component = ComponentRegistry.INSTANCE.getComponents().get(ResourceLocation.parse(type));
        if (Objects.isNull(component)) {
            throw new JsonParseException("Unknown component type: " + type);
        }
        return context.deserialize(obj, component.getClass());
    }
}

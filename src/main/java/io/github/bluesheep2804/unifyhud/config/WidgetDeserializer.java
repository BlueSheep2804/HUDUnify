package io.github.bluesheep2804.unifyhud.config;

import com.google.gson.*;
import io.github.bluesheep2804.unifyhud.api.widget.IWidget;
import io.github.bluesheep2804.unifyhud.widget.WidgetRegistry;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;
import java.util.Objects;

public class WidgetDeserializer implements JsonDeserializer<IWidget> {
    @Override
    public IWidget deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        String type = obj.get("type").getAsString();

        IWidget widget = WidgetRegistry.INSTANCE.getWidgets().get(ResourceLocation.parse(type));
        if (Objects.isNull(widget)) {
            throw new JsonParseException("Unknown widget type: " + type);
        }
        return context.deserialize(obj, widget.getClass());
    }
}

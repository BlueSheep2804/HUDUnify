package dev.bluesheep.hudunify.config;

import com.google.gson.*;
import dev.bluesheep.hudunify.api.widget.IWidget;
import dev.bluesheep.hudunify.widget.WidgetRegistry;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;
import java.util.Objects;

public class WidgetDeserializer implements JsonDeserializer<IWidget> {
    @Override
    public IWidget deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        String type = obj.get("type").getAsString();

        IWidget registeredWidget = WidgetRegistry.INSTANCE.getWidgets().get(ResourceLocation.parse(type));
        if (Objects.isNull(registeredWidget)) {
            throw new JsonParseException("Unknown widget type: " + type);
        }
        IWidget widget = context.deserialize(obj, registeredWidget.getClass());
        widget.init();
        return widget;
    }
}

package dev.bluesheep.hudunify.config;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import dev.bluesheep.hudunify.api.layer.IHudUnifyLayer;

import java.lang.reflect.Type;

public class LayerDeserializer implements JsonDeserializer<IHudUnifyLayer> {
    @Override
    public IHudUnifyLayer deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject()) {
            throw new JsonParseException("Expected a JSON object for layer deserialization");
        }

        return context.deserialize(jsonElement, IHudUnifyLayer.class);
    }
}

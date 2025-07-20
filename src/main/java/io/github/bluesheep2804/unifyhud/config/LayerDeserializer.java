package io.github.bluesheep2804.unifyhud.config;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.github.bluesheep2804.unifyhud.api.layer.IUnifyHudLayer;

import java.lang.reflect.Type;

public class LayerDeserializer implements JsonDeserializer<IUnifyHudLayer> {
    @Override
    public IUnifyHudLayer deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonObject()) {
            throw new JsonParseException("Expected a JSON object for layer deserialization");
        }

        return context.deserialize(jsonElement, IUnifyHudLayer.class);
    }
}

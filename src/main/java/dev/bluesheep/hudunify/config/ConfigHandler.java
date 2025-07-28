package dev.bluesheep.hudunify.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.bluesheep.hudunify.api.layer.IHudUnifyLayer;
import dev.bluesheep.hudunify.api.widget.IWidget;
import dev.bluesheep.hudunify.layer.LayerHandler;
import dev.bluesheep.hudunify.layer.HudUnifyLayer;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static dev.bluesheep.hudunify.HudUnify.LOGGER;
import static dev.bluesheep.hudunify.HudUnify.MODID;

public class ConfigHandler {
    public static ConfigHandler INSTANCE = new ConfigHandler();
    private final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(IWidget.class, new WidgetDeserializer())
            .registerTypeAdapter(IHudUnifyLayer.class, new LayerDeserializer())
            .create();
    private final Path configDir = FMLPaths.CONFIGDIR.get().resolve(MODID);

    private ConfigHandler() {}

    public void loadConfig() {
        try {
            if (!Files.exists(configDir)) {
                Files.createDirectories(configDir);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create config directory " + configDir, e);
        }
        Path overlaysFile = configDir.resolve("overlays.json");
        try {
            if (!Files.exists(overlaysFile)) {
                Files.createFile(overlaysFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create overlays configuration file" + overlaysFile, e);
        }
        try(InputStreamReader reader = new InputStreamReader(Files.newInputStream(overlaysFile), StandardCharsets.UTF_8)) {
            HudUnifyLayer[] overlays = GSON.fromJson(reader, HudUnifyLayer[].class);
            if (overlays != null) {
                LayerHandler layerHandler = LayerHandler.INSTANCE;
                layerHandler.clearLayers();
                layerHandler.addLayerAll(overlays);
            }
        }
        catch (Exception e) {
            LOGGER.error("Unable to read overlays.json", e);
        }
    }
}

package io.github.bluesheep2804.unifyhud.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.bluesheep2804.unifyhud.api.component.IComponent;
import io.github.bluesheep2804.unifyhud.api.layer.IUnifyHudLayer;
import io.github.bluesheep2804.unifyhud.api.widget.IWidget;
import io.github.bluesheep2804.unifyhud.layer.LayerHandler;
import io.github.bluesheep2804.unifyhud.layer.UnifyHudLayer;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.github.bluesheep2804.unifyhud.UnifyHud.LOGGER;
import static io.github.bluesheep2804.unifyhud.UnifyHud.MODID;

public class ConfigHandler {
    public static ConfigHandler INSTANCE = new ConfigHandler();
    private final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(IComponent.class, new ComponentDeserializer())
            .registerTypeAdapter(IWidget.class, new WidgetDeserializer())
            .registerTypeAdapter(IUnifyHudLayer.class, new LayerDeserializer())
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
            UnifyHudLayer[] overlays = GSON.fromJson(reader, UnifyHudLayer[].class);
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

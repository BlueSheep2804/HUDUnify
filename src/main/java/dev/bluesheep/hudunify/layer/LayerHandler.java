package dev.bluesheep.hudunify.layer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import dev.bluesheep.hudunify.mixin.GuiOverlayManagerAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;

import java.util.HashMap;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class LayerHandler {
    public static LayerHandler INSTANCE = new LayerHandler();
    private ImmutableList<NamedGuiOverlay> defaultOverlays;
    private ImmutableMap<ResourceLocation, NamedGuiOverlay> defaultOverlaysByName;
    private final HashMap<ResourceLocation, NamedGuiOverlay> additionalOverlays = new HashMap<>();

    private LayerHandler() {}

    public void init() {
        defaultOverlays = ImmutableList.copyOf(GuiOverlayManager.getOverlays());
        defaultOverlaysByName = ImmutableMap.copyOf(GuiOverlayManagerAccessor.getOverlaysByName());
    }

    public void addLayer(String name, HudUnifyLayer hudUnifyLayer) {
        ResourceLocation id = rl(name);
        NamedGuiOverlay layer = new NamedGuiOverlay(id, hudUnifyLayer);
        additionalOverlays.put(id, layer);
    }

    public void addLayerAll(HudUnifyLayer... layers) {
        for (HudUnifyLayer layer : layers) {
            addLayer(layer.getName(), layer);
        }
    }

    public void addLayerAll(Iterable<HudUnifyLayer> layers) {
        for (HudUnifyLayer layer : layers) {
            addLayer(layer.getName(), layer);
        }
    }

    public void clearLayers() {
        additionalOverlays.clear();
    }

    public void registerOverlay() {
        ImmutableList<NamedGuiOverlay> overlays = ImmutableList.<NamedGuiOverlay>builder()
                .addAll(additionalOverlays.values())
                .addAll(defaultOverlays)
                .build();

        ImmutableMap<ResourceLocation, NamedGuiOverlay> overlaysByName = ImmutableMap.<ResourceLocation, NamedGuiOverlay>builder()
                .putAll(additionalOverlays)
                .putAll(defaultOverlaysByName)
                .build();
        GuiOverlayManagerAccessor.setOverlays(overlays);
        GuiOverlayManagerAccessor.setOverlaysByName(overlaysByName);
    }
}

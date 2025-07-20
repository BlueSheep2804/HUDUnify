package io.github.bluesheep2804.unifyhud.layer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.github.bluesheep2804.unifyhud.mixin.GuiOverlayManagerAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;

import java.util.HashMap;

import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

public class LayerHandler {
    public static final LayerHandler INSTANCE = new LayerHandler();
    private final HashMap<ResourceLocation, NamedGuiOverlay> additionalOverlays = new HashMap<>();

    private LayerHandler() {}

    public void addLayer(String name, UnifyHudLayer unifyHudLayer) {
        ResourceLocation id = rl(name);
        NamedGuiOverlay layer = new NamedGuiOverlay(id, unifyHudLayer);
        additionalOverlays.put(id, layer);
    }

    public void registerOverlay() {
        ImmutableList<NamedGuiOverlay> overlays = ImmutableList.<NamedGuiOverlay>builder()
                .addAll(additionalOverlays.values())
                .addAll(GuiOverlayManager.getOverlays())
                .build();

        ImmutableMap<ResourceLocation, NamedGuiOverlay> overlaysByName = ImmutableMap.<ResourceLocation, NamedGuiOverlay>builder()
                .putAll(additionalOverlays)
                .putAll(GuiOverlayManagerAccessor.getOverlaysByName())
                .build();
        GuiOverlayManagerAccessor.setOverlays(overlays);
        GuiOverlayManagerAccessor.setOverlaysByName(overlaysByName);
    }
}

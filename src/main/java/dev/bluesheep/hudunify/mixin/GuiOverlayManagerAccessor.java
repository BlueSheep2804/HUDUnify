package dev.bluesheep.hudunify.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiOverlayManager.class)
public interface GuiOverlayManagerAccessor {
    @Accessor(value = "OVERLAYS", remap = false)
    static void setOverlays(ImmutableList<NamedGuiOverlay> overlays) {
        throw new AssertionError();
    }

    @Accessor(value = "OVERLAYS_BY_NAME", remap = false)
    static ImmutableMap<ResourceLocation, NamedGuiOverlay> getOverlaysByName() {
        throw new AssertionError();
    }

    @Accessor(value = "OVERLAYS_BY_NAME", remap = false)
    static void setOverlaysByName(ImmutableMap<ResourceLocation, NamedGuiOverlay> overlaysByName) {
        throw new AssertionError();
    }
}

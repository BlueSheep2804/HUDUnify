package dev.bluesheep.hudunify.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.bluesheep.hudunify.api.HudUnifyApi;
import dev.bluesheep.hudunify.api.widget.AbstractWidget;
import dev.bluesheep.hudunify.function.cache.CachedValueBoolean;
import dev.bluesheep.hudunify.function.cache.CachedValueInt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class ItemWidget extends AbstractWidget {
    public String slot = "0";
    public final transient CachedValueInt slotCache = new CachedValueInt(() -> slot);
    public String showBackground = "true";
    public final transient CachedValueBoolean showBackgroundCache = new CachedValueBoolean(() -> showBackground);
    public String showDecorations = "true";
    public final transient CachedValueBoolean showDecorationsCache = new CachedValueBoolean(() -> showDecorations);

    private final ResourceLocation GUI_PATH = ResourceLocation.withDefaultNamespace("textures/gui/widgets.png");

    @Override
    public ResourceLocation getId() {
        return rl("item");
    }

    public int getSlot() {
        return slotCache.getValue();
    }

    public String getSlotRaw() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public boolean getShowBackground() {
        return showBackgroundCache.getValue();
    }

    public String getShowBackgroundRaw() {
        return showBackground;
    }

    public void setShowBackground(String showBackground) {
        this.showBackground = showBackground;
    }

    public boolean getShowDecorations() {
        return showDecorationsCache.getValue();
    }

    public String getShowDecorationsRaw() {
        return showDecorations;
    }

    public void setShowDecorations(String showDecorations) {
        this.showDecorations = showDecorations;
    }

    @Override
    public int getWidth() {
        return getShowBackground() ? 20 : 16;
    }

    @Override
    public int getHeight() {
        return getShowBackground() ? 20 : 16;
    }

    @Override
    public void render(GuiGraphics guiGraphics, float partialTick, int offsetX, int offsetY, int maxWidth, int maxHeight) {
        int posX = calculatePosX(getWidth(), maxWidth) + offsetX;
        int posY = calculatePosY(getHeight(), maxHeight) + offsetY;
        int slot = getSlot();
        HudUnifyApi api = HudUnifyApi.getInstance();
        guiGraphics.pose().pushPose();

        if (getShowBackground()) {
            RenderSystem.enableBlend();
            int textureOffsetX = 1;
            if (slot > -1 && slot < 9) {
                textureOffsetX += slot * 20;
            }
            guiGraphics.blit(
                    GUI_PATH,
                    posX,
                    posY,
                    textureOffsetX,
                    1,
                    20,
                    20
            );
            RenderSystem.disableBlend();
            if (getShowBackground()) {
                posX += 2;
                posY += 2;
            }
        }

        if (getShowDecorations()) {
            Minecraft.getInstance().gui.renderSlot(
                    guiGraphics,
                    posX,
                    posY,
                    partialTick,
                    api.getPlayer(),
                    api.getPlayerItemStack(slot),
                    0
            );
        } else {
            guiGraphics.renderItem(
                    api.getPlayer(),
                    api.getPlayerItemStack(slot),
                    posX,
                    posY,
                    0
            );
        }
        guiGraphics.pose().popPose();
    }
}

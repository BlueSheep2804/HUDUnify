package dev.bluesheep.hudunify.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.bluesheep.hudunify.PlayerUtils;
import dev.bluesheep.hudunify.api.widget.AbstractWidget;
import dev.bluesheep.hudunify.function.cache.CachedValueBoolean;
import dev.bluesheep.hudunify.function.cache.CachedValueString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class ItemWidget extends AbstractWidget {
    public String slot = "0";
    public final transient CachedValueString slotCache = new CachedValueString(() -> slot);
    public String showBackground = "true";
    public final transient CachedValueBoolean showBackgroundCache = new CachedValueBoolean(() -> showBackground);
    public String showDecorations = "true";
    public final transient CachedValueBoolean showDecorationsCache = new CachedValueBoolean(() -> showDecorations);

    private final ResourceLocation GUI_PATH = ResourceLocation.withDefaultNamespace("textures/gui/widgets.png");

    @Override
    public ResourceLocation getId() {
        return rl("item");
    }

    public String getSlot() {
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
        String slot = getSlot();
        guiGraphics.pose().pushPose();

        if (getShowBackground()) {
            RenderSystem.enableBlend();
            String[] slotIdParsed = slot.split("\\.");
            int textureOffsetX = 1;
            if (slotIdParsed.length == 2 && Objects.equals(slotIdParsed[0], "hotbar")) {
                int slotId = Integer.parseInt(slotIdParsed[1]);
                if (-1 < slotId && slotId < 9) {
                    textureOffsetX += slotId * 20;
                }
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
                    PlayerUtils.getPlayer(),
                    PlayerUtils.getPlayerItemStack(slot),
                    0
            );
        } else {
            guiGraphics.renderItem(
                    PlayerUtils.getPlayer(),
                    PlayerUtils.getPlayerItemStack(slot),
                    posX,
                    posY,
                    0
            );
        }
        guiGraphics.pose().popPose();
    }
}

package dev.bluesheep.hudunify.widget;

import dev.bluesheep.hudunify.api.widget.AbstractWidget;
import dev.bluesheep.hudunify.api.widget.IHasColorOption;
import dev.bluesheep.hudunify.function.cache.CachedValueInt;
import dev.bluesheep.hudunify.function.cache.CachedValueString;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class RectangleWidget extends AbstractWidget implements IHasColorOption {
    private String width = "16";
    private final transient CachedValueInt widthCache = new CachedValueInt(() -> width);
    private String height = "16";
    private final transient CachedValueInt heightCache = new CachedValueInt(() -> height);
    private String color = "#FFFFFFFF";
    private final transient CachedValueString colorCache = new CachedValueString(() -> color);

    @Override
    public int getWidth() {
        return widthCache.getValue();
    }

    public String getWidthRaw() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return heightCache.getValue();
    }

    public String getHeightRaw() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String getColor() {
        return colorCache.getValue();
    }

    @Override
    public String getColorRaw() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public ResourceLocation getId() {
        return rl("rectangle");
    }

    @Override
    public void render(GuiGraphics guiGraphics, float partialTick, int offsetX, int offsetY, int maxWidth, int maxHeight) {
        if (!isVisible()) return;
        int x = calculatePosX(getWidth(), maxWidth) + offsetX;
        int y = calculatePosY(getHeight(), maxHeight) + offsetY;
        guiGraphics.fill(
                x,
                y,
                x + getWidth(),
                y + getHeight(),
                getColorInt()
        );
    }
}

package dev.bluesheep.hudunify.widget;

import dev.bluesheep.hudunify.api.widget.AbstractWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class RectangleWidget extends AbstractWidget {
    private int width;
    private int height;
    private String color = "#FFFFFF";

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getColorInt() {
        try {
            return Integer.parseInt(color.replace("#", ""), 16);
        } catch (NumberFormatException e) {
            return CommonColors.WHITE;
        }
    }

    @Override
    public ResourceLocation getId() {
        return rl("rectangle");
    }

    @Override
    public void render(GuiGraphics guiGraphics, int offsetX, int offsetY, int maxWidth, int maxHeight) {
        if (!isVisible()) return;
        int x = calculatePosX(width, maxWidth) + offsetX;
        int y = calculatePosY(height, maxHeight) + offsetY;
        guiGraphics.fill(
                x,
                y,
                x + width,
                y + height,
                getColorInt()
        );
    }
}

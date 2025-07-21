package io.github.bluesheep2804.unifyhud.widget;

import io.github.bluesheep2804.unifyhud.api.widget.AbstractBasicWidget;
import io.github.bluesheep2804.unifyhud.api.widget.OriginPoint;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;

import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

public class RectangleWidget extends AbstractBasicWidget {
    private int width;
    private int height;
    private String color = "#FFFFFF";

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

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
    public void render(GuiGraphics guiGraphics) {
        if (!isVisible()) return;
        int x = calculatePosX(width, guiGraphics.guiWidth());
        int y = calculatePosY(height, guiGraphics.guiHeight());
        guiGraphics.fill(
                x,
                y,
                x + width,
                y + height,
                getColorInt()
        );
    }
}

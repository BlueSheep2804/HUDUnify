package dev.bluesheep.hudunify.api.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public interface IWidget {
    ResourceLocation getId();

    int getPosX();
    String getPosXRaw();
    void setPosX(String posX);

    int getPosY();
    String getPosYRaw();
    void setPosY(String posY);

    Anchor getAnchor();
    String getAnchorRaw();
    void setAnchor(String anchor);

    boolean isVisible();
    String isVisibleRaw();
    void setVisible(String visible);

    int getWidth();
    int getHeight();

    default void init() {}

    void render(GuiGraphics guiGraphics, float partialTick, int offsetX, int offsetY, int maxWidth, int maxHeight);

    default void render(GuiGraphics guiGraphics, float partialTick) {
        render(guiGraphics, partialTick, 0, 0, guiGraphics.guiWidth(), guiGraphics.guiHeight());
    }

    default int calculatePosX(int width, int screenWidth) {
        float offsetX = getAnchor().getX();
        return (int) ((screenWidth * offsetX) - (width * offsetX)) + getPosX();
    }

    default int calculatePosY(int height, int screenHeight) {
        float offsetY = getAnchor().getY();
        return (int) ((screenHeight * offsetY) - (height * offsetY)) + getPosY();
    }
}

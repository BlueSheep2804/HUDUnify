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

    OriginPoint getOriginPoint();
    String getOriginPointRaw();
    void setOriginPoint(String originPoint);

    boolean isVisible();
    String isVisibleRaw();
    void setVisible(String visible);

    int getWidth();
    int getHeight();

    default void init() {}

    void render(GuiGraphics guiGraphics, int offsetX, int offsetY, int maxWidth, int maxHeight);

    default void render(GuiGraphics guiGraphics) {
        render(guiGraphics, 0, 0, guiGraphics.guiWidth(), guiGraphics.guiHeight());
    }

    default int calculatePosX(int width, int screenWidth) {
        float offsetX = getOriginPoint().getX();
        return (int) ((screenWidth * offsetX) - (width * offsetX)) + getPosX();
    }

    default int calculatePosY(int height, int screenHeight) {
        float offsetY = getOriginPoint().getY();
        return (int) ((screenHeight * offsetY) - (height * offsetY)) + getPosY();
    }
}

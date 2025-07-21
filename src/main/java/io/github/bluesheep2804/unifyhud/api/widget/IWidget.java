package io.github.bluesheep2804.unifyhud.api.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public interface IWidget {
    ResourceLocation getId();

    int getPosX();
    void setPosX(int posX);
    int getPosY();
    void setPosY(int posY);

    default void setPosition(int posX, int posY) {
        setPosX(posX);
        setPosY(posY);
    }

    OriginPoint getOriginPoint();
    void setOriginPoint(OriginPoint originPoint);

    boolean isVisible();
    void setVisible(boolean visible);

    void render(GuiGraphics guiGraphics);

    default int calculatePosX(int width, int screenWidth) {
        float offsetX = getOriginPoint().getX();
        return (int) ((screenWidth * offsetX) - (width * offsetX)) + getPosX();
    }

    default int calculatePosY(int height, int screenHeight) {
        float offsetY = getOriginPoint().getY();
        return (int) ((screenHeight * offsetY) - (height * offsetY)) + getPosY();
    }
}

package io.github.bluesheep2804.unifyhud.api.layer;

import io.github.bluesheep2804.unifyhud.api.widget.IWidget;

public interface ILayer {
    IWidget getWidget();
    void setWidget(IWidget widget);

    boolean isVisible();
    void setVisible(boolean visible);

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
}

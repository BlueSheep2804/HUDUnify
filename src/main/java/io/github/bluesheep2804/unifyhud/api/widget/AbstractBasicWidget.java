package io.github.bluesheep2804.unifyhud.api.widget;

public abstract class AbstractBasicWidget implements IWidget {
    private int posX = 0;
    private int posY = 0;
    private OriginPoint originPoint = OriginPoint.TOP_LEFT;
    private boolean visible = true;

    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public void setPosX(int posX) {
        this.posX = posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }

    @Override
    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public OriginPoint getOriginPoint() {
        return this.originPoint;
    }

    @Override
    public void setOriginPoint(OriginPoint originPoint) {
        this.originPoint = originPoint;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}

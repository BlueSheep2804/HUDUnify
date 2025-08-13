package dev.bluesheep.hudunify.api.widget;

import dev.bluesheep.hudunify.function.cache.CachedValueBoolean;
import dev.bluesheep.hudunify.function.cache.CachedValueInt;
import dev.bluesheep.hudunify.function.cache.CachedValueAnchor;

public abstract class AbstractWidget implements IWidget {
    private String posX = "0";
    private final transient CachedValueInt posXCache = new CachedValueInt(() -> posX);
    private String posY = "0";
    private final transient CachedValueInt posYCache = new CachedValueInt(() -> posY);
    private String anchor = "left_top";
    private final transient CachedValueAnchor anchorCache = new CachedValueAnchor(() -> anchor);
    private String visible = "true";
    private final transient CachedValueBoolean visibleCache = new CachedValueBoolean(() -> visible);

    @Override
    public int getPosX() {
        return posXCache.getValue();
    }

    @Override
    public String getPosXRaw() {
        return posX;
    }

    @Override
    public void setPosX(String posX) {
        this.posX = posX;
    }

    @Override
    public int getPosY() {
        return posYCache.getValue();
    }

    @Override
    public String getPosYRaw() {
        return posY;
    }

    @Override
    public void setPosY(String posY) {
        this.posY = posY;
    }

    @Override
    public Anchor getAnchor() {
        return anchorCache.getValue();
    }

    @Override
    public String getAnchorRaw() {
        return anchor;
    }

    @Override
    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    @Override
    public boolean isVisible() {
        return visibleCache.getValue();
    }

    @Override
    public String isVisibleRaw() {
        return visible;
    }

    @Override
    public void setVisible(String visible) {
        this.visible = visible;
    }
}

package dev.bluesheep.hudunify.api.widget;

public enum OriginPoint {
    TOP_LEFT(0, 0),
    TOP_RIGHT(1, 0),
    BOTTOM_LEFT(0, 1),
    BOTTOM_RIGHT(1, 1),
    TOP_CENTER(0.5f, 0),
    BOTTOM_CENTER(0.5f, 1),
    LEFT_CENTER(0, 0.5f),
    RIGHT_CENTER(1, 0.5f),
    CENTER(0.5f, 0.5f);

    private final float x;
    private final float y;

    OriginPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public static OriginPoint fromFloat(float x, float y) {
        for (OriginPoint point : values()) {
            if (point.x == x && point.y == y) {
                return point;
            }
        }
        throw new IllegalArgumentException("No OriginPoint found for coordinates: (" + x + ", " + y + ")");
    }
}

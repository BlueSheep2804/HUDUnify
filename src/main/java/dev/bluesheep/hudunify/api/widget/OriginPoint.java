package dev.bluesheep.hudunify.api.widget;

import java.util.Locale;
import java.util.Objects;

public enum OriginPoint {
    LEFT_TOP(0, 0),
    RIGHT_TOP(1, 0),
    LEFT_BOTTOM(0, 1),
    RIGHT_BOTTOM(1, 1),
    CENTER_TOP(0.5f, 0),
    CENTER_BOTTOM(0.5f, 1),
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

    public static OriginPoint fromString(String str) {
        String[] words = str.toLowerCase(Locale.US).split("_");
        if (words.length == 1 && Objects.equals(words[0], "center")) {
            return CENTER;
        }
        if (words.length != 2) {
            throw new IllegalArgumentException("Invalid OriginPoint format: " + str);
        }
        float x = switch (words[0]) {
            case "left" -> 0;
            case "right" -> 1;
            case "center" -> 0.5f;
            default -> throw new IllegalArgumentException("Invalid x coordinate in OriginPoint: " + words[0]);
        };
        float y = switch (words[1]) {
            case "top" -> 0;
            case "bottom" -> 1;
            case "center" -> 0.5f;
            default -> throw new IllegalArgumentException("Invalid y coordinate in OriginPoint: " + words[1]);
        };
        return fromFloat(x, y);
    }
}

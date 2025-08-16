package dev.bluesheep.hudunify.api.widget;

import net.minecraft.network.chat.TextColor;

public interface IHasColorOption {
    String getColor();

    String getColorRaw();

    void setColor(String color);

    default Integer getColorInt() {
        TextColor color = TextColor.parseColor(getColor());
        if (color == null) return -1;
        int colorValue = color.getValue();
        if (colorValue < 0x1000000) {
            return colorValue | 0xFF000000;
        } else {
            return colorValue;
        }
    }
}

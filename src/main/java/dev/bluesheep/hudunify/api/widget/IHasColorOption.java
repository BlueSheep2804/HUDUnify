package dev.bluesheep.hudunify.api.widget;

import net.minecraft.network.chat.TextColor;

public interface IHasColorOption {
    String getColor();

    String getColorRaw();

    void setColor(String color);

    default Integer getColorInt() {
        TextColor color = TextColor.parseColor(getColor());
        if (color == null) return -1;
        return color.getValue();
    }
}

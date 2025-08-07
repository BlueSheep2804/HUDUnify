package dev.bluesheep.hudunify.widget;

import dev.bluesheep.hudunify.api.widget.AbstractWidget;
import dev.bluesheep.hudunify.function.cache.CachedValueBoolean;
import dev.bluesheep.hudunify.function.cache.CachedValueComponent;
import dev.bluesheep.hudunify.function.cache.CachedValueString;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;

import java.util.Arrays;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class TextWidget extends AbstractWidget {
    private String text = "";
    private final transient CachedValueComponent textCache = new CachedValueComponent(() -> text);
    private Style[] style = {Style.NORMAL};
    private String color = "#FFFFFFFF";
    private final transient CachedValueString colorCache = new CachedValueString(() -> color);
    private String dropShadow = "true";
    private final transient CachedValueBoolean dropShadowCache = new CachedValueBoolean(() -> dropShadow);

    public Component getText() {
        return textCache.getValue()
                .withStyle(convertStyleToChatFormatting());
    }

    public String getTextRaw() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Style[] getStyle() {
        return style;
    }

    public void setStyle(Style[] style) {
        this.style = style;
    }

    public ChatFormatting[] convertStyleToChatFormatting() {
        return Arrays.stream(style).map(Style::getFormat).toArray(ChatFormatting[]::new);
    }

    public String getColor() {
        return colorCache.getValue();
    }

    public String getColorRaw() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getColorInt() {
        try {
            return Integer.parseInt(getColor().replace("#", ""), 16);
        } catch (NumberFormatException e) {
            return CommonColors.WHITE;
        }
    }

    public boolean hasDropShadow() {
        return dropShadowCache.getValue();
    }

    public String getDropShadowRaw() {
        return dropShadow;
    }

    public void setDropShadow(String dropShadow) {
        this.dropShadow = dropShadow;
    }

    @Override
    public ResourceLocation getId() {
        return rl("text");
    }

    @Override
    public int getWidth() {
        return Minecraft.getInstance().font.width(getText());
    }

    @Override
    public int getHeight() {
        return Minecraft.getInstance().font.lineHeight;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int offsetX, int offsetY, int maxWidth, int maxHeight) {
        if (!isVisible()) return;
        Font font = Minecraft.getInstance().font;
        int width = getWidth();
        int height = getHeight();
        int x = calculatePosX(width, maxWidth) + offsetX;
        int y = calculatePosY(height, maxHeight) + offsetY;
        guiGraphics.drawString(
                font,
                getText(),
                x,
                y,
                getColorInt(),
                hasDropShadow()
        );
    }

    public enum Style {
        NORMAL(ChatFormatting.RESET),
        BOLD(ChatFormatting.BOLD),
        ITALIC(ChatFormatting.ITALIC),
        UNDERLINE(ChatFormatting.UNDERLINE),
        STRIKETHROUGH(ChatFormatting.STRIKETHROUGH);

        private final ChatFormatting format;

        public ChatFormatting getFormat() {
            return format;
        }

        Style(ChatFormatting chatFormat) {
            this.format = chatFormat;
        }
    }
}

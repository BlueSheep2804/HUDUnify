package dev.bluesheep.hudunify.widget;

import dev.bluesheep.hudunify.api.widget.AbstractWidget;
import dev.bluesheep.hudunify.function.ExpressionHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;

import java.util.Arrays;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class TextWidget extends AbstractWidget {
    private transient int tick = 0;
    private transient Component componentCache = Component.empty();

    private String text = "";
    private Style[] style = {Style.NORMAL};
    private String color = "#FFFFFF";
    private boolean dropShadow = true;

    public String getText() {
        return this.text;
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
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getColorInt() {
        try {
            return Integer.parseInt(color.replace("#", ""), 16);
        } catch (NumberFormatException e) {
            return CommonColors.WHITE;
        }
    }

    public boolean hasDropShadow() {
        return dropShadow;
    }

    public void setDropShadow(boolean dropShadow) {
        this.dropShadow = dropShadow;
    }

    @Override
    public ResourceLocation getId() {
        return rl("text");
    }

    @Override
    public int getWidth() {
        int nowTick = Minecraft.getInstance().gui.getGuiTicks();
        if (tick != nowTick) {
            tick = nowTick;
            generateComponent();
        }
        return Minecraft.getInstance().font.width(componentCache);
    }

    @Override
    public int getHeight() {
        return Minecraft.getInstance().font.lineHeight;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int offsetX, int offsetY, int maxWidth, int maxHeight) {
        if (!isVisible()) return;
        int nowTick = Minecraft.getInstance().gui.getGuiTicks();
        if (tick != nowTick) {
            tick = nowTick;
            generateComponent();
        }

        Font font = Minecraft.getInstance().font;
        int width = getWidth();
        int height = getHeight();
        int x = calculatePosX(width, maxWidth) + offsetX;
        int y = calculatePosY(height, maxHeight) + offsetY;
        guiGraphics.drawString(
                font,
                componentCache,
                x,
                y,
                getColorInt(),
                dropShadow
        );
    }

    private void generateComponent() {
        componentCache = Component.literal(ExpressionHandler.INSTANCE.parse(text))
                .withStyle(convertStyleToChatFormatting());
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

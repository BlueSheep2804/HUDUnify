package io.github.bluesheep2804.unifyhud.widget;

import io.github.bluesheep2804.unifyhud.api.component.IComponent;
import io.github.bluesheep2804.unifyhud.api.widget.AbstractBasicWidget;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;

import java.util.Arrays;

import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

public class TextWidget extends AbstractBasicWidget {
    private IComponent<?> component;
    private String prefix = "";
    private String suffix = "";
    private Style[] style = {Style.NORMAL};
    private String color = "#FFFFFF";
    private boolean dropShadow = true;

    public IComponent<?> getComponent() {
        return component;
    }

    public void setComponent(IComponent<?> component) {
        this.component = component;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
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
    public void render(GuiGraphics guiGraphics) {
        if (!isVisible()) return;
        Font font = Minecraft.getInstance().font;
        Component textComponent = Component.literal(prefix + getComponent().resolve().toString() + suffix)
                .withStyle(convertStyleToChatFormatting());
        int x = calculatePosX(font.width(textComponent), guiGraphics.guiWidth());
        int y = calculatePosY(font.lineHeight, guiGraphics.guiHeight());
        guiGraphics.drawString(
                font,
                textComponent,
                x,
                y,
                getColorInt(),
                dropShadow
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

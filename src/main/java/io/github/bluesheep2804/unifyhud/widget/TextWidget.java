package io.github.bluesheep2804.unifyhud.widget;

import io.github.bluesheep2804.unifyhud.api.widget.AbstractBasicWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;

import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

public class TextWidget extends AbstractBasicWidget {
    @Override
    public ResourceLocation getId() {
        return rl("text");
    }

    @Override
    public void render(GuiGraphics guiGraphics) {
        if (!isVisible()) return;
        Font font = Minecraft.getInstance().font;
        Component textComponent = Component.literal(getComponent().resolve().toString());
        int originX = (int) (guiGraphics.guiWidth() * this.getOriginPoint().getX());
        int originY = (int) (guiGraphics.guiHeight() * this.getOriginPoint().getY());
        int x = originX - (int) (font.width(textComponent) * this.getOriginPoint().getX());
        int y = originY - (int) (font.lineHeight * this.getOriginPoint().getY());
        guiGraphics.drawString(
                font,
                textComponent,
                x + getPosX(),
                y + getPosY(),
                CommonColors.WHITE
        );
    }
}

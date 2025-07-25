package dev.bluesheep.hudunify.widget;

import com.mojang.blaze3d.platform.NativeImage;
import dev.bluesheep.hudunify.api.widget.AbstractWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class TextureWidget extends AbstractWidget {
    private String texture;
    private NativeImage image;

    public void init() {
        Minecraft minecraft = Minecraft.getInstance();
        SimpleTexture simpleTexture = (SimpleTexture) minecraft.textureManager.getTexture(ResourceLocation.parse(texture));
        try {
            image = simpleTexture.getTextureImage(minecraft.getResourceManager()).getImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    @Override
    public ResourceLocation getId() {
        return rl("texture");
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int offsetX, int offsetY, int maxWidth, int maxHeight) {
        if (!isVisible()) return;
        int width = image.getWidth();
        int height = image.getHeight();
        guiGraphics.blit(
                ResourceLocation.parse(texture),
                calculatePosX(width, maxWidth) + offsetX,
                calculatePosY(height, maxHeight) + offsetY,
                0, 0,
                width, height,
                width, height
        );
    }
}

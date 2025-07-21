package io.github.bluesheep2804.unifyhud.widget;

import com.mojang.blaze3d.platform.NativeImage;
import io.github.bluesheep2804.unifyhud.api.widget.AbstractBasicWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;

import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

public class TextureWidget extends AbstractBasicWidget {
    private String texture;

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
    public void render(GuiGraphics guiGraphics) {
        if (!isVisible()) return;
        Minecraft minecraft = Minecraft.getInstance();
        SimpleTexture simpleTexture = (SimpleTexture) minecraft.textureManager.getTexture(ResourceLocation.parse(texture));
        try {
            NativeImage image = simpleTexture.getTextureImage(minecraft.getResourceManager()).getImage();
            int width = image.getWidth();
            int height = image.getHeight();
            guiGraphics.blit(
                    ResourceLocation.parse(texture),
                    calculatePosX(width, guiGraphics.guiWidth()),
                    calculatePosY(height, guiGraphics.guiHeight()),
                    0, 0,
                    width, height,
                    width, height
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

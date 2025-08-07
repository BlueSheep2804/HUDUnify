package dev.bluesheep.hudunify.widget;

import com.mojang.blaze3d.platform.NativeImage;
import dev.bluesheep.hudunify.api.widget.AbstractWidget;
import dev.bluesheep.hudunify.function.cache.CachedValueString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class TextureWidget extends AbstractWidget {
    private String texture = "minecraft:textures/item/apple.png";
    private final transient CachedValueString textureCache = new CachedValueString(() -> texture);
    private final transient Map<String, Vec2> sizes = new HashMap<>();

    @Override
    public void init() {
        cacheSize();
    }

    public String getTexture() {
        return textureCache.getValue();
    }

    public String getTextureRaw() {
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
        cacheSize();
        return (int) sizes.get(getTexture()).x;
    }

    @Override
    public int getHeight() {
        cacheSize();
        return (int) sizes.get(getTexture()).y;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int offsetX, int offsetY, int maxWidth, int maxHeight) {
        if (!isVisible()) return;
        int width = getWidth();
        int height = getHeight();
        guiGraphics.blit(
                ResourceLocation.parse(getTexture()),
                calculatePosX(width, maxWidth) + offsetX,
                calculatePosY(height, maxHeight) + offsetY,
                0, 0,
                width, height,
                width, height
        );
    }

    private void cacheSize() {
        if (sizes.containsKey(getTexture())) {
            return;
        }
        Minecraft minecraft = Minecraft.getInstance();
        SimpleTexture simpleTexture = (SimpleTexture) minecraft.textureManager.getTexture(ResourceLocation.parse(getTexture()));
        try {
            NativeImage image = simpleTexture.getTextureImage(minecraft.getResourceManager()).getImage();
            sizes.put(getTexture(), new Vec2(image.getWidth(), image.getHeight()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

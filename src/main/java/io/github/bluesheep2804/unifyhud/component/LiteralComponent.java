package io.github.bluesheep2804.unifyhud.component;

import io.github.bluesheep2804.unifyhud.api.component.IComponent;
import net.minecraft.resources.ResourceLocation;

import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

public class LiteralComponent implements IComponent<String> {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public ResourceLocation getId() {
        return rl("literal");
    }

    @Override
    public String resolve() {
        return text;
    }
}

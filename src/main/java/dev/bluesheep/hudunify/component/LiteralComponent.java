package dev.bluesheep.hudunify.component;

import dev.bluesheep.hudunify.api.component.IComponent;
import net.minecraft.resources.ResourceLocation;

import static dev.bluesheep.hudunify.HudUnify.rl;

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

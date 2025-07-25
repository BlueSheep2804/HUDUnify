package dev.bluesheep.hudunify.api.component;

import net.minecraft.resources.ResourceLocation;

public interface IComponent<T> {
    ResourceLocation getId();
    T resolve();
}

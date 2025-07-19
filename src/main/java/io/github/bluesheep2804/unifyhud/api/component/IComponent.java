package io.github.bluesheep2804.unifyhud.api.component;

import net.minecraft.resources.ResourceLocation;

public interface IComponent<T> {
    ResourceLocation getId();
    T resolve();
}

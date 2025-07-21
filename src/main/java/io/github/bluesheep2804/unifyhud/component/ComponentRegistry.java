package io.github.bluesheep2804.unifyhud.component;

import com.google.common.collect.ImmutableMap;
import io.github.bluesheep2804.unifyhud.api.component.IComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ComponentRegistry {
    public static ComponentRegistry INSTANCE = new ComponentRegistry();
    private final Map<ResourceLocation, IComponent<?>> components = new HashMap<>();

    private ComponentRegistry() {}

    public Map<ResourceLocation, IComponent<?>> getComponents() {
        return ImmutableMap.copyOf(components);
    }

    public void register(IComponent<?> component) {
        components.put(component.getId(), component);
    }

    public void registerAll(IComponent<?>... components) {
        for (IComponent<?> component : components) {
            register(component);
        }
    }
}

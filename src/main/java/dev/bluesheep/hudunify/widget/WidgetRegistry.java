package dev.bluesheep.hudunify.widget;

import com.google.common.collect.ImmutableMap;
import dev.bluesheep.hudunify.api.widget.IWidget;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class WidgetRegistry {
    public static WidgetRegistry INSTANCE = new WidgetRegistry();
    private final Map<ResourceLocation, IWidget> widgets = new HashMap<>();

    private WidgetRegistry() {}

    public Map<ResourceLocation, IWidget> getWidgets() {
        return ImmutableMap.copyOf(widgets);
    }

    public void register(IWidget widget) {
        widgets.put(widget.getId(), widget);
    }

    public void registerAll(IWidget... widgets) {
        for (IWidget widget : widgets) {
            register(widget);
        }
    }
}

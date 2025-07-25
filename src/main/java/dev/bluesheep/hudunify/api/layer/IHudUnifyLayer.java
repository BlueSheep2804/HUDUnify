package dev.bluesheep.hudunify.api.layer;

import dev.bluesheep.hudunify.api.widget.IWidget;

import java.util.List;

public interface IHudUnifyLayer {
    String getName();
    void setName(String name);
    List<IWidget> getWidgets();
    void setWidgets(List<IWidget> widgets);
}

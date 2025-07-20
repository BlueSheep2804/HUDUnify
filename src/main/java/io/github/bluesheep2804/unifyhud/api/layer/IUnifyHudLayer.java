package io.github.bluesheep2804.unifyhud.api.layer;

import io.github.bluesheep2804.unifyhud.api.widget.IWidget;

import java.util.List;

public interface IUnifyHudLayer {
    String getName();
    void setName(String name);
    String getId();
    void setId(String id);
    List<IWidget> getWidgets();
    void setWidgets(List<IWidget> widgets);
}

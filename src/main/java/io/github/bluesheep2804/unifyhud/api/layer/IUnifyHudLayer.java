package io.github.bluesheep2804.unifyhud.api.layer;

import io.github.bluesheep2804.unifyhud.api.widget.IWidget;

import java.util.List;

public interface IUnifyHudLayer {
    List<IWidget> getWidgets();
    void setWidgets(List<IWidget> widgets);
}

package io.github.bluesheep2804.unifyhud.api.widget;

import java.util.List;

public interface IHasChildrenWidget {
    List<IWidget> getWidgets();
    void addWidget(IWidget widget);
}

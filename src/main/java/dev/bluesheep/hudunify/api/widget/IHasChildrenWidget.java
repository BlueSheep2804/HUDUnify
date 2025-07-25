package dev.bluesheep.hudunify.api.widget;

import java.util.List;

public interface IHasChildrenWidget {
    List<IWidget> getWidgets();
    void addWidget(IWidget widget);
}

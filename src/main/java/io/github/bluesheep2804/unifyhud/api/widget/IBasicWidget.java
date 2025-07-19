package io.github.bluesheep2804.unifyhud.api.widget;

import io.github.bluesheep2804.unifyhud.api.component.IComponent;

public interface IBasicWidget extends IWidget {
    IComponent getComponent();
    void setComponent(IComponent component);
}

package io.github.bluesheep2804.unifyhud.api.widget;

import java.util.List;

public interface ICompositeWidget extends IWidget{
    List<IWidget> getWidgets();
}

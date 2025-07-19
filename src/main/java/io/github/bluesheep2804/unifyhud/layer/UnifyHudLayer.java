package io.github.bluesheep2804.unifyhud.layer;

import io.github.bluesheep2804.unifyhud.api.layer.IUnifyHudLayer;
import io.github.bluesheep2804.unifyhud.api.widget.IWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.List;

public class UnifyHudLayer implements IGuiOverlay, IUnifyHudLayer {
    private List<IWidget> widgets;

    @Override
    public List<IWidget> getWidgets() {
        return widgets;
    }

    @Override
    public void setWidgets(List<IWidget> widgets) {
        this.widgets = widgets;
    }

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        widgets.forEach(it -> it.render(guiGraphics));
    }
}

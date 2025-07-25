package dev.bluesheep.hudunify.layer;

import dev.bluesheep.hudunify.api.layer.IHudUnifyLayer;
import dev.bluesheep.hudunify.api.widget.IWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.List;

public class HudUnifyLayer implements IGuiOverlay, IHudUnifyLayer {
    private String name;
    private List<IWidget> widgets;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<IWidget> getWidgets() {
        return this.widgets;
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

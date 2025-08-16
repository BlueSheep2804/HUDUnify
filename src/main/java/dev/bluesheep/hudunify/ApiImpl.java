package dev.bluesheep.hudunify;

import com.ezylang.evalex.functions.FunctionIfc;
import dev.bluesheep.hudunify.api.HudUnifyApi;
import dev.bluesheep.hudunify.api.widget.IWidget;
import dev.bluesheep.hudunify.function.ExpressionHandler;
import dev.bluesheep.hudunify.widget.WidgetRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class ApiImpl extends HudUnifyApi {
    public static final ApiImpl INSTANCE = new ApiImpl();

    private ApiImpl() {}

    public void init() {
        HudUnifyApi.setInstance(this);
    }

    @Override
    public void registerFunction(String name, FunctionIfc function) {
        ExpressionHandler.INSTANCE.register(name, function);
    }

    @SafeVarargs
    @Override
    public final void registerFunctionAll(Map.Entry<String, FunctionIfc>... functions) {
        ExpressionHandler.INSTANCE.registerAll(functions);
    }

    @Override
    public void registerWidget(IWidget widget) {
        WidgetRegistry.INSTANCE.register(widget);
    }

    @Override
    public void registerWidgetAll(IWidget... widgets) {
        WidgetRegistry.INSTANCE.registerAll(widgets);
    }

    @Override
    public ItemStack getPlayerItemStack(int slotId) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return ItemStack.EMPTY;
        return player.getInventory().getItem(slotId);
    }

    @Override
    public Player getPlayer() {
        return Minecraft.getInstance().player;
    }

    @Override
    public Entity getCameraEntity() {
        return Minecraft.getInstance().getCameraEntity();
    }
}

package dev.bluesheep.hudunify.api;

import com.ezylang.evalex.functions.FunctionIfc;
import dev.bluesheep.hudunify.api.widget.IWidget;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public abstract class HudUnifyApi {
    private static HudUnifyApi instance;

    protected static void setInstance(HudUnifyApi api) {
        if (instance == null) {
            instance = api;
        }
    }

    public static boolean initialized() {
        return instance != null;
    }

    public static HudUnifyApi getInstance() {
        if (instance == null) {
            throw new IllegalStateException("HudUnifyApi is not initialized. Please ensure it is set before accessing.");
        }
        return instance;
    }

    public abstract void registerFunction(String name, FunctionIfc function);
    public abstract void registerFunctionAll(Map.Entry<String, FunctionIfc>... functions);

    public abstract void registerWidget(IWidget widget);
    public abstract void registerWidgetAll(IWidget... widgets);

    public abstract ItemStack getPlayerItemStack(int slotId);
    public abstract Player getPlayer();
    public abstract Entity getCameraEntity();
}

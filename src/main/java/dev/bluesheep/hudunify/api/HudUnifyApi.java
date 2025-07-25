package dev.bluesheep.hudunify.api;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

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

    public abstract ItemStack getPlayerItemStack(int slotId);
    public abstract Player getPlayer();
    public abstract Entity getCameraEntity();
}

package io.github.bluesheep2804.unifyhud.api;

import net.minecraft.world.item.ItemStack;

public abstract class UnifyHudApi {
    private static UnifyHudApi instance;

    protected static void setInstance(UnifyHudApi api) {
        if (instance == null) {
            instance = api;
        }
    }

    public static boolean initialized() {
        return instance != null;
    }

    public static UnifyHudApi getInstance() {
        if (instance == null) {
            throw new IllegalStateException("UnifyHudApi is not initialized. Please ensure it is set before accessing.");
        }
        return instance;
    }

    public abstract ItemStack getPlayerItemStack(int slotId);
}

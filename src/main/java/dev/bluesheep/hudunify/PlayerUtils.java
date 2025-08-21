package dev.bluesheep.hudunify;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PlayerUtils {
    public static Player getPlayer() {
        return Minecraft.getInstance().player;
    }

    public static Entity getCameraEntity() {
        return Minecraft.getInstance().getCameraEntity();
    }

    public static ItemStack getPlayerItemStack(int slotId) {
        Player player = getPlayer();
        if (player == null) return ItemStack.EMPTY;
        return player.getInventory().getItem(slotId);
    }
}

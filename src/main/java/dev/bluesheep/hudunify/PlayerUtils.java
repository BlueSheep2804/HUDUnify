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

    public static ItemStack getPlayerItemStack(String slotId) {
        String[] argument = slotId.strip().split("\\.");
        if (argument.length < 1) return ItemStack.EMPTY;
        return switch (argument[0]) {
            case "hotbar" -> {
                int slot = Integer.parseInt(argument[1]);
                if (0 > slot || slot > 8) yield ItemStack.EMPTY;
                yield getPlayerItemStack(slot);
            }
            case "inventory" -> {
                int slot = Integer.parseInt(argument[1]);
                if (0 > slot || slot > 26) yield ItemStack.EMPTY;
                yield getPlayerItemStack(9 + Integer.parseInt(argument[1]));
            }
            case "weapon" -> {
                if (argument.length < 2) yield getPlayer().getMainHandItem();
                yield switch (argument[1]) {
                    case "mainhand" -> getPlayer().getMainHandItem();
                    case "offhand" -> getPlayer().getOffhandItem();
                    default -> ItemStack.EMPTY;
                };
            }
            case "armor" -> {
                if (argument.length < 2) yield ItemStack.EMPTY;
                yield switch (argument[1]) {
                    case "head" -> getPlayer().getInventory().armor.get(3);
                    case "chest" -> getPlayer().getInventory().armor.get(2);
                    case "legs" -> getPlayer().getInventory().armor.get(1);
                    case "feet" -> getPlayer().getInventory().armor.get(0);
                    default -> ItemStack.EMPTY;
                };
            }
            default -> ItemStack.EMPTY;
        };
    }
}

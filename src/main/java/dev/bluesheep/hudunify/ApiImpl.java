package dev.bluesheep.hudunify;

import dev.bluesheep.hudunify.api.HudUnifyApi;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ApiImpl extends HudUnifyApi {
    public static final ApiImpl INSTANCE = new ApiImpl();

    private ApiImpl() {}

    public void init() {
        HudUnifyApi.setInstance(this);
    }

    @Override
    public ItemStack getPlayerItemStack(int slotId) {
        return Minecraft.getInstance().player.getInventory().getItem(slotId);
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

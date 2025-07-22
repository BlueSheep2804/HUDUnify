package io.github.bluesheep2804.unifyhud;

import io.github.bluesheep2804.unifyhud.api.UnifyHudApi;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ApiImpl extends UnifyHudApi {
    public static final ApiImpl INSTANCE = new ApiImpl();

    private ApiImpl() {}

    public void init() {
        UnifyHudApi.setInstance(this);
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

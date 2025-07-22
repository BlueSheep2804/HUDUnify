package io.github.bluesheep2804.unifyhud.component;

import io.github.bluesheep2804.unifyhud.ApiImpl;
import io.github.bluesheep2804.unifyhud.api.component.AbstractInventoryComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

public class ItemIdComponent extends AbstractInventoryComponent<ResourceLocation> {
    @Override
    public ResourceLocation getId() {
        return rl("item_id");
    }

    @Override
    public ResourceLocation resolve() {
        ItemStack item = ApiImpl.INSTANCE.getPlayerItemStack(getSlotId());
        return ForgeRegistries.ITEMS.getKey(item.getItem());
    }
}

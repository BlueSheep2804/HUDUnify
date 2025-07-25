package dev.bluesheep.hudunify.component;

import dev.bluesheep.hudunify.ApiImpl;
import dev.bluesheep.hudunify.api.component.AbstractInventoryComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import static dev.bluesheep.hudunify.HudUnify.rl;

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

package dev.bluesheep.hudunify.component;

import dev.bluesheep.hudunify.ApiImpl;
import dev.bluesheep.hudunify.api.component.AbstractInventoryComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class ItemNameComponent extends AbstractInventoryComponent<Component> {
    private boolean emptyIfNoItem = true;

    public boolean isEmptyIfNoItem() {
        return emptyIfNoItem;
    }

    public void setEmptyIfNoItem(boolean emptyIfNoItem) {
        this.emptyIfNoItem = emptyIfNoItem;
    }

    @Override
    public ResourceLocation getId() {
        return rl("item_name");
    }

    @Override
    public Component resolve() {
        ItemStack item = ApiImpl.INSTANCE.getPlayerItemStack(getSlotId());
        if (item.isEmpty() && emptyIfNoItem) {
            return Component.empty();
        }
        return item.getDisplayName().toFlatList().get(1);
    }
}

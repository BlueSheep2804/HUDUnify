package io.github.bluesheep2804.unifyhud.component;

import io.github.bluesheep2804.unifyhud.ApiImpl;
import io.github.bluesheep2804.unifyhud.api.component.AbstractInventoryComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

public class ItemCountComponent extends AbstractInventoryComponent {
    @Override
    public ResourceLocation getId() {
        return rl("item_count");
    }

    @Override
    public Integer resolve() {
        ItemStack item = ApiImpl.INSTANCE.getPlayerItemStack(getSlotId());
        return switch (getMode()) {
            case CURRENT -> item.getCount();
            case MAXIMUM -> item.getMaxStackSize();
            case PERCENTAGE -> (int) ((item.getCount() / (float) item.getMaxStackSize()) * 100);
        };
    }
}

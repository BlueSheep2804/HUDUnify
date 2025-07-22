package io.github.bluesheep2804.unifyhud.component;

import io.github.bluesheep2804.unifyhud.ApiImpl;
import io.github.bluesheep2804.unifyhud.api.component.AbstractInventoryRangeComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

public class ItemDamageComponent extends AbstractInventoryRangeComponent {
    @Override
    public ResourceLocation getId() {
        return rl("item_damage");
    }

    @Override
    public Integer resolve() {
        ItemStack item = ApiImpl.INSTANCE.getPlayerItemStack(getSlotId());
        return switch (getMode()) {
            case CURRENT -> item.getDamageValue();
            case MAXIMUM -> item.getMaxDamage();
            case PERCENTAGE -> (int) ((item.getDamageValue() / (float) item.getMaxDamage()) * 100);
        };
    }
}

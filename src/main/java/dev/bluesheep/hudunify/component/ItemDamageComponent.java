package dev.bluesheep.hudunify.component;

import dev.bluesheep.hudunify.ApiImpl;
import dev.bluesheep.hudunify.api.component.AbstractInventoryRangeComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static dev.bluesheep.hudunify.HudUnify.rl;

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

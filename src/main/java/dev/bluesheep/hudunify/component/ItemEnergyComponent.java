package dev.bluesheep.hudunify.component;

import dev.bluesheep.hudunify.ApiImpl;
import dev.bluesheep.hudunify.api.component.AbstractInventoryRangeComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class ItemEnergyComponent extends AbstractInventoryRangeComponent {
    @Override
    public ResourceLocation getId() {
        return rl("item_energy");
    }

    @Override
    public Integer resolve() {
        ItemStack item = ApiImpl.INSTANCE.getPlayerItemStack(getSlotId());
        LazyOptional<IEnergyStorage> energyCapability = item.getCapability(ForgeCapabilities.ENERGY);
        if (energyCapability.resolve().isEmpty()) {
            return 0;
        }
        IEnergyStorage energy = energyCapability.resolve().get();
        return switch (getMode()) {
            case CURRENT -> energy.getEnergyStored();
            case MAXIMUM -> energy.getMaxEnergyStored();
            case PERCENTAGE -> (int) ((energy.getEnergyStored() / (float) energy.getMaxEnergyStored()) * 100);
        };
    }
}

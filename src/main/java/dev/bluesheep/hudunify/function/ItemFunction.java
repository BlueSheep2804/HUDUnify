package dev.bluesheep.hudunify.function;

import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.functions.AbstractFunction;
import com.ezylang.evalex.functions.FunctionParameter;
import com.ezylang.evalex.parser.Token;
import dev.bluesheep.hudunify.ApiImpl;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;

@FunctionParameter(name = "attribute")
@FunctionParameter(name = "slot")
public class ItemFunction extends AbstractFunction {
    @Override
    public EvaluationValue evaluate(Expression expression, Token token, EvaluationValue... evaluationValues) {
        EvaluationValue attribute = evaluationValues[0];
        EvaluationValue slot = evaluationValues[1];

        ItemStack itemStack = ApiImpl.INSTANCE.getPlayerItemStack(slot.isNumberValue() ? slot.getNumberValue().intValue() : 0);
        return EvaluationValue.of(
            switch (attribute.getStringValue()) {
                case "name" -> itemStack.getDisplayName().toFlatList().get(1).getString();
                case "id" -> ForgeRegistries.ITEMS.getKey(itemStack.getItem()).toString();

                case "count" -> itemStack.getCount();
                case "count_max" -> itemStack.getMaxStackSize();
                case "count_percentage" -> (int) ((itemStack.getCount() / (float) itemStack.getMaxStackSize()) * 100);

                case "durability" -> itemStack.getDamageValue();
                case "durability_max" -> itemStack.getMaxDamage();
                case "durability_percentage" -> (int) ((itemStack.getDamageValue() / (float) itemStack.getMaxDamage()) * 100);

                case "energy" -> getEnergyCapability(itemStack).map(IEnergyStorage::getEnergyStored).orElse(0);
                case "energy_max" -> getEnergyCapability(itemStack).map(IEnergyStorage::getMaxEnergyStored).orElse(0);
                case "energy_percentage" -> getEnergyCapability(itemStack)
                    .map(energy -> (int) ((energy.getEnergyStored() / (float) energy.getMaxEnergyStored()) * 100))
                    .orElse(0);

                default -> "";
            },
            expression.getConfiguration()
        );
    }

    private Optional<IEnergyStorage> getEnergyCapability(ItemStack itemStack) {
        return itemStack.getCapability(ForgeCapabilities.ENERGY).resolve();
    }
}

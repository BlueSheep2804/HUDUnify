package dev.bluesheep.hudunify.function.cache;

import dev.bluesheep.hudunify.function.ExpressionHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Supplier;

public class CachedValueComponent extends CachedValue<MutableComponent> {
    public CachedValueComponent(Supplier<String> expressionSupplier) {
        super(expressionSupplier);
    }

    @Override
    protected MutableComponent parse() {
        return Component.literal(ExpressionHandler.INSTANCE.parse(getExpression()));
    }
}

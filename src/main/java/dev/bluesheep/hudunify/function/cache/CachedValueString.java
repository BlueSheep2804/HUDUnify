package dev.bluesheep.hudunify.function.cache;

import dev.bluesheep.hudunify.function.ExpressionHandler;

import java.util.function.Supplier;

public class CachedValueString extends CachedValue<String> {
    public CachedValueString(Supplier<String> expressionSupplier) {
        super(expressionSupplier);
    }

    @Override
    protected String parse() {
        return ExpressionHandler.INSTANCE.parse(getExpression());
    }
}

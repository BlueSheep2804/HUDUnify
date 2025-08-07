package dev.bluesheep.hudunify.function.cache;

import dev.bluesheep.hudunify.function.ExpressionHandler;

import java.util.function.Supplier;

public class CachedValueInt extends CachedValue<Integer> {
    public CachedValueInt(Supplier<String> expressionSupplier) {
        super(expressionSupplier);
    }

    @Override
    protected Integer parse() {
        return ExpressionHandler.INSTANCE.parseInt(getExpression());
    }
}

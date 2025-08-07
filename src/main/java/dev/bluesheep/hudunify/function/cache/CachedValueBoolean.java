package dev.bluesheep.hudunify.function.cache;

import dev.bluesheep.hudunify.function.ExpressionHandler;

import java.util.function.Supplier;

public class CachedValueBoolean extends CachedValue<Boolean>{
    public CachedValueBoolean(Supplier<String> expressionSupplier) {
        super(expressionSupplier);
    }

    @Override
    protected Boolean parse() {
        String evaluatedValue = ExpressionHandler.INSTANCE.parse(getExpression());
        try {
            return Integer.parseInt(evaluatedValue) > 0;
        } catch (NumberFormatException e) {
            return Boolean.parseBoolean(evaluatedValue);
        }
    }
}

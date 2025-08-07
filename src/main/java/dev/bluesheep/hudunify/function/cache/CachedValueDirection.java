package dev.bluesheep.hudunify.function.cache;

import dev.bluesheep.hudunify.function.ExpressionHandler;
import dev.bluesheep.hudunify.widget.ListWidget;

import java.util.function.Supplier;

public class CachedValueDirection extends CachedValue<ListWidget.Direction> {
    public CachedValueDirection(Supplier<String> expressionSupplier) {
        super(expressionSupplier);
    }

    @Override
    protected ListWidget.Direction parse() {
        return ListWidget.Direction.valueOf(ExpressionHandler.INSTANCE.parse(getExpression()));
    }
}

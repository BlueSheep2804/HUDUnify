package dev.bluesheep.hudunify.function.cache;

import dev.bluesheep.hudunify.api.widget.Anchor;
import dev.bluesheep.hudunify.function.ExpressionHandler;

import java.util.function.Supplier;

public class CachedValueAnchor extends CachedValue<Anchor> {
    public CachedValueAnchor(Supplier<String> expressionSupplier) {
        super(expressionSupplier);
    }

    @Override
    protected Anchor parse() {
        return Anchor.fromString(ExpressionHandler.INSTANCE.parse(getExpression()));
    }
}

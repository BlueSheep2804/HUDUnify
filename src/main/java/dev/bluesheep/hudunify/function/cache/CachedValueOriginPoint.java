package dev.bluesheep.hudunify.function.cache;

import dev.bluesheep.hudunify.api.widget.OriginPoint;
import dev.bluesheep.hudunify.function.ExpressionHandler;

import java.util.function.Supplier;

public class CachedValueOriginPoint extends CachedValue<OriginPoint> {
    public CachedValueOriginPoint(Supplier<String> expressionSupplier) {
        super(expressionSupplier);
    }

    @Override
    protected OriginPoint parse() {
        return OriginPoint.fromString(ExpressionHandler.INSTANCE.parse(getExpression()));
    }
}

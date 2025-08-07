package dev.bluesheep.hudunify.function.cache;

import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

public abstract class CachedValue<T> {
    private Supplier<String> expression;
    private T value;
    private int lastUpdateTick = -1;

    public CachedValue(Supplier<String> expressionSupplier) {
        this.expression = expressionSupplier;
    }

    public String getExpression() {
        return expression.get();
    }

    public void setExpression(Supplier<String> expression) {
        this.expression = expression;
    }

    public T getValue() {
        int tick = Minecraft.getInstance().gui.getGuiTicks();
        if (lastUpdateTick < tick) {
            lastUpdateTick = tick;
            value = parse();
        }
        return value;
    }

    protected abstract T parse();
}

package dev.bluesheep.hudunify.api.component;

public abstract class AbstractPlayerRangeComponent<T> extends AbstractPlayerComponent<T> implements IHasRangeMode {
    private Mode mode;

    @Override
    public Mode getMode() {
        return this.mode;
    }

    @Override
    public void setMode(Mode mode) {
        this.mode = mode;
    }
}

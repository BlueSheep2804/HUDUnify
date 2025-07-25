package dev.bluesheep.hudunify.api.component;

public abstract class AbstractInventoryRangeComponent extends AbstractInventoryComponent<Integer> implements IHasRangeMode {
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

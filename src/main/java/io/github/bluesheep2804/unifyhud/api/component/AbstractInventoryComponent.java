package io.github.bluesheep2804.unifyhud.api.component;

public abstract class AbstractInventoryComponent implements IInventoryComponent {
    private int slotId;
    private Mode mode;

    @Override
    public int getSlotId() {
        return this.slotId;
    }

    @Override
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    @Override
    public Mode getMode() {
        return this.mode;
    }

    @Override
    public void setMode(Mode mode) {
        this.mode = mode;
    }
}

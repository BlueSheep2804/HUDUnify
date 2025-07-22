package io.github.bluesheep2804.unifyhud.api.component;

public abstract class AbstractInventoryComponent<T> implements IComponent<T>, IHasInventorySlot {
    private int slotId;

    @Override
    public int getSlotId() {
        return this.slotId;
    }

    @Override
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}

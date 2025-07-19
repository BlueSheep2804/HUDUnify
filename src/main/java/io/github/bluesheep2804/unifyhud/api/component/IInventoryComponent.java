package io.github.bluesheep2804.unifyhud.api.component;

public interface IInventoryComponent extends IComponent<Integer> {
    int getSlotId();
    void setSlotId(int slotId);

    Mode getMode();
    void setMode(Mode mode);

    enum Mode {
        CURRENT,
        MAXIMUM,
        PERCENTAGE,
    }
}

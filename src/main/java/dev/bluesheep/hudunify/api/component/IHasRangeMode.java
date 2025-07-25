package dev.bluesheep.hudunify.api.component;

public interface IHasRangeMode {
    Mode getMode();
    void setMode(Mode mode);

    enum Mode {
        CURRENT,
        MAXIMUM,
        PERCENTAGE,
    }
}

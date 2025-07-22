package io.github.bluesheep2804.unifyhud.api.component;

public interface IHasRangeMode {
    Mode getMode();
    void setMode(Mode mode);

    enum Mode {
        CURRENT,
        MAXIMUM,
        PERCENTAGE,
    }
}

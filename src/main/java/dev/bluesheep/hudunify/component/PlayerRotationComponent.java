package dev.bluesheep.hudunify.component;

import dev.bluesheep.hudunify.ApiImpl;
import dev.bluesheep.hudunify.api.component.AbstractPlayerComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class PlayerRotationComponent extends AbstractPlayerComponent<Float> {
    private RotationType rotationType = RotationType.YAW;

    public RotationType getRotationType() {
        return rotationType;
    }

    public void setRotationType(RotationType rotationType) {
        this.rotationType = rotationType;
    }

    @Override
    public ResourceLocation getId() {
        return rl("player_rotation");
    }

    @Override
    public Float resolve() {
        Entity camera = ApiImpl.INSTANCE.getCameraEntity();
        return switch (rotationType) {
            case YAW -> Mth.wrapDegrees(camera.getYRot());
            case PITCH -> Mth.wrapDegrees(camera.getXRot());
        };
    }

    public enum RotationType {
        YAW,
        PITCH
    }
}

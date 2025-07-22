package io.github.bluesheep2804.unifyhud.component;

import io.github.bluesheep2804.unifyhud.ApiImpl;
import io.github.bluesheep2804.unifyhud.api.component.AbstractPlayerComponent;
import net.minecraft.core.Direction.Axis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

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

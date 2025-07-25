package dev.bluesheep.hudunify.component;

import dev.bluesheep.hudunify.ApiImpl;
import dev.bluesheep.hudunify.api.component.AbstractPlayerComponent;
import net.minecraft.core.Direction.Axis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class PlayerPosComponent extends AbstractPlayerComponent<Double> {
    private Axis axis = Axis.X;

    public Axis getAxis() {
        return axis;
    }

    public void setAxis(Axis axis) {
        this.axis = axis;
    }

    @Override
    public ResourceLocation getId() {
        return rl("player_pos");
    }

    @Override
    public Double resolve() {
        Vec3 pos = ApiImpl.INSTANCE.getPlayer().position();
        return switch (axis) {
            case X -> pos.x;
            case Y -> pos.y;
            case Z -> pos.z;
        };
    }
}

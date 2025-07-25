package dev.bluesheep.hudunify.component;

import dev.bluesheep.hudunify.ApiImpl;
import dev.bluesheep.hudunify.api.component.AbstractPlayerRangeComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class PlayerHealthComponent extends AbstractPlayerRangeComponent<Float> {
    @Override
    public ResourceLocation getId() {
        return rl("player_health");
    }

    @Override
    public Float resolve() {
        Player player = ApiImpl.INSTANCE.getPlayer();
        return switch (getMode()) {
            case CURRENT -> player.getHealth();
            case MAXIMUM -> player.getMaxHealth();
            case PERCENTAGE -> (player.getHealth() / player.getMaxHealth()) * 100;
        };
    }
}

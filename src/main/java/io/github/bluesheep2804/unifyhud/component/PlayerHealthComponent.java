package io.github.bluesheep2804.unifyhud.component;

import io.github.bluesheep2804.unifyhud.ApiImpl;
import io.github.bluesheep2804.unifyhud.api.component.AbstractPlayerRangeComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

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

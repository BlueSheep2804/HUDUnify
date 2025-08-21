package dev.bluesheep.hudunify;

import com.mojang.blaze3d.platform.InputConstants;
import dev.bluesheep.hudunify.function.*;
import dev.bluesheep.hudunify.widget.*;
import dev.bluesheep.hudunify.config.ConfigHandler;
import dev.bluesheep.hudunify.layer.LayerHandler;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

import java.util.Map;

import static dev.bluesheep.hudunify.HudUnify.MODID;
import static dev.bluesheep.hudunify.HudUnify.LOGGER;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HudUnifyClient {
    public static final Lazy<KeyMapping> RELOAD_KEY = Lazy.of(() -> new KeyMapping(
            "key.hudunify.reload",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_Y,
            "key.categories.misc"
    ));

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(HudUnifyClient::onClientTick);
        LayerHandler.INSTANCE.init();
        ExpressionHandler.INSTANCE.registerAll(
                Map.entry("player", new PlayerFunction()),
                Map.entry("item", new ItemFunction())
        );
        WidgetRegistry.INSTANCE.registerAll(
                new TextWidget(),
                new RectangleWidget(),
                new TextureWidget(),
                new ListWidget(),
                new ItemWidget()
        );
        ConfigHandler.INSTANCE.loadConfig();
        LayerHandler.INSTANCE.registerOverlay();
    }

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(RELOAD_KEY.get());
    }

    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) return;
        if (RELOAD_KEY.get().consumeClick()) {
            LOGGER.info("Reloading overlays configuration");
            ConfigHandler.INSTANCE.loadConfig();
            LayerHandler.INSTANCE.registerOverlay();
            LOGGER.info("Overlays configuration reloaded");
        }
    }
}

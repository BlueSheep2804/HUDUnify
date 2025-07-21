package io.github.bluesheep2804.unifyhud;

import com.mojang.blaze3d.platform.InputConstants;
import io.github.bluesheep2804.unifyhud.component.ComponentRegistry;
import io.github.bluesheep2804.unifyhud.component.ItemCountComponent;
import io.github.bluesheep2804.unifyhud.component.ItemDamageComponent;
import io.github.bluesheep2804.unifyhud.component.LiteralComponent;
import io.github.bluesheep2804.unifyhud.config.ConfigHandler;
import io.github.bluesheep2804.unifyhud.layer.LayerHandler;
import io.github.bluesheep2804.unifyhud.widget.TextWidget;
import io.github.bluesheep2804.unifyhud.widget.WidgetRegistry;
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

import static io.github.bluesheep2804.unifyhud.UnifyHud.MODID;
import static io.github.bluesheep2804.unifyhud.UnifyHud.LOGGER;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UnifyHudClient {
    public static final Lazy<KeyMapping> RELOAD_KEY = Lazy.of(() -> new KeyMapping(
            "key.unifyhud.reload",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_Y,
            "key.categories.misc"
    ));

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(UnifyHudClient::onClientTick);
        LayerHandler.INSTANCE.init();
        ComponentRegistry.INSTANCE.registerAll(
                new ItemCountComponent(),
                new ItemDamageComponent(),
                new LiteralComponent()
        );
        WidgetRegistry.INSTANCE.registerAll(
                new TextWidget()
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

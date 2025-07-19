package io.github.bluesheep2804.unifyhud;

import io.github.bluesheep2804.unifyhud.api.component.IInventoryComponent;
import io.github.bluesheep2804.unifyhud.api.widget.OriginPoint;
import io.github.bluesheep2804.unifyhud.component.ItemCountComponent;
import io.github.bluesheep2804.unifyhud.component.ItemDamageComponent;
import io.github.bluesheep2804.unifyhud.layer.UnifyHudLayer;
import io.github.bluesheep2804.unifyhud.widget.TextWidget;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;

import static io.github.bluesheep2804.unifyhud.UnifyHud.MODID;
import static io.github.bluesheep2804.unifyhud.UnifyHud.LOGGER;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UnifyHudClient {
    public static final UnifyHudLayer testLayer = new UnifyHudLayer();
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        LOGGER.info("HELLO FROM CLIENT SETUP");
        LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        ItemCountComponent component = new ItemCountComponent();
        component.setSlotId(1);
        component.setMode(IInventoryComponent.Mode.PERCENTAGE);
        ItemDamageComponent component2 = new ItemDamageComponent();
        component2.setSlotId(0);
        component2.setMode(IInventoryComponent.Mode.PERCENTAGE);

        TextWidget widget = new TextWidget();
        widget.setComponent(component);
        widget.setPosition(0, 0);
        widget.setOriginPoint(OriginPoint.BOTTOM_RIGHT);
        TextWidget widget2 = new TextWidget();
        widget2.setComponent(component2);
        widget2.setPosition(16, 16);
        widget2.setOriginPoint(OriginPoint.CENTER);

        testLayer.setWidgets(List.of(widget, widget2));
        event.registerAboveAll("unifyhud", testLayer);
    }
}

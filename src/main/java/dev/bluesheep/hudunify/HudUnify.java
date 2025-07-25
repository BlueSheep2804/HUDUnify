package dev.bluesheep.hudunify;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(HudUnify.MODID)
public class HudUnify {
    public static final String MODID = "hudunify";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HudUnify(FMLJavaModLoadingContext context) {
        MinecraftForge.EVENT_BUS.register(this);
        ApiImpl.INSTANCE.init();
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}

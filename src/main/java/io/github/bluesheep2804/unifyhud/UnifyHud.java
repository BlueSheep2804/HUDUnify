package io.github.bluesheep2804.unifyhud;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(UnifyHud.MODID)
public class UnifyHud {
    public static final String MODID = "unifyhud";
    public static final Logger LOGGER = LogUtils.getLogger();

    public UnifyHud(FMLJavaModLoadingContext context) {
        MinecraftForge.EVENT_BUS.register(this);
        ApiImpl.INSTANCE.init();
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}

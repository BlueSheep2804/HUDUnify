package io.github.bluesheep2804.unifyhud;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(UnifyHud.MODID)
public class UnifyHud {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "unifyhud";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public UnifyHud(FMLJavaModLoadingContext context) {
        MinecraftForge.EVENT_BUS.register(this);
        ApiImpl.INSTANCE.init();
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}

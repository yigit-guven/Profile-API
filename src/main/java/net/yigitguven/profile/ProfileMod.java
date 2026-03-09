package net.yigitguven.profile;

import com.mojang.logging.LogUtils;
import net.yigitguven.profile.network.ProfileNetwork;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ProfileMod.MODID)
public class ProfileMod {
    public static final String MODID = "profile";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ProfileMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ProfileNetwork::init);
        LOGGER.info("Profile API Initialized");
    }
}

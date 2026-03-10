package net.yigitguven.profile;

import com.mojang.logging.LogUtils;
import net.yigitguven.profile.network.ProfileNetwork;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.yigitguven.profile.api.ProfileRegistry;
import net.yigitguven.profile.api.ProfileComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

@Mod(ProfileMod.MODID)
public class ProfileMod {
    public static final String MODID = "profile";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ProfileMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);
        MinecraftForge.EVENT_BUS.register(this);

        net.minecraftforge.fml.ModLoadingContext.get()
                .registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, Config.SPEC);
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        ProfileCommand.register(event.getDispatcher());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ProfileNetwork.init();

            // Sample Component for Testing: Shows player health
            ProfileRegistry.register(player -> new ProfileComponent() {
                public ResourceLocation getId() {
                    return new ResourceLocation("profile", "health");
                }

                @Override
                public Component getTitle() {
                    return Component.literal("Health");
                }

                @Override
                public Component getValue() {
                    return Component.literal((int) player.getHealth() + " / " + (int) player.getMaxHealth());
                }
            });

            // Sample Component for Testing: Shows player experience level
            ProfileRegistry.register(player -> new ProfileComponent() {
                public ResourceLocation getId() {
                    return new ResourceLocation("profile", "experience");
                }

                @Override
                public Component getTitle() {
                    return Component.literal("Experience Level");
                }

                @Override
                public Component getValue() {
                    return Component.literal(String.valueOf(player.experienceLevel));
                }
            });
        });
        LOGGER.info("Profile API Initialized");
    }
}

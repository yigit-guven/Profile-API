package net.yigitguven.profile;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = ProfileMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
        private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_PLAYER_MODEL;
        public static final ForgeConfigSpec.ConfigValue<String> BACKGROUND_COLOR;
        public static final ForgeConfigSpec.ConfigValue<String> TEXT_COLOR;
        public static final ForgeConfigSpec.ConfigValue<Integer> MODEL_SCALE;
        public static final ForgeConfigSpec SPEC;

        static {
                BUILDER.push("UI");

                SHOW_PLAYER_MODEL = BUILDER
                                .comment("Whether to show the player model in the profile screen.")
                                .define("showPlayerModel", true);

                BACKGROUND_COLOR = BUILDER
                                .comment(
                                                "Background color of the profile screen in hex format (e.g. #000000 for black). Default is a translucent dark overlay.")
                                .define("backgroundColor", "#C0101010");

                TEXT_COLOR = BUILDER
                                .comment("Text color of the profile string in hex format. Default is white.")
                                .define("textColor", "#FFFFFF");

                MODEL_SCALE = BUILDER
                                .comment("Scale of the player model rendered in the UI.")
                                .define("modelScale", 30);

                BUILDER.pop();
                SPEC = BUILDER.build();
        }

        @SubscribeEvent
        static void onLoad(final ModConfigEvent event) {
        }
}

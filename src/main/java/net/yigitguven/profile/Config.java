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
        public static final ForgeConfigSpec.ConfigValue<Boolean> VANILLA_STYLE_UI;
        public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_SKIN_HEAD;
        public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_ICONS;
        public static final ForgeConfigSpec.ConfigValue<Boolean> USE_FANCY_PANELS;
        public static final ForgeConfigSpec.ConfigValue<String> HIGHLIGHT_COLOR;
        public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_ARMOR_TOOLTIPS;
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

                VANILLA_STYLE_UI = BUILDER
                                .comment("Whether to use the standard vanilla GUI menu background instead of solid colors.")
                                .define("vanillaStyleUi", true);

                SHOW_SKIN_HEAD = BUILDER
                                .comment("Whether to show the player's skin head next to the title.")
                                .define("showSkinHead", true);

                SHOW_ICONS = BUILDER
                                .comment("Whether to display vanilla-style icons (health, food, exp) next to known stats, or custom icons if provided.")
                                .define("showIcons", true);

                USE_FANCY_PANELS = BUILDER
                                .comment("Whether to useframed panels and structured rows for a more premium look.")
                                .define("useFancyPanels", true);

                HIGHLIGHT_COLOR = BUILDER
                                .comment("Highlight color for titles and key information in hex format.")
                                .define("highlightColor", "#55FFFF");

                ENABLE_ARMOR_TOOLTIPS = BUILDER
                                .comment("Whether to show item tooltips when hovering over armor slots.")
                                .define("enableArmorTooltips", true);

                BUILDER.pop();
                SPEC = BUILDER.build();
        }

        @SubscribeEvent
        static void onLoad(final ModConfigEvent event) {
        }
}

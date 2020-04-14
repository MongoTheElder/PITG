package tv.mongotheelder.pitg;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class Config {
    public static final String CATEGORY_ITEMS = "glazing_tool";

    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue ENABLE_GREEN_DYE;
    public static ForgeConfigSpec.BooleanValue ENABLE_GLAZING_TOOL;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        setupItems(COMMON_BUILDER);
        COMMON_CONFIG = COMMON_BUILDER.build();

    }
    private static void setupItems(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("Glazing Tool settings").push(CATEGORY_ITEMS);

        ENABLE_GLAZING_TOOL = COMMON_BUILDER.comment("Enable the glazing tool").define("glazing_tool_enabled", true);
        ENABLE_GREEN_DYE = COMMON_BUILDER.comment("Enable alternate green dye").define("green_dye_enabled", true);

        COMMON_BUILDER.pop();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }

}

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
    public static ForgeConfigSpec.BooleanValue ENABLE_PANE_BREAK;
    public static ForgeConfigSpec.BooleanValue ENABLE_UNBREAKABLE;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        setupItems(COMMON_BUILDER);
        COMMON_CONFIG = COMMON_BUILDER.build();

    }
    private static void setupItems(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("Glazing Tool settings").push(CATEGORY_ITEMS);

        ENABLE_GLAZING_TOOL = COMMON_BUILDER.comment(" Enable the glazing tool").define("glazing_tool_enabled", true);
        ENABLE_PANE_BREAK = COMMON_BUILDER.comment(" Let the glazing tool drop panes (like silk touch) by sneaking while in BREAK mode").define("glazing_tool_break_pane", false);
        ENABLE_UNBREAKABLE= COMMON_BUILDER.comment(" Allow glazing tool to mark glass panes as unbreakable while in UNBREAKING mode").define("unbreakable_pane", false);
        ENABLE_GREEN_DYE = COMMON_BUILDER.comment(" Enable alternate green dye").define("green_dye_enabled", true);

        COMMON_BUILDER.pop();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }

}

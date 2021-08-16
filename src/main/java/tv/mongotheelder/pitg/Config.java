package tv.mongotheelder.pitg;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class Config {
    public static final String GLAZING_TOOL_CONFIG = "glazing_tool";
    public static final String GLASS_KILN_CONFIG = "glass_kiln";

    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue ENABLE_PANE_BREAK;
    public static ForgeConfigSpec.BooleanValue ENABLE_UNBREAKABLE;

    public static ForgeConfigSpec.IntValue GLASS_KILN_SMELT_TIME;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        setupItems(COMMON_BUILDER);
        COMMON_CONFIG = COMMON_BUILDER.build();

    }

    private static void setupItems(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("Glazing Tool settings").push(GLAZING_TOOL_CONFIG);

        ENABLE_PANE_BREAK = COMMON_BUILDER.comment(" Let the glazing tool drop panes (like silk touch) by sneaking while in BREAK mode").define("glazing_tool_break_pane", false);
        ENABLE_UNBREAKABLE = COMMON_BUILDER.comment(" Allow glazing tool to mark glass panes as unbreakable while in UNBREAKING mode").define("unbreakable_pane", false);

        COMMON_BUILDER.pop().comment("Glass Kiln settings").push(GLASS_KILN_CONFIG);

        GLASS_KILN_SMELT_TIME = COMMON_BUILDER.comment(" Item smelt time (ticks)").defineInRange("glass_furnace_smelt_time", 200, 20, 5000);

        COMMON_BUILDER.pop();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }

}

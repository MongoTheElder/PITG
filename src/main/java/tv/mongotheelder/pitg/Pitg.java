package tv.mongotheelder.pitg;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mongotheelder.pitg.setup.*;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("pitg")
public class Pitg {
    public static final String GLAZING_TOOL_TAG = "glazing_tool_tag";
    public static final String MODID = "pitg";

    public static final String[] COLORS = { "", "white", "red", "blue", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "brown", "green", "black" };

    public static IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    private static final Logger LOGGER = LogManager.getLogger();

    public Pitg() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        Registration.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
    }
}

package tv.mongotheelder.pitg.setup;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tv.mongotheelder.pitg.Pitg;

@Mod.EventBusSubscriber(modid = Pitg.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(Registration.GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.RED_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.WHITE_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.BLUE_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.ORANGE_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.MAGENTA_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.LIGHT_BLUE_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.YELLOW_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.LIME_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.PINK_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.GRAY_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.LIGHT_GRAY_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.CYAN_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.PURPLE_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.BROWN_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.GREEN_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.BLACK_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
    }
}

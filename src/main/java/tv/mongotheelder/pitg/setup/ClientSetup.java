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
        //MinecraftForge.EVENT_BUS.addListener(InWorldRenderer::render);
        //MinecraftForge.EVENT_BUS.addListener(AfterLivingRenderer::render);
        RenderTypeLookup.setRenderLayer(Registration.GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.RED_STAINED_GLASS_PANE.get(), RenderType.getTranslucent());
    }
}

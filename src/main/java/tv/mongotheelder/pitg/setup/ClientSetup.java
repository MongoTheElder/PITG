package tv.mongotheelder.pitg.setup;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tv.mongotheelder.pitg.Pitg;

@Mod.EventBusSubscriber(modid = Pitg.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(Registration.GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.DUAL_GLASS_PANE.get(), RenderType.getTranslucent());

        for (DyeColor color : DyeColor.values()) {
            RenderTypeLookup.setRenderLayer(Registration.GLASS_PANES.get(color).get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registration.DUAL_GLASS_PANES.get(color).get(), RenderType.getTranslucent());
        }

    }
}

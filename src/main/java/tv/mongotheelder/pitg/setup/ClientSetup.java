package tv.mongotheelder.pitg.setup;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.items.GlazingTool;
import tv.mongotheelder.pitg.items.GlazingToolMode;

@Mod.EventBusSubscriber(modid = Pitg.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(Registration.GLASS_PANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(Registration.DUAL_GLASS_PANE.get(), RenderType.getTranslucent());

        for (DyeColor color : DyeColor.values()) {
            RenderTypeLookup.setRenderLayer(Registration.GLASS_PANES.get(color).get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registration.DUAL_GLASS_PANES.get(color).get(), RenderType.getTranslucent());
        }

        // Register custom item property for the Glazing Tool to change models for the UNBREAKING state
        ItemModelsProperties.registerProperty(Registration.GLAZING_TOOL_ITEM.get(), new ResourceLocation("pitg:unbreaking"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity != null) {
                if (livingEntity.getHeldItemMainhand().getItem() instanceof GlazingTool) {
                    return livingEntity.getHeldItemMainhand() == itemStack && GlazingTool.getMode(itemStack) == GlazingToolMode.UNBREAKABLE ? 1.0f : 0.0f;
                }
            }
            return 0.0F;
        });
    }
}

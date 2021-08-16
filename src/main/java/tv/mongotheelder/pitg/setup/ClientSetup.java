package tv.mongotheelder.pitg.setup;

import net.minecraft.client.gui.ScreenManager;
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
import tv.mongotheelder.pitg.screens.GlassKilnScreen;
import tv.mongotheelder.pitg.screens.GlassPaneTableScreen;

@Mod.EventBusSubscriber(modid = Pitg.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        ScreenManager.register(Registration.GLASS_KILN_CONTAINER.get(), GlassKilnScreen::new);
        ScreenManager.register(Registration.GLASS_PANE_TABLE_CONTAINER.get(), GlassPaneTableScreen::new);

        RenderTypeLookup.setRenderLayer(Registration.GLASS_PANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(Registration.DUAL_GLASS_PANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(Registration.HORIZONTAL_GLASS_PANE.get(), RenderType.translucent());

        for (DyeColor color : DyeColor.values()) {
            RenderTypeLookup.setRenderLayer(Registration.STAINED_GLASS_PANES.get(color).get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(Registration.TINTED_GLASS_PANES.get(color).get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(Registration.PLAIN_GLASS_PANES.get(color).get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(Registration.STAINED_DUAL_GLASS_PANES.get(color).get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(Registration.TINTED_DUAL_GLASS_PANES.get(color).get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(Registration.PLAIN_DUAL_GLASS_PANES.get(color).get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(Registration.STAINED_HORIZONTAL_GLASS_PANES.get(color).get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(Registration.TINTED_HORIZONTAL_GLASS_PANES.get(color).get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(Registration.PLAIN_HORIZONTAL_GLASS_PANES.get(color).get(), RenderType.translucent());
        }

        // Register custom item property for the Glazing Tool to change models for the UNBREAKING state
        ItemModelsProperties.register(Registration.GLAZING_TOOL_ITEM.get(), new ResourceLocation("pitg:unbreaking"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity != null) {
                if (livingEntity.getMainHandItem().getItem() instanceof GlazingTool) {
                    return livingEntity.getMainHandItem() == itemStack && GlazingTool.getMode(itemStack) == GlazingToolMode.UNBREAKABLE ? 1.0f : 0.0f;
                }
            }
            return 0.0F;
        });
    }
}

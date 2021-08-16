package tv.mongotheelder.pitg.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import tv.mongotheelder.pitg.Pitg;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new PitgRecipeProvider(generator));
            generator.addProvider(new PitgLootTableProvider(generator));
            generator.addProvider(new PitgItemsTagsProvider(generator, new BlockTagsProvider(generator, Pitg.MODID, event.getExistingFileHelper()), event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(new PitgBlockStateProvider(generator, event.getExistingFileHelper()));
            generator.addProvider(new PitgItemProvider(generator, event.getExistingFileHelper()));
        }
    }
}

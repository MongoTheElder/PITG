package tv.mongotheelder.pitg.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.setup.Registration;

public class PitgItemsTagsProvider extends ItemTagsProvider {
    public PitgItemsTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider,
                                 ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, Pitg.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        ITag.INamedTag<Item> newTag = ItemTags.bind("pitg:glass_panes");
        ITag.INamedTag<Item> allTag = ItemTags.bind("pitg:all_glass_panes");
        tag(newTag)
                .add(Registration.GLASS_PANE_ITEM.get())
                .add(Registration.HORIZONTAL_GLASS_PANE_ITEM.get())
                .add(Items.GLASS_PANE.asItem());
        tag(allTag).addTags(newTag);
        for (DyeColor color : DyeColor.values()) {
            ITag.INamedTag<Item> newColorTag = ItemTags.bind("pitg:" + color + "_glass_panes");
            tag(newColorTag)
                    .add(Registration.STAINED_GLASS_PANE_ITEMS.get(color).get())
                    .add(Registration.STAINED_HORIZONTAL_GLASS_PANE_ITEMS.get(color).get())
                    .add(Registration.TINTED_GLASS_PANE_ITEMS.get(color).get())
                    .add(Registration.TINTED_HORIZONTAL_GLASS_PANE_ITEMS.get(color).get())
                    .add(Registration.PLAIN_GLASS_PANE_ITEMS.get(color).get())
                    .add(Registration.PLAIN_HORIZONTAL_GLASS_PANE_ITEMS.get(color).get())
                    .add(PitgRecipeProvider.lookupVanillaPaneBlock(color).asItem());
            tag(allTag).addTags(newColorTag);
        }

        ITag.INamedTag<Item> horizontalTag = ItemTags.bind("pitg:horizontal_glass_panes");
        tag(horizontalTag).add(Registration.HORIZONTAL_GLASS_PANE_ITEM.get());
        for (RegistryObject<Item> regOb : Registration.STAINED_HORIZONTAL_GLASS_PANE_ITEMS.values()) {
            tag(horizontalTag).add(regOb.get());
        }
        for (RegistryObject<Item> regOb : Registration.TINTED_HORIZONTAL_GLASS_PANE_ITEMS.values()) {
            tag(horizontalTag).add(regOb.get());
        }
        for (RegistryObject<Item> regOb : Registration.PLAIN_HORIZONTAL_GLASS_PANE_ITEMS.values()) {
            tag(horizontalTag).add(regOb.get());
        }

        ITag.INamedTag<Item> dualTag = ItemTags.bind("pitg:dual_glass_panes");
        tag(dualTag).add(Registration.DUAL_GLASS_PANE_ITEM.get());
        for (RegistryObject<Item> regOb : Registration.STAINED_DUAL_GLASS_PANE_ITEMS.values()) {
            tag(dualTag).add(regOb.get());
        }
        for (RegistryObject<Item> regOb : Registration.TINTED_DUAL_GLASS_PANE_ITEMS.values()) {
            tag(dualTag).add(regOb.get());
        }
        for (RegistryObject<Item> regOb : Registration.PLAIN_DUAL_GLASS_PANE_ITEMS.values()) {
            tag(dualTag).add(regOb.get());
        }
    }
}

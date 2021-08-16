package tv.mongotheelder.pitg.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.setup.Registration;

public class PitgItemProvider extends ItemModelProvider {
    public PitgItemProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Pitg.MODID, existingFileHelper);
    }

    public void itemModel(String itemPath, String texturePath, String itemGlyph) {
        withExistingParent(itemPath, "item/handheld").texture("layer0", modLoc(texturePath)).texture("layer1", modLoc("item/" + itemGlyph)).transforms().transform(ModelBuilder.Perspective.GUI).scale(0.8f, 0.8f, 0.8f);
    }

    public void makeSingleItemModel(String color, Item item, String colorSpace, String itemGlyph) {
        String actualColorName = (color.equals("") ? "" : color + colorSpace);
        String texturePath = BLOCK_FOLDER + "/" + actualColorName + "glass_pane";
        String itemPath = ITEM_FOLDER + "/" + item.asItem().getRegistryName().getPath();

        itemModel(itemPath, texturePath, itemGlyph);
    }

    public void makeDualItemModel(String color, Item item, String colorSpace, String itemGlyph) {
        String actualColorName = (color.equals("") ? "" : color + colorSpace);
        String texturePath = BLOCK_FOLDER + "/" + actualColorName + "glass_pane";
        String itemPath = ITEM_FOLDER + "/" + item.asItem().getRegistryName().getPath();

        itemModel(itemPath, texturePath, itemGlyph);
    }

    public void makeHorizontalItemModel(String color, Item item, String colorSpace, String itemGlyph) {
        String actualColorName = (color.equals("") ? "" : color + colorSpace);
        String texturePath = BLOCK_FOLDER + "/" + actualColorName + "glass_pane";
        String itemPath = ITEM_FOLDER + "/" + item.asItem().getRegistryName().getPath();

        itemModel(itemPath, texturePath, itemGlyph);
    }

    @Override
    protected void registerModels() {
        makeSingleItemModel("", Registration.GLASS_PANE_ITEM.get(), "", "s");
        makeDualItemModel("", Registration.DUAL_GLASS_PANE_ITEM.get(), "", "d");
        makeHorizontalItemModel("", Registration.HORIZONTAL_GLASS_PANE_ITEM.get(), "", "h");

        for (DyeColor color : DyeColor.values()) {
            String colorName = color.getSerializedName().toLowerCase();
            makeSingleItemModel(colorName, Registration.STAINED_GLASS_PANE_ITEMS.get(color).get(), "_stained_", "s");
            makeSingleItemModel(colorName, Registration.TINTED_GLASS_PANE_ITEMS.get(color).get(), "_tinted_", "st");
            makeSingleItemModel(colorName, Registration.PLAIN_GLASS_PANE_ITEMS.get(color).get(), "_plain_", "sp");
            makeDualItemModel(colorName, Registration.STAINED_DUAL_GLASS_PANE_ITEMS.get(color).get(), "_stained_", "d");
            makeDualItemModel(colorName, Registration.TINTED_DUAL_GLASS_PANE_ITEMS.get(color).get(), "_tinted_", "dt");
            makeDualItemModel(colorName, Registration.PLAIN_DUAL_GLASS_PANE_ITEMS.get(color).get(), "_plain_", "dp");
            makeHorizontalItemModel(colorName, Registration.STAINED_HORIZONTAL_GLASS_PANE_ITEMS.get(color).get(), "_stained_", "h");
            makeHorizontalItemModel(colorName, Registration.TINTED_HORIZONTAL_GLASS_PANE_ITEMS.get(color).get(), "_tinted_", "ht");
            makeHorizontalItemModel(colorName, Registration.PLAIN_HORIZONTAL_GLASS_PANE_ITEMS.get(color).get(), "_plain_", "hp");
        }
    }
}

package tv.mongotheelder.pitg.datagen;

import net.minecraft.data.DataGenerator;
import tv.mongotheelder.pitg.setup.Registration;

public class LootTables extends BaseLootTableProvider {
    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.GLASS_PANE.get(), createSilkTouchTable("glasspane", Registration.GLASS_PANE.get()));
        lootTables.put(Registration.WHITE_STAINED_GLASS_PANE.get(), createSilkTouchTable("white_stained_glasspane", Registration.WHITE_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.RED_STAINED_GLASS_PANE.get(), createSilkTouchTable("red_stained_glasspane", Registration.RED_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.BLUE_STAINED_GLASS_PANE.get(), createSilkTouchTable("blue_stained_glasspane", Registration.BLUE_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.ORANGE_STAINED_GLASS_PANE.get(), createSilkTouchTable("orange_stained_glasspane", Registration.ORANGE_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.MAGENTA_STAINED_GLASS_PANE.get(), createSilkTouchTable("magenta_stained_glasspane", Registration.MAGENTA_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.LIGHT_BLUE_STAINED_GLASS_PANE.get(), createSilkTouchTable("light_blue_stained_glasspane", Registration.LIGHT_BLUE_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.YELLOW_STAINED_GLASS_PANE.get(), createSilkTouchTable("yellow_stained_glasspane", Registration.YELLOW_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.LIME_STAINED_GLASS_PANE.get(), createSilkTouchTable("lime_stained_glasspane", Registration.LIME_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.PINK_STAINED_GLASS_PANE.get(), createSilkTouchTable("pink_stained_glasspane", Registration.PINK_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.GRAY_STAINED_GLASS_PANE.get(), createSilkTouchTable("gray_stained_glasspane", Registration.GRAY_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.LIGHT_GRAY_STAINED_GLASS_PANE.get(), createSilkTouchTable("light_gray_stained_glasspane", Registration.LIGHT_GRAY_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.CYAN_STAINED_GLASS_PANE.get(), createSilkTouchTable("cyan_stained_glasspane", Registration.CYAN_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.PURPLE_STAINED_GLASS_PANE.get(), createSilkTouchTable("purple_stained_glasspane", Registration.PURPLE_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.BROWN_STAINED_GLASS_PANE.get(), createSilkTouchTable("brown_stained_glasspane", Registration.BROWN_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.GREEN_STAINED_GLASS_PANE.get(), createSilkTouchTable("green_stained_glasspane", Registration.GREEN_STAINED_GLASS_PANE.get()));
        lootTables.put(Registration.BLACK_STAINED_GLASS_PANE.get(), createSilkTouchTable("black_stained_glasspane", Registration.BLACK_STAINED_GLASS_PANE.get()));
    }
}

package tv.mongotheelder.pitg.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.DyeColor;
import tv.mongotheelder.pitg.setup.Registration;

public class PitgLootTableProvider extends BaseLootTableProvider {
    public PitgLootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.GLASS_PANE.get(), createSilkTouchTable("glasspane", Registration.GLASS_PANE.get()));
        lootTables.put(Registration.DUAL_GLASS_PANE.get(), createSilkTouchTable("dualglasspane", Registration.DUAL_GLASS_PANE.get()));
        lootTables.put(Registration.HORIZONTAL_GLASS_PANE.get(), createSilkTouchTable("horizontal_glasspane", Registration.HORIZONTAL_GLASS_PANE.get()));

        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName().toLowerCase();
            lootTables.put(Registration.STAINED_GLASS_PANES.get(color).get(), createSilkTouchTable(name + "_stained_glasspane", Registration.STAINED_GLASS_PANES.get(color).get()));
            lootTables.put(Registration.STAINED_HORIZONTAL_GLASS_PANES.get(color).get(), createSilkTouchTable(name + "_stained_horizontal_glasspane", Registration.STAINED_HORIZONTAL_GLASS_PANES.get(color).get()));
            lootTables.put(Registration.STAINED_DUAL_GLASS_PANES.get(color).get(), createSilkTouchTable(name + "_stained_dualglasspane", Registration.STAINED_DUAL_GLASS_PANES.get(color).get()));
            lootTables.put(Registration.TINTED_GLASS_PANES.get(color).get(), createSilkTouchTable(name + "_tinted_glasspane", Registration.TINTED_GLASS_PANES.get(color).get()));
            lootTables.put(Registration.TINTED_HORIZONTAL_GLASS_PANES.get(color).get(), createSilkTouchTable(name + "_tinted_horizontal_glasspane", Registration.TINTED_HORIZONTAL_GLASS_PANES.get(color).get()));
            lootTables.put(Registration.TINTED_DUAL_GLASS_PANES.get(color).get(), createSilkTouchTable(name + "_tinted_dualglasspane", Registration.TINTED_DUAL_GLASS_PANES.get(color).get()));
            lootTables.put(Registration.PLAIN_GLASS_PANES.get(color).get(), createSilkTouchTable(name + "_plain_glasspane", Registration.PLAIN_GLASS_PANES.get(color).get()));
            lootTables.put(Registration.PLAIN_HORIZONTAL_GLASS_PANES.get(color).get(), createSilkTouchTable(name + "_plain_horizontal_glasspane", Registration.PLAIN_HORIZONTAL_GLASS_PANES.get(color).get()));
            lootTables.put(Registration.PLAIN_DUAL_GLASS_PANES.get(color).get(), createSilkTouchTable(name + "_plain_dualglasspane", Registration.PLAIN_DUAL_GLASS_PANES.get(color).get()));
        }
    }
}

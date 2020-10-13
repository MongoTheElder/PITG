package tv.mongotheelder.pitg.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.DyeColor;
import tv.mongotheelder.pitg.setup.Registration;

public class LootTables extends BaseLootTableProvider {
    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.GLASS_PANE.get(), createSilkTouchTable("glasspane", Registration.GLASS_PANE.get()));
        lootTables.put(Registration.DUAL_GLASS_PANE.get(), createSilkTouchTable("dualglasspane", Registration.DUAL_GLASS_PANE.get()));

        for (DyeColor color : DyeColor.values()) {
            String name = color.getString().toLowerCase() + "_stained_";
            lootTables.put(Registration.GLASS_PANES.get(color).get(), createSilkTouchTable(name + "glasspane", Registration.GLASS_PANES.get(color).get()));
            lootTables.put(Registration.DUAL_GLASS_PANES.get(color).get(), createSilkTouchTable(name + "dualglasspane", Registration.DUAL_GLASS_PANES.get(color).get()));
        }

    }
}

package tv.mongotheelder.pitg.blocks;

import net.minecraft.item.DyeColor;

public class StainedDualGlassPane extends DualGlassPane {
    private final DyeColor color;

    public StainedDualGlassPane(DyeColor colorIn, Properties properties) {
        super(properties);
        this.color = colorIn;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, Boolean.FALSE)
                .setValue(EAST, Boolean.FALSE)
                .setValue(SOUTH, Boolean.FALSE)
                .setValue(WEST, Boolean.FALSE)
                .setValue(WATERLOGGED, Boolean.FALSE));
    }
}

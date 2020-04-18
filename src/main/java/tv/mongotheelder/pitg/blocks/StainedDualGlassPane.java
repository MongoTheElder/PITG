package tv.mongotheelder.pitg.blocks;

import net.minecraft.item.DyeColor;

public class StainedDualGlassPane extends DualGlassPane {
    private final DyeColor color;

    public StainedDualGlassPane(DyeColor colorIn, Properties properties) {
        super(properties);
        this.color = colorIn;
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(NORTH, Boolean.FALSE)
                .with(EAST, Boolean.FALSE)
                .with(SOUTH, Boolean.FALSE)
                .with(WEST, Boolean.FALSE)
                .with(WATERLOGGED, Boolean.FALSE));
    }
}

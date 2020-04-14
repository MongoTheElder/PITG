package tv.mongotheelder.pitg.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;

public class StainedGlassPane extends GlassPane {
    private final DyeColor color;

    public StainedGlassPane(DyeColor colorIn, Block.Properties properties) {
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

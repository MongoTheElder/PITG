package tv.mongotheelder.pitg.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.item.DyeColor;

public class StainedGlassPane extends GlassPane {
    private final DyeColor color;

    public StainedGlassPane(DyeColor colorIn, AbstractBlock.Properties properties) {
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

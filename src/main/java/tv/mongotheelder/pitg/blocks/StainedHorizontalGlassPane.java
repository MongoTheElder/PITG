package tv.mongotheelder.pitg.blocks;

import net.minecraft.item.DyeColor;

public class StainedHorizontalGlassPane extends HorizontalGlassPane {
    private final DyeColor color;

    public StainedHorizontalGlassPane(DyeColor colorIn, Properties properties) {
        super(properties);
        this.color = colorIn;
    }
}

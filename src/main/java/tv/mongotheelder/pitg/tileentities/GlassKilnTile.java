package tv.mongotheelder.pitg.tileentities;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import tv.mongotheelder.pitg.containers.GlassKilnContainer;
import tv.mongotheelder.pitg.setup.Registration;

public class GlassKilnTile extends AbstractFurnaceTileEntity {
    public GlassKilnTile() {
        super(Registration.GLASS_KILN_TILE.get(), Registration.GLASS_KILN_TYPE);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("gui.pitg.glass_kiln_title");
    }

    @Override
    protected Container createMenu(int p_213906_1_, PlayerInventory p_213906_2_) {
        return new GlassKilnContainer(p_213906_1_, p_213906_2_, this, this.dataAccess);
    }
}

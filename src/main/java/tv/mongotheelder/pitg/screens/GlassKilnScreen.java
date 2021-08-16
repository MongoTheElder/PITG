package tv.mongotheelder.pitg.screens;

import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.containers.GlassKilnContainer;
import tv.mongotheelder.pitg.recipes.GlassKilnRecipeGui;

public class GlassKilnScreen extends AbstractFurnaceScreen<GlassKilnContainer> {

    public GlassKilnScreen(GlassKilnContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, new GlassKilnRecipeGui(), playerInventory, title, Pitg.GLASS_KILN_GUI_TEXTURE);
    }
}
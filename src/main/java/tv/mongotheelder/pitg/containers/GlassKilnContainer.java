package tv.mongotheelder.pitg.containers;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import tv.mongotheelder.pitg.setup.Registration;

public class GlassKilnContainer extends AbstractFurnaceContainer {

    public GlassKilnContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray dataAccess) {
        super(Registration.GLASS_KILN_CONTAINER.get(), Registration.GLASS_KILN_TYPE, RecipeBookCategory.BLAST_FURNACE, id, playerInventory, inventory, dataAccess);
    }

    public GlassKilnContainer(int id, PlayerInventory playerInventory, PacketBuffer packetBuffer) {
        super(Registration.GLASS_KILN_CONTAINER.get(), Registration.GLASS_KILN_TYPE, RecipeBookCategory.BLAST_FURNACE, id, playerInventory);
    }
}

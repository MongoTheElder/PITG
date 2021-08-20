package tv.mongotheelder.pitg.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import tv.mongotheelder.pitg.setup.Registration;

public class GlassPaneTableRecipe extends SingleItemRecipe {
    public GlassPaneTableRecipe(ResourceLocation p_i50021_1_, String p_i50021_2_, Ingredient ingredient, ItemStack stack) {
        super(Registration.GLASS_PANE_TABLE_TYPE, Registration.GLASS_PANE_TABLE_RECIPE_SERIALIZER.get(), p_i50021_1_, p_i50021_2_, ingredient, stack);
    }

    @Override
    public boolean matches(IInventory inventory, World world) {
        return this.ingredient.test(inventory.getItem(0));
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(Registration.GLASS_PANE_TABLE.get());
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}

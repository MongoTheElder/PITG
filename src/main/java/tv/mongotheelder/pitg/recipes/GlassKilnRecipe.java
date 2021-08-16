package tv.mongotheelder.pitg.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import tv.mongotheelder.pitg.setup.Registration;

public class GlassKilnRecipe extends AbstractCookingRecipe {
    public GlassKilnRecipe(ResourceLocation p_i50031_1_, String p_i50031_2_, Ingredient p_i50031_3_, ItemStack p_i50031_4_, float p_i50031_5_, int p_i50031_6_) {
        super(Registration.GLASS_KILN_TYPE, p_i50031_1_, p_i50031_2_, p_i50031_3_, p_i50031_4_, p_i50031_5_, p_i50031_6_);
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(Registration.GLASS_KILN.get());
    }

    public IRecipeSerializer<?> getSerializer() {
        return Registration.GLASS_KILN_RECIPE_SERIALIZER.get();
    }
}

package tv.mongotheelder.pitg.datagen;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapelessRecipeBuilder;
import tv.mongotheelder.pitg.setup.Registration;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(Registration.GLASS_PANE.get())
                .addIngredient(Blocks.GLASS_PANE)
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(Blocks.GLASS_PANE)
                .addIngredient(Registration.GLASS_PANE.get())
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(Registration.RED_STAINED_GLASS_PANE.get())
                .addIngredient(Blocks.RED_STAINED_GLASS_PANE)
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(Blocks.RED_STAINED_GLASS_PANE)
                .addIngredient(Registration.RED_STAINED_GLASS_PANE.get())
                .build(consumer);
    }
}

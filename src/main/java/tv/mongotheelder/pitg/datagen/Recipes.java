package tv.mongotheelder.pitg.datagen;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
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

    protected void glassPaneRecipe(Consumer<IFinishedRecipe> consumer, Block result, Block ingredient, String group) {
        ShapelessRecipeBuilder.shapelessRecipe(result)
                .addIngredient(ingredient, 1)
                .addCriterion("has_glass", InventoryChangeTrigger.Instance.forItems(Blocks.GLASS))
                .setGroup(group)
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ingredient)
                .addIngredient(result, 1)
                .addCriterion("has_glass", InventoryChangeTrigger.Instance.forItems(Blocks.GLASS))
                .setGroup(group)
                .build(consumer);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        glassPaneRecipe(consumer, Registration.GLASS_PANE.get(), Blocks.GLASS_PANE, "glass_pane");
        glassPaneRecipe(consumer, Registration.RED_STAINED_GLASS_PANE.get(), Blocks.RED_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.WHITE_STAINED_GLASS_PANE.get(), Blocks.WHITE_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.BLUE_STAINED_GLASS_PANE.get(), Blocks.BLUE_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.ORANGE_STAINED_GLASS_PANE.get(), Blocks.ORANGE_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.MAGENTA_STAINED_GLASS_PANE.get(), Blocks.MAGENTA_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.LIGHT_BLUE_STAINED_GLASS_PANE.get(), Blocks.LIGHT_BLUE_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.YELLOW_STAINED_GLASS_PANE.get(), Blocks.YELLOW_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.LIME_STAINED_GLASS_PANE.get(), Blocks.LIME_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.PINK_STAINED_GLASS_PANE.get(), Blocks.PINK_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.GRAY_STAINED_GLASS_PANE.get(), Blocks.GRAY_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.LIGHT_GRAY_STAINED_GLASS_PANE.get(), Blocks.LIGHT_GRAY_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.CYAN_STAINED_GLASS_PANE.get(), Blocks.CYAN_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.PURPLE_STAINED_GLASS_PANE.get(), Blocks.PURPLE_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.BROWN_STAINED_GLASS_PANE.get(), Blocks.BROWN_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.GREEN_STAINED_GLASS_PANE.get(), Blocks.GREEN_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.BLACK_STAINED_GLASS_PANE.get(), Blocks.BLACK_STAINED_GLASS_PANE, "stained_glass_pane");
    }
}

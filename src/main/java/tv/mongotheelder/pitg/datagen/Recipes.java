package tv.mongotheelder.pitg.datagen;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
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
                .build(consumer, ingredient.getRegistryName()+"_from_edge_pane");
    }

    protected void dualGlassPaneRecipe(Consumer<IFinishedRecipe> consumer, Block result, Block ingredient, String group) {
        ShapelessRecipeBuilder.shapelessRecipe(result)
                .addIngredient(ingredient, 2)
                .addCriterion("has_glass", InventoryChangeTrigger.Instance.forItems(Blocks.GLASS))
                .setGroup(group)
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ingredient, 2)
                .addIngredient(result, 1)
                .addCriterion("has_glass", InventoryChangeTrigger.Instance.forItems(Blocks.GLASS))
                .setGroup(group)
                .build(consumer, ingredient.getRegistryName()+"_from_dual_pane");
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

        dualGlassPaneRecipe(consumer, Registration.DUAL_GLASS_PANE.get(), Registration.GLASS_PANE.get(), "glass_pane");
        dualGlassPaneRecipe(consumer, Registration.RED_STAINED_DUAL_GLASS_PANE.get(), Registration.RED_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.WHITE_STAINED_DUAL_GLASS_PANE.get(), Registration.WHITE_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.BLUE_STAINED_DUAL_GLASS_PANE.get(), Registration.BLUE_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.ORANGE_STAINED_DUAL_GLASS_PANE.get(), Registration.ORANGE_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.MAGENTA_STAINED_DUAL_GLASS_PANE.get(), Registration.MAGENTA_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.LIGHT_BLUE_STAINED_DUAL_GLASS_PANE.get(), Registration.LIGHT_BLUE_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.YELLOW_STAINED_DUAL_GLASS_PANE.get(), Registration.YELLOW_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.LIME_STAINED_DUAL_GLASS_PANE.get(), Registration.LIME_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.PINK_STAINED_DUAL_GLASS_PANE.get(), Registration.PINK_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.GRAY_STAINED_DUAL_GLASS_PANE.get(), Registration.GRAY_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.LIGHT_GRAY_STAINED_DUAL_GLASS_PANE.get(), Registration.LIGHT_GRAY_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.CYAN_STAINED_DUAL_GLASS_PANE.get(), Registration.CYAN_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.PURPLE_STAINED_DUAL_GLASS_PANE.get(), Registration.PURPLE_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.BROWN_STAINED_DUAL_GLASS_PANE.get(), Registration.BROWN_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.GREEN_STAINED_DUAL_GLASS_PANE.get(), Registration.GREEN_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.BLACK_STAINED_DUAL_GLASS_PANE.get(), Registration.BLACK_STAINED_GLASS_PANE.get(), "stained_glass_pane");

        ShapelessRecipeBuilder.shapelessRecipe(Registration.GLAZING_TOOL_ITEM.get())
                .addIngredient(Tags.Items.INGOTS_IRON)
                .addIngredient(Tags.Items.DYES_GREEN)
                .addIngredient(Tags.Items.GLASS_PANES)
                .setGroup("tools")
                .addCriterion("glass", InventoryChangeTrigger.Instance.forItems(Blocks.GLASS))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(Registration.GREEN_DYE_ITEM.get())
                .addIngredient(Tags.Items.DYES_BLUE)
                .addIngredient(Tags.Items.DYES_YELLOW)
                .setGroup("green_dye")
                .addCriterion("has_yellow_dye", InventoryChangeTrigger.Instance.forItems(Items.YELLOW_DYE))
                .addCriterion("has_blue_dye", InventoryChangeTrigger.Instance.forItems(Items.BLUE_DYE))
                .build(consumer);
    }
}

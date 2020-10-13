package tv.mongotheelder.pitg.datagen;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.DyeColor;
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
        dualGlassPaneRecipe(consumer, Registration.DUAL_GLASS_PANE.get(), Registration.GLASS_PANE.get(), "glass_pane");
        for (DyeColor color : DyeColor.values()) {
            glassPaneRecipe(consumer, Registration.GLASS_PANES.get(color).get(), lookupVanillaPaneBlock(color), "stained_glass_pane");
            dualGlassPaneRecipe(consumer, Registration.DUAL_GLASS_PANES.get(color).get(), lookupVanillaPaneBlock(color), "stained_glass_pane");
        }
    }

    // TODO: Integrate this better without explicit block references
    private Block lookupVanillaPaneBlock(DyeColor color) {
        switch (color) {
            case RED:
                return Blocks.RED_STAINED_GLASS_PANE;
            default:
            case WHITE:
                return Blocks.WHITE_STAINED_GLASS_PANE;
            case BLACK:
                return Blocks.BLACK_STAINED_GLASS_PANE;
            case BLUE:
                return Blocks.BLUE_STAINED_GLASS_PANE;
            case CYAN:
                return Blocks.CYAN_STAINED_GLASS_PANE;
            case GRAY:
                return Blocks.GRAY_STAINED_GLASS_PANE;
            case LIME:
                return Blocks.LIME_STAINED_GLASS_PANE;
            case PINK:
                return Blocks.PINK_STAINED_GLASS_PANE;
            case BROWN:
                return Blocks.BROWN_STAINED_GLASS_PANE;
            case GREEN:
                return Blocks.GREEN_STAINED_GLASS_PANE;
            case ORANGE:
                return Blocks.ORANGE_STAINED_GLASS_PANE;
            case PURPLE:
                return Blocks.PURPLE_STAINED_GLASS_PANE;
            case YELLOW:
                return Blocks.YELLOW_STAINED_GLASS_PANE;
            case MAGENTA:
                return Blocks.MAGENTA_STAINED_GLASS_PANE;
            case LIGHT_BLUE:
                return Blocks.LIGHT_BLUE_STAINED_GLASS_PANE;
            case LIGHT_GRAY:
                return Blocks.LIGHT_GRAY_STAINED_GLASS_PANE;
        }
    }
}

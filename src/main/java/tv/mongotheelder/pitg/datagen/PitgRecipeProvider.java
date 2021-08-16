package tv.mongotheelder.pitg.datagen;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.setup.Registration;

import java.util.function.Consumer;

public class PitgRecipeProvider extends RecipeProvider {
    private static final ITag<Item> ALL_GLASS_PANE_TAG = ItemTags.bind("pitg:all_glass_panes");
    private static final ITag<Item> DUAL_GLASS_PANE_TAG = ItemTags.bind("pitg:dual_glass_panes");
    private static final ITag<Item> GLASS_PANE_TAG = Tags.Items.GLASS_PANES;

    public PitgRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    protected void glassPaneRecipe(Consumer<IFinishedRecipe> consumer, Block result, Block ingredient, String group, int requires, int gives) {
        create(consumer, result, ingredient, group, requires, gives).save(consumer);
    }

    protected void glassPaneRecipe(Consumer<IFinishedRecipe> consumer, Block result, Block ingredient, String group, int requires, int gives, String from) {
        create(consumer, result, ingredient, group, requires, gives).save(consumer, result.getRegistryName() + from);
    }

    private ShapelessRecipeBuilder create(Consumer<IFinishedRecipe> consumer, Block result, Block ingredient, String group, int requires, int gives) {
        return ShapelessRecipeBuilder.shapeless(result, gives)
                .requires(ingredient, requires)
                .unlockedBy("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .group(group);
    }

    // Yes, this is not the correct recipe type for a furnace but the CookingRecipeBuilder uses a IFactory interface that is not public so
    // you can't derive new classes from it. The only thing missing from the recipe is the cookingtime entry, so the default will have to do.
    private SingleItemRecipeBuilder kilnBuilder(Ingredient result, IItemProvider ingredient, int quantity) {
        return new SingleItemRecipeBuilder(Registration.GLASS_KILN_RECIPE_SERIALIZER.get(), result, ingredient, quantity);
    }

    private SingleItemRecipeBuilder glassPaneTableBuilder(Ingredient result, IItemProvider ingredient, int quantity) {
        return new SingleItemRecipeBuilder(Registration.GLASS_PANE_TABLE_RECIPE_SERIALIZER.get(), result, ingredient, quantity);
    }

    private void glassPanesInKiln(Consumer<IFinishedRecipe> consumer) {
        kilnBuilder(Ingredient.of(ALL_GLASS_PANE_TAG), Items.GLASS_PANE, 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc("glass_panes_in_glass_kiln"));
    }

    private void dualGlassPanesInKiln(Consumer<IFinishedRecipe> consumer) {
        kilnBuilder(Ingredient.of(DUAL_GLASS_PANE_TAG), Items.GLASS_PANE, 2)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc("dual_glass_panes_in_glass_kiln"));
    }

    private void sandInKiln(Consumer<IFinishedRecipe> consumer) {
        kilnBuilder(Ingredient.of(Tags.Items.SAND), Blocks.GLASS, 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc("sand_in_glass_kiln"));
    }

    private void glassInKiln(Consumer<IFinishedRecipe> consumer) {
        kilnBuilder(Ingredient.of(Tags.Items.GLASS), Blocks.GLASS, 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc("glass_in_glass_kiln"));
    }

    private void glassPanesInTable(Consumer<IFinishedRecipe> consumer) {
        kilnBuilder(Ingredient.of(ALL_GLASS_PANE_TAG), Items.GLASS_PANE, 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc("glass_panes_in_glass_kiln"));
    }

    private void glazingToolRecipe(Consumer<IFinishedRecipe> consumer, String group) {
        ShapelessRecipeBuilder.shapeless(Registration.GLAZING_TOOL_ITEM.get(), 1)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(Tags.Items.INGOTS_IRON)
                .requires(Tags.Items.GLASS_PANES)
                .unlockedBy("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .group(group)
                .save(consumer);
    }

    private void glassKilnRecipe(Consumer<IFinishedRecipe> consumer, String group) {
        ShapedRecipeBuilder.shaped(Registration.GLASS_KILN.get(), 1)
                .pattern(" T ")
                .pattern("TGT")
                .pattern("TTT")
                .define('T', Items.TERRACOTTA.getItem())
                .define('G', GLASS_PANE_TAG)
                .unlockedBy("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .group(group)
                .save(consumer);
    }

    private void glassPaneTableRecipe(Consumer<IFinishedRecipe> consumer, String group) {
        ShapedRecipeBuilder.shaped(Registration.GLASS_PANE_TABLE.get(), 1)
                .pattern("SGS")
                .pattern(" S ")
                .pattern("SSS")
                .define('S', Items.BLACKSTONE_SLAB.getItem())
                .define('G', GLASS_PANE_TAG)
                .unlockedBy("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .group(group)
                .save(consumer);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        glassPaneRecipe(consumer, Registration.GLASS_PANE.get(), Blocks.GLASS_PANE, "glass_pane", 1, 1);
        glassPaneRecipe(consumer, Registration.HORIZONTAL_GLASS_PANE.get(), Registration.GLASS_PANE.get(), "glass_pane", 1, 1);
        glassPaneRecipe(consumer, Blocks.GLASS_PANE, Registration.HORIZONTAL_GLASS_PANE.get(), "glass_pane", 1, 1, "_from_horizontal_glass_pane");
        glassPaneRecipe(consumer, Registration.DUAL_GLASS_PANE.get(), Registration.GLASS_PANE.get(), "glass_pane", 2, 1);
        glassPaneRecipe(consumer, Registration.GLASS_PANE.get(), Registration.DUAL_GLASS_PANE.get(), "glass_pane", 1, 2, "_from_dual_glass_pane");
        createTableRecipes(consumer);
        glazingToolRecipe(consumer, "tools");
        glassKilnRecipe(consumer, "tools");
        glassPaneTableRecipe(consumer, "tools");
        glassPanesInKiln(consumer);
        dualGlassPanesInKiln(consumer);
        sandInKiln(consumer);
        glassInKiln(consumer);
        glassPanesInTable(consumer);
        for (DyeColor color : DyeColor.values()) {
            glassPaneRecipe(consumer, Registration.STAINED_GLASS_PANES.get(color).get(), lookupVanillaPaneBlock(color), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.STAINED_HORIZONTAL_GLASS_PANES.get(color).get(), Registration.STAINED_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.TINTED_GLASS_PANES.get(color).get(), Registration.STAINED_HORIZONTAL_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.TINTED_HORIZONTAL_GLASS_PANES.get(color).get(), Registration.TINTED_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.PLAIN_GLASS_PANES.get(color).get(), Registration.TINTED_HORIZONTAL_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.PLAIN_HORIZONTAL_GLASS_PANES.get(color).get(), Registration.PLAIN_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, lookupVanillaPaneBlock(color), Registration.PLAIN_HORIZONTAL_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1, "_from_horizontal_glass_pane");

            glassPaneRecipe(consumer, Registration.STAINED_DUAL_GLASS_PANES.get(color).get(), lookupVanillaPaneBlock(color), color + "_dual_stained_glass_pane", 2, 1);
            glassPaneRecipe(consumer, Registration.TINTED_DUAL_GLASS_PANES.get(color).get(), Registration.STAINED_DUAL_GLASS_PANES.get(color).get(), color + "_dual_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.PLAIN_DUAL_GLASS_PANES.get(color).get(), Registration.TINTED_DUAL_GLASS_PANES.get(color).get(), color + "_dual_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, lookupVanillaPaneBlock(color), Registration.PLAIN_DUAL_GLASS_PANES.get(color).get(), color + "_dual_stained_glass_pane", 1, 2, "_from_dual_glass_pane");
            createColoredTableRecipes(consumer, color);
        }
    }

    // TODO: Integrate this better without explicit block references
    public static Block lookupVanillaPaneBlock(DyeColor color) {
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

    public void createColoredTableRecipes(Consumer<IFinishedRecipe> consumer, DyeColor color) {
        ITag<Item> inputs = ItemTags.bind("pitg:" + color + "_glass_panes");
        glassPaneTableBuilder(Ingredient.of(inputs), lookupVanillaPaneBlock(color), 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, color + "_stained_glass_pains_from_table");
        glassPaneTableBuilder(Ingredient.of(inputs), Registration.STAINED_GLASS_PANES.get(color).get(), 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc(color + "_stained_glass_pains_from_table"));
        glassPaneTableBuilder(Ingredient.of(inputs), Registration.PLAIN_GLASS_PANES.get(color).get(), 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc(color + "_plain_glass_pains_from_table"));
        glassPaneTableBuilder(Ingredient.of(inputs), Registration.TINTED_GLASS_PANES.get(color).get(), 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc(color + "_tinted_glass_pains_from_table"));
        glassPaneTableBuilder(Ingredient.of(inputs), Registration.STAINED_HORIZONTAL_GLASS_PANES.get(color).get(), 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc(color + "_stained_horizontal_glass_pains_from_table"));
        glassPaneTableBuilder(Ingredient.of(inputs), Registration.PLAIN_HORIZONTAL_GLASS_PANES.get(color).get(), 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc(color + "_plain_horizontal_glass_pains_from_table"));
        glassPaneTableBuilder(Ingredient.of(inputs), Registration.TINTED_HORIZONTAL_GLASS_PANES.get(color).get(), 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc(color + "_tinted_horizontal_glass_pains_from_table"));
    }

    public void createTableRecipes(Consumer<IFinishedRecipe> consumer) {
        ITag<Item> inputs = ItemTags.bind("pitg:glass_panes");
        glassPaneTableBuilder(Ingredient.of(inputs), Blocks.GLASS_PANE, 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, "glass_pain_from_table");
        glassPaneTableBuilder(Ingredient.of(inputs), Registration.GLASS_PANE.get(), 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc("glass_pain_from_table"));
        glassPaneTableBuilder(Ingredient.of(inputs), Registration.HORIZONTAL_GLASS_PANE.get(), 1)
                .unlocks("has_glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer, modLoc("horizontal_glass_pain_from_table"));
    }

    private static ResourceLocation modLoc(String path) {
        return new ResourceLocation(Pitg.MODID, path);
    }
}

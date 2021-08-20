package tv.mongotheelder.pitg.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.Tags;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.setup.Registration;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class PitgRecipeProvider extends RecipeProvider {
    private static final ITag<Item> ALL_GLASS_PANE_TAG = ItemTags.bind("pitg:all_glass_panes");
    private static final ITag<Item> DUAL_GLASS_PANE_TAG = ItemTags.bind("pitg:dual_glass_panes");
    private static final ITag<Item> GLASS_PANE_TAG = Tags.Items.GLASS_PANES;

    public PitgRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    protected void glassPaneRecipe(Consumer<IFinishedRecipe> consumer, String recipeName, Block result, Block ingredient, String group, int requires, int gives) {
        ResourceLocation loc = result.getRegistryName();
        consumer.accept(new GlassPaneResult(new ResourceLocation(loc.getNamespace(), loc.getPath() + recipeName), result.asItem(), gives, group, Ingredient.of(ingredient), requires));
    }

    protected void glassPaneRecipe(Consumer<IFinishedRecipe> consumer, Block result, Block ingredient, String group, int requires, int gives) {
        consumer.accept(new GlassPaneResult(result.getRegistryName(), result.asItem(), gives, group, Ingredient.of(ingredient), requires));
    }

    private void glassPaneTableBuilder(Consumer<IFinishedRecipe> consumer, ResourceLocation recipeName, Ingredient result, IItemProvider ingredient, int quantity) {
        consumer.accept(new GlassPaneTableResult(recipeName, "glass_pane", result, ingredient.asItem(), quantity));
    }

    private void glassPanesInBlastFurnace(Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.blasting(Ingredient.of(ALL_GLASS_PANE_TAG), Items.GLASS_PANE, 0f, 100)
                .unlockedBy("has_glass_pane", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS_PANE))
                .save(consumer, mcLoc("glass_panes_in_blast_furnace"));
    }

    private void dualGlassPanesInBlastFurnace(Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.blasting(Ingredient.of(DUAL_GLASS_PANE_TAG), Registration.DUAL_GLASS_PANE.get(), 0f, 100)
                .unlockedBy("has_glass_pane", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS_PANE))
                .save(consumer, mcLoc("dual_glass_panes_in_blast_furnace"));
    }

    private void glassInBlastFurnace(Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.blasting(Ingredient.of(Tags.Items.GLASS), Blocks.GLASS, 0f, 100)
                .unlockedBy("has_glass_pane", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS_PANE))
                .save(consumer, mcLoc("glass_in_blast_furnace"));
    }

    private void glazingToolRecipe(Consumer<IFinishedRecipe> consumer, String group) {
        ShapelessRecipeBuilder.shapeless(Registration.GLAZING_TOOL_ITEM.get(), 1)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(Tags.Items.INGOTS_IRON)
                .requires(Tags.Items.GLASS_PANES)
                .unlockedBy("has_glass_pane", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS_PANE))
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
                .unlockedBy("has_glass_pane", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS_PANE))
                .group(group)
                .save(consumer);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        glassPaneRecipe(consumer, Registration.GLASS_PANE.get(), Blocks.GLASS_PANE, "glass_pane", 1, 1);
        glassPaneRecipe(consumer, Registration.HORIZONTAL_GLASS_PANE.get(), Registration.GLASS_PANE.get(), "glass_pane", 1, 1);
        glassPaneRecipe(consumer, "_from_horizontal_glass_pane", Blocks.GLASS_PANE, Registration.HORIZONTAL_GLASS_PANE.get(), "glass_pane", 1, 1);
        glassPaneRecipe(consumer, Registration.DUAL_GLASS_PANE.get(), Registration.GLASS_PANE.get(), "glass_pane", 2, 1);
        glassPaneRecipe(consumer, "_from_vanilla_glass_pane", Registration.DUAL_GLASS_PANE.get(), Blocks.GLASS_PANE, "glass_pane", 2, 1);
        glassPaneRecipe(consumer, "_from_dual_glass_pane", Registration.GLASS_PANE.get(), Registration.DUAL_GLASS_PANE.get(), "glass_pane", 1, 2);
        createTableRecipes(consumer);
        glazingToolRecipe(consumer, "tools");
        glassPaneTableRecipe(consumer, "tools");
        glassPanesInBlastFurnace(consumer);
        dualGlassPanesInBlastFurnace(consumer);
        glassInBlastFurnace(consumer);
        for (DyeColor color : DyeColor.values()) {
            glassPaneRecipe(consumer, Registration.STAINED_GLASS_PANES.get(color).get(), lookupVanillaPaneBlock(color), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.STAINED_HORIZONTAL_GLASS_PANES.get(color).get(), Registration.STAINED_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.TINTED_GLASS_PANES.get(color).get(), Registration.STAINED_HORIZONTAL_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.TINTED_HORIZONTAL_GLASS_PANES.get(color).get(), Registration.TINTED_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.PLAIN_GLASS_PANES.get(color).get(), Registration.TINTED_HORIZONTAL_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.PLAIN_HORIZONTAL_GLASS_PANES.get(color).get(), Registration.PLAIN_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, "_from_horizontal_glass_pane", lookupVanillaPaneBlock(color), Registration.PLAIN_HORIZONTAL_GLASS_PANES.get(color).get(), color + "_stained_glass_pane", 1, 1);

            glassPaneRecipe(consumer, Registration.STAINED_DUAL_GLASS_PANES.get(color).get(), lookupVanillaPaneBlock(color), color + "_dual_stained_glass_pane", 2, 1);
            glassPaneRecipe(consumer, "_from_stained_glass_panes", Registration.STAINED_DUAL_GLASS_PANES.get(color).get(), Registration.STAINED_GLASS_PANES.get(color).get(), color + "_dual_stained_glass_pane", 2, 1);
            glassPaneRecipe(consumer, Registration.TINTED_DUAL_GLASS_PANES.get(color).get(), Registration.STAINED_DUAL_GLASS_PANES.get(color).get(), color + "_dual_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, Registration.PLAIN_DUAL_GLASS_PANES.get(color).get(), Registration.TINTED_DUAL_GLASS_PANES.get(color).get(), color + "_dual_stained_glass_pane", 1, 1);
            glassPaneRecipe(consumer, "_from_dual_glass_pane", lookupVanillaPaneBlock(color), Registration.PLAIN_DUAL_GLASS_PANES.get(color).get(), color + "_dual_stained_glass_pane", 1, 2);
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
        ITag<Item> panes = ItemTags.bind("pitg:" + color + "_glass_panes");
        glassPaneTableBuilder(consumer, mcLoc(color + "_stained_glass_pains_from_table"), Ingredient.of(panes), lookupVanillaPaneBlock(color), 1);
        glassPaneTableBuilder(consumer, modLoc(color + "_stained_glass_pains_from_table"), Ingredient.of(panes), Registration.STAINED_GLASS_PANES.get(color).get(), 1);
        glassPaneTableBuilder(consumer, modLoc(color + "_plain_glass_pains_from_table"), Ingredient.of(panes), Registration.PLAIN_GLASS_PANES.get(color).get(), 1);
        glassPaneTableBuilder(consumer, modLoc(color + "_tinted_glass_pains_from_table"), Ingredient.of(panes), Registration.TINTED_GLASS_PANES.get(color).get(), 1);
        glassPaneTableBuilder(consumer, modLoc(color + "_stained_horizontal_glass_pains_from_table"), Ingredient.of(panes), Registration.STAINED_HORIZONTAL_GLASS_PANES.get(color).get(), 1);
        glassPaneTableBuilder(consumer, modLoc(color + "_plain_horizontal_glass_pains_from_table"), Ingredient.of(panes), Registration.PLAIN_HORIZONTAL_GLASS_PANES.get(color).get(), 1);
        glassPaneTableBuilder(consumer, modLoc(color + "_tinted_horizontal_glass_pains_from_table"), Ingredient.of(panes), Registration.TINTED_HORIZONTAL_GLASS_PANES.get(color).get(), 1);

        ITag<Item> duelPanes = ItemTags.bind("pitg:" + color + "_dual_glass_panes");
        glassPaneTableBuilder(consumer, modLoc(color + "_stained_dual_glass_pains_from_table"), Ingredient.of(duelPanes), Registration.STAINED_DUAL_GLASS_PANES.get(color).get(), 1);
        glassPaneTableBuilder(consumer, modLoc(color + "_plain_dual_glass_pains_from_table"), Ingredient.of(duelPanes), Registration.PLAIN_DUAL_GLASS_PANES.get(color).get(), 1);
        glassPaneTableBuilder(consumer, modLoc(color + "_tinted_dual_glass_pains_from_table"), Ingredient.of(duelPanes), Registration.TINTED_DUAL_GLASS_PANES.get(color).get(), 1);
    }

    public void createTableRecipes(Consumer<IFinishedRecipe> consumer) {
        ITag<Item> inputs = ItemTags.bind("pitg:glass_panes");
        glassPaneTableBuilder(consumer, mcLoc("glass_pain_from_table"), Ingredient.of(inputs), Blocks.GLASS_PANE, 1);
        glassPaneTableBuilder(consumer, modLoc("glass_pain_from_table"), Ingredient.of(inputs), Registration.GLASS_PANE.get(), 1);
        glassPaneTableBuilder(consumer, modLoc("horizontal_glass_pain_from_table"), Ingredient.of(inputs), Registration.HORIZONTAL_GLASS_PANE.get(), 1);
    }

    private static ResourceLocation modLoc(String path) {
        return new ResourceLocation(Pitg.MODID, path);
    }

    private static ResourceLocation mcLoc(String path) {
        return new ResourceLocation(path);
    }

    private static class GlassPaneTableResult implements IFinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final Item result;
        private final int count;

        private final IRecipeSerializer<?> type;

        public GlassPaneTableResult(ResourceLocation recipeName, String group, Ingredient ingredient, Item result, int count) {
            this.id = recipeName;
            this.type = Registration.GLASS_PANE_TABLE_RECIPE_SERIALIZER.get();
            this.group = group;
            this.ingredient = ingredient;
            this.result = result;
            this.count = count;
        }

        public void serializeRecipeData(JsonObject p_218610_1_) {
            if (!this.group.isEmpty()) {
                p_218610_1_.addProperty("group", this.group);
            }

            p_218610_1_.add("ingredient", this.ingredient.toJson());
            p_218610_1_.addProperty("result", Registry.ITEM.getKey(this.result).toString());
            p_218610_1_.addProperty("count", this.count);
        }

        public ResourceLocation getId() {
            return this.id;
        }

        public IRecipeSerializer<?> getType() {
            return this.type;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }

    public static class GlassPaneResult implements IFinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final Ingredient ingredient;
        private final int requires;

        public GlassPaneResult(ResourceLocation recipeName, Item p_i48268_2_, int p_i48268_3_, String p_i48268_4_, Ingredient p_i48268_5_, int requires) {
            this.id = recipeName;
            this.result = p_i48268_2_;
            this.count = p_i48268_3_;
            this.group = p_i48268_4_;
            this.ingredient = p_i48268_5_;
            this.requires = requires;
        }

        public void serializeRecipeData(JsonObject p_218610_1_) {
            if (!this.group.isEmpty()) {
                p_218610_1_.addProperty("group", this.group);
            }

            JsonArray jsonarray = new JsonArray();
            for (int i = 0; i < requires; i++) {
                jsonarray.add(ingredient.toJson());
            }
            p_218610_1_.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }

            p_218610_1_.add("result", jsonobject);
        }

        public IRecipeSerializer<?> getType() {
            return IRecipeSerializer.SHAPELESS_RECIPE;
        }

        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}

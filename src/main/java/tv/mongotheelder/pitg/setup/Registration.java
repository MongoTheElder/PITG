package tv.mongotheelder.pitg.setup;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tv.mongotheelder.pitg.blocks.*;
import tv.mongotheelder.pitg.containers.GlassPaneTableContainer;
import tv.mongotheelder.pitg.items.GlazingTool;
import tv.mongotheelder.pitg.recipes.GlassPaneTableRecipe;
import tv.mongotheelder.pitg.recipes.GlassPaneTableRecipeSerializer;

import java.util.HashMap;
import java.util.Map;

import static tv.mongotheelder.pitg.Pitg.MODID;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);


    public static Map<DyeColor, RegistryObject<StainedGlassPane>> STAINED_GLASS_PANES = new HashMap<>();
    public static Map<DyeColor, RegistryObject<Item>> STAINED_GLASS_PANE_ITEMS = new HashMap<>();
    public static Map<DyeColor, RegistryObject<StainedDualGlassPane>> STAINED_DUAL_GLASS_PANES = new HashMap<>();
    public static Map<DyeColor, RegistryObject<Item>> STAINED_DUAL_GLASS_PANE_ITEMS = new HashMap<>();
    public static Map<DyeColor, RegistryObject<StainedHorizontalGlassPane>> STAINED_HORIZONTAL_GLASS_PANES = new HashMap<>();
    public static Map<DyeColor, RegistryObject<Item>> STAINED_HORIZONTAL_GLASS_PANE_ITEMS = new HashMap<>();
    public static Map<DyeColor, RegistryObject<StainedHorizontalGlassPane>> TINTED_HORIZONTAL_GLASS_PANES = new HashMap<>();
    public static Map<DyeColor, RegistryObject<Item>> TINTED_HORIZONTAL_GLASS_PANE_ITEMS = new HashMap<>();
    public static Map<DyeColor, RegistryObject<StainedGlassPane>> TINTED_GLASS_PANES = new HashMap<>();
    public static Map<DyeColor, RegistryObject<Item>> TINTED_GLASS_PANE_ITEMS = new HashMap<>();
    public static Map<DyeColor, RegistryObject<StainedDualGlassPane>> TINTED_DUAL_GLASS_PANES = new HashMap<>();
    public static Map<DyeColor, RegistryObject<Item>> TINTED_DUAL_GLASS_PANE_ITEMS = new HashMap<>();
    public static Map<DyeColor, RegistryObject<StainedHorizontalGlassPane>> PLAIN_HORIZONTAL_GLASS_PANES = new HashMap<>();
    public static Map<DyeColor, RegistryObject<Item>> PLAIN_HORIZONTAL_GLASS_PANE_ITEMS = new HashMap<>();
    public static Map<DyeColor, RegistryObject<StainedGlassPane>> PLAIN_GLASS_PANES = new HashMap<>();
    public static Map<DyeColor, RegistryObject<Item>> PLAIN_GLASS_PANE_ITEMS = new HashMap<>();
    public static Map<DyeColor, RegistryObject<StainedDualGlassPane>> PLAIN_DUAL_GLASS_PANES = new HashMap<>();
    public static Map<DyeColor, RegistryObject<Item>> PLAIN_DUAL_GLASS_PANE_ITEMS = new HashMap<>();

    public static void init() {
        registerGlassPanes();
        registerDualGlassPanes();
        registerHorizontalPanes();
        registerTintedPanes();
        registerTintedDualGlassPanes();
        registerTintedHorizontalPanes();
        registerPlainPanes();
        registerPlainDualGlassPanes();
        registerPlainHorizontalPanes();
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Glass Panes
    public static final RegistryObject<GlassPane> GLASS_PANE = BLOCKS.register("glasspane", () -> new GlassPane(AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Item> GLASS_PANE_ITEM = ITEMS.register("glasspane", () -> new BlockItem(GLASS_PANE.get(), new Item.Properties().tab(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<DualGlassPane> DUAL_GLASS_PANE = BLOCKS.register("dualglasspane", () -> new DualGlassPane(AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Item> DUAL_GLASS_PANE_ITEM = ITEMS.register("dualglasspane", () -> new BlockItem(DUAL_GLASS_PANE.get(), new Item.Properties().tab(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<HorizontalGlassPane> HORIZONTAL_GLASS_PANE = BLOCKS.register("horizontal_glasspane", () -> new HorizontalGlassPane(AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Item> HORIZONTAL_GLASS_PANE_ITEM = ITEMS.register("horizontal_glasspane", () -> new BlockItem(HORIZONTAL_GLASS_PANE.get(), new Item.Properties().tab(ModSetup.ITEM_GROUP)));

    // Glass Pane Table
    public static final RegistryObject<GlassPaneTable> GLASS_PANE_TABLE = BLOCKS.register("glass_pane_table", () -> new GlassPaneTable(AbstractBlock.Properties.of(Material.STONE).strength(0.3f).sound(SoundType.STONE)));
    public static final RegistryObject<Item> GLASS_PANE_TABLE_ITEM = ITEMS.register("glass_pane_table", () -> new BlockItem(GLASS_PANE_TABLE.get(), new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<ContainerType<GlassPaneTableContainer>> GLASS_PANE_TABLE_CONTAINER = CONTAINERS.register("glass_pane_table", () -> IForgeContainerType.create(GlassPaneTableContainer::new));
    public static final IRecipeType<GlassPaneTableRecipe> GLASS_PANE_TABLE_TYPE = IRecipeType.register("pitg:glass_pane_table");
    public static final RegistryObject<IRecipeSerializer<?>> GLASS_PANE_TABLE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("glass_pane_table", () -> new GlassPaneTableRecipeSerializer<>(GlassPaneTableRecipe::new));

    // Glazing Tool
    public static final RegistryObject<Item> GLAZING_TOOL_ITEM = ITEMS.register("glazing_tool", () -> new GlazingTool(new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(1)));

    // Alternate green dye item - To be removed in later revisions
    public static final RegistryObject<Item> GREEN_DYE_ITEM = ITEMS.register("green_dye", () -> new DyeItem(DyeColor.GREEN, (new Item.Properties()).tab(ModSetup.ITEM_GROUP)));

    private static void registerGlassPanes() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName().toLowerCase() + "_stained_glasspane";
            StainedGlassPane pane = new StainedGlassPane(color, AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion());
            STAINED_GLASS_PANES.put(color, BLOCKS.register(name, () -> pane));
            STAINED_GLASS_PANE_ITEMS.put(color, ITEMS.register(name, () -> new BlockItem(pane, new Item.Properties().tab(ModSetup.ITEM_GROUP))));
        }
    }

    private static void registerTintedPanes() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName().toLowerCase() + "_tinted_glasspane";
            StainedGlassPane pane = new StainedGlassPane(color, AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion());
            TINTED_GLASS_PANES.put(color, BLOCKS.register(name, () -> pane));
            TINTED_GLASS_PANE_ITEMS.put(color, ITEMS.register(name, () -> new BlockItem(pane, new Item.Properties().tab(ModSetup.ITEM_GROUP))));
        }
    }

    private static void registerPlainPanes() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName().toLowerCase() + "_plain_glasspane";
            StainedGlassPane pane = new StainedGlassPane(color, AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion());
            PLAIN_GLASS_PANES.put(color, BLOCKS.register(name, () -> pane));
            PLAIN_GLASS_PANE_ITEMS.put(color, ITEMS.register(name, () -> new BlockItem(pane, new Item.Properties().tab(ModSetup.ITEM_GROUP))));
        }
    }

    private static void registerDualGlassPanes() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName().toLowerCase() + "_stained_dualglasspane";
            StainedDualGlassPane pane = new StainedDualGlassPane(color, AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion());
            STAINED_DUAL_GLASS_PANES.put(color, BLOCKS.register(name, () -> pane));
            STAINED_DUAL_GLASS_PANE_ITEMS.put(color, ITEMS.register(name, () -> new BlockItem(pane, new Item.Properties().tab(ModSetup.ITEM_GROUP))));
        }
    }

    private static void registerTintedDualGlassPanes() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName().toLowerCase() + "_tinted_dualglasspane";
            StainedDualGlassPane pane = new StainedDualGlassPane(color, AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion());
            TINTED_DUAL_GLASS_PANES.put(color, BLOCKS.register(name, () -> pane));
            TINTED_DUAL_GLASS_PANE_ITEMS.put(color, ITEMS.register(name, () -> new BlockItem(pane, new Item.Properties().tab(ModSetup.ITEM_GROUP))));
        }
    }

    private static void registerPlainDualGlassPanes() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName().toLowerCase() + "_plain_dualglasspane";
            StainedDualGlassPane pane = new StainedDualGlassPane(color, AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion());
            PLAIN_DUAL_GLASS_PANES.put(color, BLOCKS.register(name, () -> pane));
            PLAIN_DUAL_GLASS_PANE_ITEMS.put(color, ITEMS.register(name, () -> new BlockItem(pane, new Item.Properties().tab(ModSetup.ITEM_GROUP))));
        }
    }

    private static void registerHorizontalPanes() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName().toLowerCase() + "_stained_horizontal_glasspane";
            StainedHorizontalGlassPane pane = new StainedHorizontalGlassPane(color, AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion());
            STAINED_HORIZONTAL_GLASS_PANES.put(color, BLOCKS.register(name, () -> pane));
            STAINED_HORIZONTAL_GLASS_PANE_ITEMS.put(color, ITEMS.register(name, () -> new BlockItem(pane, new Item.Properties().tab(ModSetup.ITEM_GROUP))));
        }
    }

    private static void registerTintedHorizontalPanes() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName().toLowerCase() + "_tinted_horizontal_glasspane";
            StainedHorizontalGlassPane pane = new StainedHorizontalGlassPane(color, AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion());
            TINTED_HORIZONTAL_GLASS_PANES.put(color, BLOCKS.register(name, () -> pane));
            TINTED_HORIZONTAL_GLASS_PANE_ITEMS.put(color, ITEMS.register(name, () -> new BlockItem(pane, new Item.Properties().tab(ModSetup.ITEM_GROUP))));
        }
    }

    private static void registerPlainHorizontalPanes() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName().toLowerCase() + "_plain_horizontal_glasspane";
            StainedHorizontalGlassPane pane = new StainedHorizontalGlassPane(color, AbstractBlock.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion());
            PLAIN_HORIZONTAL_GLASS_PANES.put(color, BLOCKS.register(name, () -> pane));
            PLAIN_HORIZONTAL_GLASS_PANE_ITEMS.put(color, ITEMS.register(name, () -> new BlockItem(pane, new Item.Properties().tab(ModSetup.ITEM_GROUP))));
        }
    }

}

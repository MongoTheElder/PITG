package tv.mongotheelder.pitg.setup;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tv.mongotheelder.pitg.blocks.GlassPane;
import tv.mongotheelder.pitg.blocks.StainedGlassPane;
import tv.mongotheelder.pitg.items.GlazingTool;

import static tv.mongotheelder.pitg.Pitg.MODID;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<GlassPane> GLASS_PANE = BLOCKS.register("glasspane", () -> new GlassPane(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> GLASS_PANE_ITEM = ITEMS.register("glasspane", () -> new BlockItem(GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> RED_STAINED_GLASS_PANE = BLOCKS.register("red_stained_glasspane", () -> new StainedGlassPane(DyeColor.RED, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> RED_STAINED_GLASS_PANE_ITEM = ITEMS.register("red_stained_glasspane", () -> new BlockItem(RED_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> WHITE_STAINED_GLASS_PANE = BLOCKS.register("white_stained_glasspane", () -> new StainedGlassPane(DyeColor.WHITE, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> WHITE_STAINED_GLASS_PANE_ITEM = ITEMS.register("white_stained_glasspane", () -> new BlockItem(WHITE_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> BLUE_STAINED_GLASS_PANE = BLOCKS.register("blue_stained_glasspane", () -> new StainedGlassPane(DyeColor.BLUE, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> BLUE_STAINED_GLASS_PANE_ITEM = ITEMS.register("blue_stained_glasspane", () -> new BlockItem(BLUE_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> ORANGE_STAINED_GLASS_PANE = BLOCKS.register("orange_stained_glasspane", () -> new StainedGlassPane(DyeColor.ORANGE, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> ORANGE_STAINED_GLASS_PANE_ITEM = ITEMS.register("orange_stained_glasspane", () -> new BlockItem(ORANGE_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> MAGENTA_STAINED_GLASS_PANE = BLOCKS.register("magenta_stained_glasspane", () -> new StainedGlassPane(DyeColor.MAGENTA, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> MAGENTA_STAINED_GLASS_PANE_ITEM = ITEMS.register("magenta_stained_glasspane", () -> new BlockItem(MAGENTA_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> LIGHT_BLUE_STAINED_GLASS_PANE = BLOCKS.register("light_blue_stained_glasspane", () -> new StainedGlassPane(DyeColor.LIGHT_BLUE, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> LIGHT_BLUE_STAINED_GLASS_PANE_ITEM = ITEMS.register("light_blue_stained_glasspane", () -> new BlockItem(LIGHT_BLUE_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> YELLOW_STAINED_GLASS_PANE = BLOCKS.register("yellow_stained_glasspane", () -> new StainedGlassPane(DyeColor.YELLOW, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> YELLOW_STAINED_GLASS_PANE_ITEM = ITEMS.register("yellow_stained_glasspane", () -> new BlockItem(YELLOW_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> LIME_STAINED_GLASS_PANE = BLOCKS.register("lime_stained_glasspane", () -> new StainedGlassPane(DyeColor.LIME, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> LIME_STAINED_GLASS_PANE_ITEM = ITEMS.register("lime_stained_glasspane", () -> new BlockItem(LIME_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> PINK_STAINED_GLASS_PANE = BLOCKS.register("pink_stained_glasspane", () -> new StainedGlassPane(DyeColor.PINK, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> PINK_STAINED_GLASS_PANE_ITEM = ITEMS.register("pink_stained_glasspane", () -> new BlockItem(PINK_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> GRAY_STAINED_GLASS_PANE = BLOCKS.register("gray_stained_glasspane", () -> new StainedGlassPane(DyeColor.GRAY, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> GRAY_STAINED_GLASS_PANE_ITEM = ITEMS.register("gray_stained_glasspane", () -> new BlockItem(GRAY_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> LIGHT_GRAY_STAINED_GLASS_PANE = BLOCKS.register("light_gray_stained_glasspane", () -> new StainedGlassPane(DyeColor.LIGHT_GRAY, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> LIGHT_GRAY_STAINED_GLASS_PANE_ITEM = ITEMS.register("light_gray_stained_glasspane", () -> new BlockItem(LIGHT_GRAY_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> CYAN_STAINED_GLASS_PANE = BLOCKS.register("cyan_stained_glasspane", () -> new StainedGlassPane(DyeColor.CYAN, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> CYAN_STAINED_GLASS_PANE_ITEM = ITEMS.register("cyan_stained_glasspane", () -> new BlockItem(CYAN_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> PURPLE_STAINED_GLASS_PANE = BLOCKS.register("purple_stained_glasspane", () -> new StainedGlassPane(DyeColor.PURPLE, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> PURPLE_STAINED_GLASS_PANE_ITEM = ITEMS.register("purple_stained_glasspane", () -> new BlockItem(PURPLE_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> BROWN_STAINED_GLASS_PANE = BLOCKS.register("brown_stained_glasspane", () -> new StainedGlassPane(DyeColor.BROWN, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> BROWN_STAINED_GLASS_PANE_ITEM = ITEMS.register("brown_stained_glasspane", () -> new BlockItem(BROWN_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> GREEN_STAINED_GLASS_PANE = BLOCKS.register("green_stained_glasspane", () -> new StainedGlassPane(DyeColor.GREEN, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> GREEN_STAINED_GLASS_PANE_ITEM = ITEMS.register("green_stained_glasspane", () -> new BlockItem(GREEN_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<StainedGlassPane> BLACK_STAINED_GLASS_PANE = BLOCKS.register("black_stained_glasspane", () -> new StainedGlassPane(DyeColor.BLACK, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> BLACK_STAINED_GLASS_PANE_ITEM = ITEMS.register("black_stained_glasspane", () -> new BlockItem(BLACK_STAINED_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<Item> GLAZING_TOOL_ITEM = ITEMS.register("glazing_tool", () -> new GlazingTool(new Item.Properties().group(ModSetup.ITEM_GROUP).maxStackSize(1)));
}

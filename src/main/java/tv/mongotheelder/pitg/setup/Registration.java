package tv.mongotheelder.pitg.setup;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tv.mongotheelder.pitg.blocks.DualGlassPane;
import tv.mongotheelder.pitg.blocks.GlassPane;
import tv.mongotheelder.pitg.blocks.StainedDualGlassPane;
import tv.mongotheelder.pitg.blocks.StainedGlassPane;
import tv.mongotheelder.pitg.items.GlazingTool;

import java.util.HashMap;
import java.util.Map;

import static tv.mongotheelder.pitg.Pitg.MODID;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static Map<DyeColor, RegistryObject<StainedGlassPane>> GLASS_PANES = new HashMap<>();
    public static Map<DyeColor, RegistryObject<Item>> GLASS_PANE_ITEMS = new HashMap<>();
    public static Map<DyeColor, RegistryObject<StainedDualGlassPane>> DUAL_GLASS_PANES = new HashMap<>();
    public static Map<DyeColor, RegistryObject<Item>> DUAL_GLASS_PANE_ITEMS = new HashMap<>();

    public static void init() {
        registerGlassPanes();
        registerDualGlassPanes();
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<GlassPane> GLASS_PANE = BLOCKS.register("glasspane", () -> new GlassPane(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> GLASS_PANE_ITEM = ITEMS.register("glasspane", () -> new BlockItem(GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<DualGlassPane> DUAL_GLASS_PANE = BLOCKS.register("dualglasspane", () -> new DualGlassPane(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Item> DUAL_PANE_ITEM = ITEMS.register("dualglasspane", () -> new BlockItem(DUAL_GLASS_PANE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<Item> GLAZING_TOOL_ITEM = ITEMS.register("glazing_tool", () -> new GlazingTool(new Item.Properties().group(ModSetup.ITEM_GROUP).maxStackSize(1)));

    // Alternate green dye item - I hate being forced to find a desert biome to get green dye
    public static final RegistryObject<Item> GREEN_DYE_ITEM = ITEMS.register("green_dye", () -> new DyeItem(DyeColor.GREEN, (new Item.Properties()).group(ModSetup.ITEM_GROUP)));

    private static void registerGlassPanes() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getString().toLowerCase() + "_stained_glasspane";
            StainedGlassPane pane = new StainedGlassPane(color, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid());
            GLASS_PANES.put(color, BLOCKS.register(name, () -> pane));
            GLASS_PANE_ITEMS.put(color, ITEMS.register(name, () -> new BlockItem(pane, new Item.Properties().group(ModSetup.ITEM_GROUP))));
        }
    }

    private static void registerDualGlassPanes() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getString().toLowerCase() + "_stained_dualglasspane";
            StainedDualGlassPane pane = new StainedDualGlassPane(color, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f).sound(SoundType.GLASS).notSolid());
            DUAL_GLASS_PANES.put(color, BLOCKS.register(name, () -> pane));
            DUAL_GLASS_PANE_ITEMS.put(color, ITEMS.register(name, () -> new BlockItem(pane, new Item.Properties().group(ModSetup.ITEM_GROUP))));
        }
    }
}

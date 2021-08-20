package tv.mongotheelder.pitg.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.DyeColor;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.blocks.DualGlassPane;
import tv.mongotheelder.pitg.blocks.GlassPane;
import tv.mongotheelder.pitg.blocks.GlassPaneTable;
import tv.mongotheelder.pitg.blocks.HorizontalGlassPane;
import tv.mongotheelder.pitg.setup.Registration;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class PitgBlockStateProvider extends BlockStateProvider {
    public PitgBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Pitg.MODID, exFileHelper);
    }

    public BlockModelBuilder makeBlockModel(String modelName, String color, String parent, String textureName, boolean usesEdge) {
        String actualColor = color.isEmpty() ? "" : color + "_";
        String modelPath = BLOCK_FOLDER + "/" + actualColor + modelName;
        String texturePath = BLOCK_FOLDER + "/" + actualColor + textureName;
        BlockModelBuilder model = models().getBuilder(modelPath);

        model
                .parent(models().getExistingFile(new ResourceLocation(Pitg.MODID, BLOCK_FOLDER + "/bases/" + parent + "_base")))
                .texture("pane", modLoc(texturePath))
                .texture("edge", modLoc(texturePath + (usesEdge ? "_top" : "")))
                .texture("particle", "#pane");
        return model;
    }

    public BlockModelBuilder makeGlassPaneTableModel(String modelName, String parent, String textureName, String paneName) {
        String modelPath = BLOCK_FOLDER + "/" + modelName;
        String texturePath = BLOCK_FOLDER + "/" + textureName;
        String panePath = BLOCK_FOLDER + "/" + paneName;

        BlockModelBuilder model = models().getBuilder(modelPath);

        model
                .parent(models().getExistingFile(new ResourceLocation(Pitg.MODID, BLOCK_FOLDER + "/bases/" + parent + "_base")))
                .texture("body", modLoc(texturePath))
                .texture("pane", modLoc(panePath))
                .texture("particle", "#body");
        return model;
    }

    public void blockstateGlassPane(GlassPane block, BlockModelBuilder side, BlockModelBuilder corner) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        builder.part().modelFile(side).addModel().condition(GlassPane.NORTH, true).condition(GlassPane.WEST, false).condition(GlassPane.EAST, false);
        builder.part().modelFile(side).rotationY(90).addModel().condition(GlassPane.EAST, true).condition(GlassPane.NORTH, false).condition(GlassPane.SOUTH, false);
        builder.part().modelFile(side).rotationY(180).addModel().condition(GlassPane.SOUTH, true).condition(GlassPane.WEST, false).condition(GlassPane.EAST, false);
        builder.part().modelFile(side).rotationY(270).addModel().condition(GlassPane.WEST, true).condition(GlassPane.NORTH, false).condition(GlassPane.SOUTH, false);

        builder.part().modelFile(corner).addModel().condition(GlassPane.NORTH, true).condition(GlassPane.EAST, true);
        builder.part().modelFile(corner).rotationY(90).addModel().condition(GlassPane.EAST, true).condition(GlassPane.SOUTH, true);
        builder.part().modelFile(corner).rotationY(180).addModel().condition(GlassPane.SOUTH, true).condition(GlassPane.WEST, true);
        builder.part().modelFile(corner).rotationY(270).addModel().condition(GlassPane.WEST, true).condition(GlassPane.NORTH, true);
    }

    public void blockstateHorizontalGlassPane(HorizontalGlassPane block, BlockModelBuilder upper, BlockModelBuilder lower) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.partialState().with(BlockStateProperties.HALF, Half.TOP).modelForState().modelFile(upper).addModel();
        builder.partialState().with(BlockStateProperties.HALF, Half.BOTTOM).modelForState().modelFile(lower).addModel();
    }

    public void blockstateGlassPaneTable(GlassPaneTable block, BlockModelBuilder model) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.partialState().modelForState().modelFile(model).addModel();
    }

    public void blockstateDualGlassPane(GlassPane block, BlockModelBuilder side, BlockModelBuilder left, BlockModelBuilder right, BlockModelBuilder corners) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        builder.part().modelFile(side).addModel().condition(DualGlassPane.NORTH, true);
        builder.part().modelFile(side).rotationY(180).addModel().condition(DualGlassPane.NORTH, true);
        builder.part().modelFile(side).addModel().condition(DualGlassPane.SOUTH, true);
        builder.part().modelFile(side).rotationY(180).addModel().condition(DualGlassPane.SOUTH, true);
        builder.part().modelFile(side).rotationY(90).addModel().condition(DualGlassPane.EAST, true);
        builder.part().modelFile(side).rotationY(270).addModel().condition(DualGlassPane.EAST, true);
        builder.part().modelFile(side).rotationY(90).addModel().condition(DualGlassPane.WEST, true);
        builder.part().modelFile(side).rotationY(270).addModel().condition(DualGlassPane.WEST, true);

        builder.part().modelFile(left).addModel().condition(DualGlassPane.WEST, false).condition(DualGlassPane.EAST, false);
        builder.part().modelFile(left).rotationY(180).addModel().condition(DualGlassPane.WEST, false).condition(DualGlassPane.EAST, false);
        builder.part().modelFile(left).rotationY(90).addModel().condition(DualGlassPane.NORTH, false).condition(DualGlassPane.SOUTH, false);
        builder.part().modelFile(left).rotationY(270).addModel().condition(DualGlassPane.NORTH, false).condition(DualGlassPane.SOUTH, false);

        builder.part().modelFile(right).addModel().condition(DualGlassPane.EAST, false).condition(DualGlassPane.WEST, false);
        builder.part().modelFile(right).rotationY(180).addModel().condition(DualGlassPane.EAST, false).condition(DualGlassPane.WEST, false);
        builder.part().modelFile(right).rotationY(90).addModel().condition(DualGlassPane.NORTH, false).condition(DualGlassPane.SOUTH, false);
        builder.part().modelFile(right).rotationY(270).addModel().condition(DualGlassPane.NORTH, false).condition(DualGlassPane.SOUTH, false);

        builder.part().modelFile(corners).addModel();
        builder.part().modelFile(corners).rotationY(90).addModel();
        builder.part().modelFile(corners).rotationY(270).addModel();
        builder.part().modelFile(corners).rotationY(180).addModel();
    }

    private void buildGlassPaneModels(GlassPane block) {
        BlockModelBuilder singleModel = makeBlockModel("glasspane_single", "", "glasspane_side", "glass_pane", true);
        BlockModelBuilder cornerModel = makeBlockModel("glasspane_corner", "", "glasspane_corner", "glass_pane", true);
        blockstateGlassPane(block, singleModel, cornerModel);
    }

    private void buildStainedGlassPaneModels(GlassPane block, String color) {
        BlockModelBuilder singleModel = makeBlockModel("stained_glasspane_single", color, "glasspane_side", "stained_glass_pane", true);
        BlockModelBuilder cornerModel = makeBlockModel("stained_glasspane_corner", color, "glasspane_corner", "stained_glass_pane", true);
        blockstateGlassPane(block, singleModel, cornerModel);
    }

    private void buildTintedGlassPaneModels(GlassPane block, String color) {
        BlockModelBuilder singleModel = makeBlockModel("tinted_glasspane_single", color, "glasspane_side", "tinted_glass_pane", false);
        BlockModelBuilder cornerModel = makeBlockModel("tinted_glasspane_corner", color, "glasspane_corner", "tinted_glass_pane", false);
        blockstateGlassPane(block, singleModel, cornerModel);
    }

    private void buildPlainGlassPaneModels(GlassPane block, String color) {
        BlockModelBuilder singleModel = makeBlockModel("plain_glasspane_single", color, "glasspane_side", "plain_glass_pane", false);
        BlockModelBuilder cornerModel = makeBlockModel("plain_glasspane_corner", color, "glasspane_corner", "plain_glass_pane", false);
        blockstateGlassPane(block, singleModel, cornerModel);
    }

    private void buildHorizontalGlassPaneModels(HorizontalGlassPane block) {
        BlockModelBuilder lowerModel = makeBlockModel("horizontal_glasspane_lower", "", "horizontal_glasspane_lower", "glass_pane", true);
        BlockModelBuilder upperModel = makeBlockModel("horizontal_glasspane_upper", "", "horizontal_glasspane_upper", "glass_pane", true);
        blockstateHorizontalGlassPane(block, upperModel, lowerModel);
    }

    private void buildStainedHorizontalGlassPaneModels(HorizontalGlassPane block, String color) {
        BlockModelBuilder lowerModel = makeBlockModel("stained_horizontal_glasspane_lower", color, "horizontal_glasspane_lower", "stained_glass_pane", true);
        BlockModelBuilder upperModel = makeBlockModel("stained_horizontal_glasspane_upper", color, "horizontal_glasspane_upper", "stained_glass_pane", true);
        blockstateHorizontalGlassPane(block, upperModel, lowerModel);
    }

    private void buildTintedHorizontalGlassPaneModels(HorizontalGlassPane block, String color) {
        BlockModelBuilder lowerModel = makeBlockModel("tinted_horizontal_glasspane_lower", color, "horizontal_glasspane_lower", "tinted_glass_pane", false);
        BlockModelBuilder upperModel = makeBlockModel("tinted_horizontal_glasspane_upper", color, "horizontal_glasspane_upper", "tinted_glass_pane", false);
        blockstateHorizontalGlassPane(block, upperModel, lowerModel);
    }

    private void buildPlainHorizontalGlassPaneModels(HorizontalGlassPane block, String color) {
        BlockModelBuilder lowerModel = makeBlockModel("plain_horizontal_glasspane_lower", color, "horizontal_glasspane_lower", "plain_glass_pane", false);
        BlockModelBuilder upperModel = makeBlockModel("plain_horizontal_glasspane_upper", color, "horizontal_glasspane_upper", "plain_glass_pane", false);
        blockstateHorizontalGlassPane(block, upperModel, lowerModel);
    }

    private void buildDualGlassPaneModels(GlassPane block) {
        BlockModelBuilder sideModel = makeBlockModel("dualglasspane_side", "", "dualglasspane_side", "glass_pane", true);
        BlockModelBuilder leftModel = makeBlockModel("dualglasspane_left_edge", "", "dualglasspane_left_edge", "glass_pane", true);
        BlockModelBuilder rightModel = makeBlockModel("dualglasspane_right_edge", "", "dualglasspane_right_edge", "glass_pane", true);
        BlockModelBuilder cornerModel = makeBlockModel("dualglasspane_corner", "", "dualglasspane_corner", "glass_pane", true);
        blockstateDualGlassPane(block, sideModel, leftModel, rightModel, cornerModel);
    }

    private void buildStainedDualGlassPaneModels(GlassPane block, String color) {
        BlockModelBuilder sideModel = makeBlockModel("stained_dualglasspane_side", color, "dualglasspane_side", "stained_glass_pane", true);
        BlockModelBuilder leftModel = makeBlockModel("stained_dualglasspane_left_edge", color, "dualglasspane_left_edge", "stained_glass_pane", true);
        BlockModelBuilder rightModel = makeBlockModel("stained_dualglasspane_right_edge", color, "dualglasspane_right_edge", "stained_glass_pane", true);
        BlockModelBuilder cornerModel = makeBlockModel("stained_dualglasspane_corner", color, "dualglasspane_corner", "stained_glass_pane", true);
        blockstateDualGlassPane(block, sideModel, leftModel, rightModel, cornerModel);
    }

    private void buildTintedDualGlassPaneModels(GlassPane block, String color) {
        BlockModelBuilder sideModel = makeBlockModel("tinted_dualglasspane_side", color, "dualglasspane_side", "tinted_glass_pane", false);
        BlockModelBuilder leftModel = makeBlockModel("tinted_dualglasspane_left_edge", color, "dualglasspane_left_edge", "tinted_glass_pane", false);
        BlockModelBuilder rightModel = makeBlockModel("tinted_dualglasspane_right_edge", color, "dualglasspane_right_edge", "tinted_glass_pane", false);
        BlockModelBuilder cornerModel = makeBlockModel("tinted_dualglasspane_corner", color, "dualglasspane_corner", "tinted_glass_pane", false);
        blockstateDualGlassPane(block, sideModel, leftModel, rightModel, cornerModel);
    }

    private void buildPlainDualGlassPaneModels(GlassPane block, String color) {
        BlockModelBuilder sideModel = makeBlockModel("plain_dualglasspane_side", color, "dualglasspane_side", "plain_glass_pane", false);
        BlockModelBuilder leftModel = makeBlockModel("plain_dualglasspane_left_edge", color, "dualglasspane_left_edge", "plain_glass_pane", false);
        BlockModelBuilder rightModel = makeBlockModel("plain_dualglasspane_right_edge", color, "dualglasspane_right_edge", "plain_glass_pane", false);
        BlockModelBuilder cornerModel = makeBlockModel("plain_dualglasspane_corner", color, "dualglasspane_corner", "plain_glass_pane", false);
        blockstateDualGlassPane(block, sideModel, leftModel, rightModel, cornerModel);
    }

    private void buildGlassPaneTableModels(GlassPaneTable block) {
        BlockModelBuilder model = makeGlassPaneTableModel("glass_pane_table", "glass_pane_table", "glass_pane_table_body", "purple_stained_glass_pane");
        blockstateGlassPaneTable(block, model);
    }

    @Override
    protected void registerStatesAndModels() {
        buildGlassPaneModels(Registration.GLASS_PANE.get());
        buildHorizontalGlassPaneModels(Registration.HORIZONTAL_GLASS_PANE.get());
        buildDualGlassPaneModels(Registration.DUAL_GLASS_PANE.get());
        buildGlassPaneTableModels(Registration.GLASS_PANE_TABLE.get());

        for (DyeColor color : DyeColor.values()) {
            String colorName = color.getSerializedName().toLowerCase();
            buildStainedHorizontalGlassPaneModels(Registration.STAINED_HORIZONTAL_GLASS_PANES.get(color).get(), colorName);
            buildTintedHorizontalGlassPaneModels(Registration.TINTED_HORIZONTAL_GLASS_PANES.get(color).get(), colorName);
            buildPlainHorizontalGlassPaneModels(Registration.PLAIN_HORIZONTAL_GLASS_PANES.get(color).get(), colorName);

            buildStainedGlassPaneModels(Registration.STAINED_GLASS_PANES.get(color).get(), colorName);
            buildTintedGlassPaneModels(Registration.TINTED_GLASS_PANES.get(color).get(), colorName);
            buildPlainGlassPaneModels(Registration.PLAIN_GLASS_PANES.get(color).get(), colorName);

            buildStainedDualGlassPaneModels(Registration.STAINED_DUAL_GLASS_PANES.get(color).get(), colorName);
            buildTintedDualGlassPaneModels(Registration.TINTED_DUAL_GLASS_PANES.get(color).get(), colorName);
            buildPlainDualGlassPaneModels(Registration.PLAIN_DUAL_GLASS_PANES.get(color).get(), colorName);
        }
    }
}

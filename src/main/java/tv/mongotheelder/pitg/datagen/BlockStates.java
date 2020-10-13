package tv.mongotheelder.pitg.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.DyeColor;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.blocks.DualGlassPane;
import tv.mongotheelder.pitg.blocks.GlassPane;
import tv.mongotheelder.pitg.setup.Registration;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class BlockStates extends BlockStateProvider {
    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Pitg.MODID, exFileHelper);
    }

    public void modelGlassPaneSide(BlockModelBuilder model, String path) {

        model
                .element()
                .from(0, 0, 0)
                .to(16, 16, 2)
                .face(Direction.NORTH).texture("#pane").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).uvs(0, 0, 16, 16).end()
                .face(Direction.SOUTH).texture("#pane").uvs(0, 0, 16, 16).end()
                .end()
                .element()
                .from(2, 0, 0)
                .to(14, 16, 2)
                .face(Direction.UP).texture("#edge").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).uvs(7, 2, 9, 14).end()
                .face(Direction.DOWN).texture("#edge").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).uvs(7, 2, 9, 14).end()
                .end()
                .texture("pane", new ResourceLocation("minecraft", path + "glass"))
                .texture("edge", new ResourceLocation("minecraft", path + "glass_pane_top"))
                .texture("particle", "#pane");
    }

    public void modelGlassPaneLeftEdge(BlockModelBuilder model, String path) {
        model
                .element()
                .from(0, 0, 0)
                .to(2, 16, 2)
                .face(Direction.WEST).texture("#edge").uvs(7, 0, 9, 16).cullface(Direction.EAST).end()
                .end()
                .texture("pane", new ResourceLocation("minecraft", path + "glass"))
                .texture("edge", new ResourceLocation("minecraft", path + "glass_pane_top"))
                .texture("particle", "#pane");
    }

    public void modelGlassPaneRightEdge(BlockModelBuilder model, String path) {
        model
                .element()
                .from(14, 0, 0)
                .to(16, 16, 2)
                .face(Direction.EAST).texture("#edge").uvs(7, 0, 9, 16).cullface(Direction.WEST).end()
                .end()
                .texture("pane", new ResourceLocation("minecraft", path + "glass"))
                .texture("edge", new ResourceLocation("minecraft", path + "glass_pane_top"))
                .texture("particle", "#pane");
    }

    public void modelGlassPaneCorners(BlockModelBuilder model, String path) {
        model
                .element()
                .from(0, 0, 0)
                .to(2, 16, 2)
                .face(Direction.DOWN).texture("#edge").uvs(7, 0, 9, 2).rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).end()
                .face(Direction.UP).texture("#edge").uvs(7, 0, 9, 2).rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).end()
                .end()
                .texture("pane", new ResourceLocation("minecraft", path + "glass"))
                .texture("edge", new ResourceLocation("minecraft", path + "glass_pane_top"))
                .texture("particle", "#pane");
    }

    public void blockstateGlassPane(GlassPane block, BlockModelBuilder side, BlockModelBuilder left, BlockModelBuilder right, BlockModelBuilder corners) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        builder.part().modelFile(side).addModel().condition(GlassPane.NORTH, true);
        builder.part().modelFile(side).rotationY(90).addModel().condition(GlassPane.EAST, true);
        builder.part().modelFile(side).rotationY(180).addModel().condition(GlassPane.SOUTH, true);
        builder.part().modelFile(side).rotationY(270).addModel().condition(GlassPane.WEST, true);

        builder.part().modelFile(left).addModel().condition(GlassPane.NORTH, true).condition(GlassPane.WEST, false);
        builder.part().modelFile(right).addModel().condition(GlassPane.NORTH, true).condition(GlassPane.EAST, false);

        builder.part().modelFile(right).rotationY(180).addModel().condition(GlassPane.SOUTH, true).condition(GlassPane.WEST, false);
        builder.part().modelFile(left).rotationY(180).addModel().condition(GlassPane.SOUTH, true).condition(GlassPane.EAST, false);

        builder.part().modelFile(right).rotationY(90).addModel().condition(GlassPane.EAST, true).condition(GlassPane.SOUTH, false);
        builder.part().modelFile(left).rotationY(90).addModel().condition(GlassPane.EAST, true).condition(GlassPane.NORTH, false);

        builder.part().modelFile(left).rotationY(270).addModel().condition(GlassPane.WEST, true).condition(GlassPane.SOUTH, false);
        builder.part().modelFile(right).rotationY(270).addModel().condition(GlassPane.WEST, true).condition(GlassPane.NORTH, false);

        builder.part().modelFile(corners).addModel().useOr().condition(GlassPane.NORTH, true).condition(GlassPane.WEST, true);
        builder.part().modelFile(corners).rotationY(90).addModel().useOr().condition(GlassPane.NORTH, true).condition(GlassPane.EAST, true);
        builder.part().modelFile(corners).rotationY(270).addModel().useOr().condition(GlassPane.SOUTH, true).condition(GlassPane.WEST, true);
        builder.part().modelFile(corners).rotationY(180).addModel().useOr().condition(GlassPane.SOUTH, true).condition(GlassPane.EAST, true);

    }

    public void blockstateDualGlassPane(GlassPane block, BlockModelBuilder side, BlockModelBuilder left, BlockModelBuilder right, BlockModelBuilder corners) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        builder.part().modelFile(side).addModel().useOr().condition(DualGlassPane.NORTH, true).useOr().condition(DualGlassPane.SOUTH, true);
        builder.part().modelFile(side).rotationY(90).addModel().useOr().condition(DualGlassPane.EAST, true).useOr().condition(DualGlassPane.WEST, true);
        builder.part().modelFile(side).rotationY(180).addModel().useOr().condition(DualGlassPane.NORTH, true).useOr().condition(DualGlassPane.SOUTH, true);
        builder.part().modelFile(side).rotationY(270).addModel().useOr().condition(DualGlassPane.EAST, true).useOr().condition(DualGlassPane.WEST, true);

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

    private void buildGlassPaneModels(GlassPane block, String color) {
        String path = BLOCK_FOLDER + "/" + (color.equals("") ? "" : color + "_stained_");
        BlockModelBuilder side_model = models().getBuilder(path + "glasspane");
        BlockModelBuilder left_model = models().getBuilder(path + "glasspane_edge_left");
        BlockModelBuilder right_model = models().getBuilder(path + "glasspane_edge_right");
        BlockModelBuilder corner_model = models().getBuilder(path + "glasspane_corners");
        modelGlassPaneSide(side_model, path);
        modelGlassPaneCorners(corner_model, path);
        modelGlassPaneLeftEdge(left_model, path);
        modelGlassPaneRightEdge(right_model, path);
        blockstateGlassPane(block, side_model, left_model, right_model, corner_model);
    }

    private void buildDualGlassPaneModels(GlassPane block, String color) {
        String path = BLOCK_FOLDER + "/" + (color.equals("") ? "" : color + "_stained_");
        BlockModelBuilder side_model = models().getBuilder(path + "glasspane");
        BlockModelBuilder left_model = models().getBuilder(path + "glasspane_edge_left");
        BlockModelBuilder right_model = models().getBuilder(path + "glasspane_edge_right");
        BlockModelBuilder corner_model = models().getBuilder(path + "glasspane_corners");
        blockstateDualGlassPane(block, side_model, left_model, right_model, corner_model);
    }

    @Override
    protected void registerStatesAndModels() {
        buildGlassPaneModels(Registration.GLASS_PANE.get(), "");
        buildDualGlassPaneModels(Registration.DUAL_GLASS_PANE.get(), "");

        for (DyeColor color : DyeColor.values()) {
            String colorName = color.getString().toLowerCase();
            buildGlassPaneModels(Registration.GLASS_PANES.get(color).get(), colorName);
            buildDualGlassPaneModels(Registration.DUAL_GLASS_PANES.get(color).get(), colorName);
        }
    }
}

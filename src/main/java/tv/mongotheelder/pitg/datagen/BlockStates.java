package tv.mongotheelder.pitg.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import tv.mongotheelder.pitg.Pitg;
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

    private void buildModels(GlassPane block, String color) {
        String path = BLOCK_FOLDER + "/" + (color.equals("") ? "" : color + "_stained_");
        BlockModelBuilder side_model = models().getBuilder(path + "glasspane");
        BlockModelBuilder left_model = models().getBuilder(path + "glasspane_edge_left");
        BlockModelBuilder right_model = models().getBuilder(path + "glasspane_edge_right");
        BlockModelBuilder corner_model = models().getBuilder(path + "glasspane_corners");
        modelGlassPaneSide(side_model, path);
        modelGlassPaneCorners(corner_model, path);
        modelGlassPaneLeftEdge(left_model, path);
        modelGlassPaneRightEdge(right_model, path);
        //blockstateGlassPane(block, side_model, left_model, right_model, corner_model);
    }

    @Override
    protected void registerStatesAndModels() {
        buildModels(Registration.GLASS_PANE.get(), "");
        buildModels(Registration.RED_STAINED_GLASS_PANE.get(), "red");
        buildModels(Registration.WHITE_STAINED_GLASS_PANE.get(), "white");
        buildModels(Registration.BLUE_STAINED_GLASS_PANE.get(), "blue");
        buildModels(Registration.ORANGE_STAINED_GLASS_PANE.get(), "orange");
        buildModels(Registration.MAGENTA_STAINED_GLASS_PANE.get(), "magenta");
        buildModels(Registration.LIGHT_BLUE_STAINED_GLASS_PANE.get(), "light_blue");
        buildModels(Registration.YELLOW_STAINED_GLASS_PANE.get(), "yellow");
        buildModels(Registration.LIME_STAINED_GLASS_PANE.get(), "lime");
        buildModels(Registration.PINK_STAINED_GLASS_PANE.get(), "pink");
        buildModels(Registration.GRAY_STAINED_GLASS_PANE.get(), "gray");
        buildModels(Registration.LIGHT_GRAY_STAINED_GLASS_PANE.get(), "light_gray");
        buildModels(Registration.CYAN_STAINED_GLASS_PANE.get(), "cyan");
        buildModels(Registration.PURPLE_STAINED_GLASS_PANE.get(), "purple");
        buildModels(Registration.BROWN_STAINED_GLASS_PANE.get(), "brown");
        buildModels(Registration.GREEN_STAINED_GLASS_PANE.get(), "green");
        buildModels(Registration.BLACK_STAINED_GLASS_PANE.get(), "black");
    }
}
/*

blockstates/glasspane.json
    { "when": { "north": true },
      "apply": { "model": "pitg:block/glasspane" }
    },
    { "when": { "east": true },
      "apply": { "model": "pitg:block/glasspane", "y": 90 }
    },
    { "when": { "south": true },
      "apply": { "model": "pitg:block/glasspane", "y": 180 }
    },
    { "when": { "west": true },
      "apply": { "model": "pitg:block/glasspane", "y": 270 }
    },
    {
      "when": { "north": true, "west": false },
      "apply": { "model": "pitg:/block/glasspane_edge_left", "y": 0 }
    },


models/block/glasspane.json
{
  "textures": {
    "edge": "block/glass_pane_top",
    "pane": "block/glass",
    "particle": "#pane"
  },
  "ambientocclusion": false,
  "elements": [
    { "from": [ 0, 0, 0 ],
      "to": [ 16, 16, 2 ],
      "faces": {
        "north": { "uv": [  0,  0, 16, 16 ], "texture": "#pane", "rotation": 90 },
        "south": { "uv": [  0,  0, 16, 16 ], "texture": "#pane" }
      }
    },
    {
      "from": [ 2, 0, 0 ],
      "to": [ 14, 16, 2],
      "faces": {
        "down": { "uv": [ 7, 2, 9, 14 ], "texture": "#edge", "rotation": 90 },
        "up":   { "uv": [ 7, 2, 9, 14 ], "texture": "#edge", "rotation": 90
        }
      }
    }
  ]
}
 */
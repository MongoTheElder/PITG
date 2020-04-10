package tv.mongotheelder.pitg.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelBuilder;
import tv.mongotheelder.pitg.Pitg;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class BlockStates extends BlockStateProvider {
    private static final String[] colors = { "", "white", "red", "blue", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "brown", "green", "black" };

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Pitg.MODID, exFileHelper);
    }

    public void genericGlassPane(String color) {
        String path = BLOCK_FOLDER + "/" + (color.equals("") ? "" : color + "_stained_");

        BlockModelBuilder glassPaneModel = models().getBuilder(path + "glasspane");
        glassPaneModel
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
                .texture("edge", new ResourceLocation("minecraft", path + "glass_pane_top"));
    }

    @Override
    protected void registerStatesAndModels() {
        for (String c: colors) {
            genericGlassPane(c);
        }
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
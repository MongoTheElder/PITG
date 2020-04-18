package tv.mongotheelder.pitg.blocks;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class DualGlassPane extends GlassPane {
    public DualGlassPane(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecated")
    protected VoxelShape[] makeShapes(double paneWidth, double paneThickness, double paneHeight) {
        VoxelShape south = Block.makeCuboidShape(0.0D, 0.0D, paneWidth-paneThickness, paneWidth, paneHeight, paneWidth);
        VoxelShape west = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, paneThickness, paneHeight, paneWidth);
        VoxelShape north = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, paneWidth, paneHeight, paneThickness);
        VoxelShape east = Block.makeCuboidShape(paneWidth-paneThickness, 0.0D, 0.0D, paneWidth, paneHeight, paneWidth);
        VoxelShape empty = VoxelShapes.empty();
        VoxelShape[] shapes = new VoxelShape[]{
                empty, // 0000
                VoxelShapes.or(north, south), // 0001
                VoxelShapes.or(east, west), // 0010
                VoxelShapes.or(north, east, south, west), // 0011
                VoxelShapes.or(north, south), // 0100
                empty, // 0101
                VoxelShapes.or(north, east, south, west), // 0110
                empty, // 0111
                VoxelShapes.or(east, west), // 1000
                VoxelShapes.or(north, east, south, west), // 1001
                empty, // 1010
                empty, // 1011
                VoxelShapes.or(north, east, south, west), // 1100
                empty, // 1101
                empty, // 1110
                empty // 1111
        };
        return shapes;
    }
}

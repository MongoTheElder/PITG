package tv.mongotheelder.pitg.blocks;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mongotheelder.pitg.Config;

public class GlassPane extends Block implements IWaterLoggable {
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");
    public static final BooleanProperty UNBREAKABLE = BooleanProperty.create("unbreakable");
    public static final int NORTH_MASK = 0b0001;
    public static final int WEST_MASK = 0b0010;
    public static final int SOUTH_MASK = 0b0100;
    public static final int EAST_MASK = 0b1000;

    protected final VoxelShape[] shapes;
    protected final VoxelShape[] collisionShapes;
    protected final Object2IntMap<BlockState> indexHash = new Object2IntOpenHashMap<>();
    protected static final double PANE_WIDTH = 16.0;
    protected static final double PANE_THICKNESS = 2.0;
    protected static final double PANE_HEIGHT = 16.0;

    protected static final double CORNER_HITBOX_SIZE = 0.25;

    protected static final Logger LOGGER = LogManager.getLogger();

    public GlassPane(Properties properties) {
        super(properties);
        this.shapes = this.makeShapes(PANE_WIDTH, PANE_THICKNESS, PANE_HEIGHT);
        this.collisionShapes = this.makeShapes(PANE_WIDTH, PANE_THICKNESS, PANE_HEIGHT);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(NORTH, Boolean.FALSE)
                .with(EAST, Boolean.FALSE)
                .with(SOUTH, Boolean.FALSE)
                .with(WEST, Boolean.FALSE)
                .with(UNBREAKABLE, Boolean.FALSE)
                .with(WATERLOGGED, Boolean.FALSE));
    }

    protected VoxelShape[] makeShapes(double paneWidth, double paneThickness, double paneHeight) {
        VoxelShape south = Block.makeCuboidShape(0.0D, 0.0D, paneWidth-paneThickness, paneWidth, paneHeight, paneWidth);
        VoxelShape west = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, paneThickness, paneHeight, paneWidth);
        VoxelShape north = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, paneWidth, paneHeight, paneThickness);
        VoxelShape east = Block.makeCuboidShape(paneWidth-paneThickness, 0.0D, 0.0D, paneWidth, paneHeight, paneWidth);
        VoxelShape empty = VoxelShapes.empty();
        VoxelShape[] shapes = new VoxelShape[]{
                empty, // 0000
                north, // 0001
                west, // 0010
                VoxelShapes.or(north, west), // 0011
                south, // 0100
                empty, // 0101
                VoxelShapes.or(south, west), // 0110
                empty, // 0111
                east, // 1000
                VoxelShapes.or(north, east), // 1001
                empty, // 1010
                empty, // 1011
                VoxelShapes.or(south, east), // 1100
                empty, // 1101
                empty, // 1110
                empty // 1111
        };
        return shapes;
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return !state.get(WATERLOGGED);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.shapes[this.getIndex(state)];
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.collisionShapes[this.getIndex(state)];
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getBlockHardness(BlockState blockState, IBlockReader worldIn, BlockPos pos) {
        return Config.ENABLE_UNBREAKABLE.get() && blockState.get(UNBREAKABLE) ? -1.0f : 0.3f;
    }

    protected int getIndex(BlockState state) {
        return this.indexHash.computeIntIfAbsent(state, (blockState) -> {
            int i = 0;
            if (blockState.get(NORTH)) {
                i |= NORTH_MASK;
            }

            if (blockState.get(EAST)) {
                i |= EAST_MASK;
            }

            if (blockState.get(SOUTH)) {
                i |= SOUTH_MASK;
            }

            if (blockState.get(WEST)) {
                i |= WEST_MASK;
            }

            return i;
        });
    }

    @Override
    @SuppressWarnings("deprecation")
    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot) {
        switch (rot) {
            case CLOCKWISE_180:
                return state.with(NORTH, state.get(SOUTH)).with(EAST, state.get(WEST)).with(SOUTH, state.get(NORTH)).with(WEST, state.get(EAST));
            case COUNTERCLOCKWISE_90:
                // OK, this is a bit of a hack: COUNTERCLOCKWISE is used to rotate the pane 90 keeping the same style
                // (N, E, S, W) or (NE, SE, SW, NW)
                return state.with(NORTH, state.get(WEST))
                        .with(EAST, state.get(NORTH))
                        .with(SOUTH, state.get(EAST))
                        .with(WEST, state.get(SOUTH));
            case CLOCKWISE_90:
                // Rotate through all 8 facing states (N, NE, E, SE, S, SW, W, NW)
                return state.with(NORTH, (state.get(WEST) || state.get(NORTH)) && !(state.get(EAST) || state.get(SOUTH)))
                        .with(EAST, (state.get(EAST) || state.get(NORTH)) && !(state.get(WEST) || state.get(SOUTH)))
                        .with(SOUTH, (state.get(EAST) || state.get(SOUTH)) && !(state.get(WEST) || state.get(NORTH)))
                        .with(WEST, (state.get(WEST) || state.get(SOUTH)) && !(state.get(EAST) || state.get(NORTH)));
            default:
                return state;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        switch (mirrorIn) {
            case LEFT_RIGHT:
                return state.with(NORTH, state.get(SOUTH)).with(SOUTH, state.get(NORTH));
            case FRONT_BACK:
                return state.with(EAST, state.get(WEST)).with(WEST, state.get(EAST));
            default:
                return super.mirror(state, mirrorIn);
        }
    }

    /**
     * Determine which faces to include based on where the player clicked.
     *
     * The possible directions are: NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST, EAST, NORTHEAST
     * by combining a primary face and one optional adjoining face. For example, clicking in the north center of a block
     * will generate only the north facing pane while clicking on the northwest corner will generate both north and west
     * panes. If the player clicks in the center of the block, their facing direction will be used to determine which
     * pane to generate.
     */
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        Direction direction = context.getFace();
        BlockPos blockpos = context.getPos();

        // Determine which sides to include based on click location
        // If placed on the vertical face of a block, suppress including the matching face unless center clicked
        boolean clickedSouth = ((context.getHitVec().z - (double) blockpos.getZ()) >= (1.0D - CORNER_HITBOX_SIZE)) && (direction != Direction.NORTH);
        boolean clickedNorth = ((context.getHitVec().z - (double) blockpos.getZ()) <= CORNER_HITBOX_SIZE) && (direction != Direction.SOUTH);
        boolean clickedEast = ((context.getHitVec().x - (double) blockpos.getX()) >= (1.0D - CORNER_HITBOX_SIZE)) && (direction != Direction.WEST);
        boolean clickedWest = ((context.getHitVec().x - (double) blockpos.getX()) <= CORNER_HITBOX_SIZE)  && (direction != Direction.EAST);
        boolean horizontalFaceClicked = !(clickedNorth || clickedEast || clickedSouth || clickedWest);

        // If the center of the face is clicked, default to the predominate horizontal facing direction
        if (horizontalFaceClicked) {
            Direction[] lookingDirections = context.getNearestLookingDirections();
            Direction lookingDirection = (lookingDirections[0].getAxis().isHorizontal() ? lookingDirections[0] : lookingDirections[1]);
            switch (lookingDirection) {
                case NORTH:
                    clickedNorth = true;
                    break;
                case WEST:
                    clickedWest = true;
                    break;
                case SOUTH:
                    clickedSouth = true;
                    break;
                case EAST:
                    clickedEast = true;
                    break;
                default:
                    LOGGER.info("Center click was not able to determine facing direction");
                    clickedNorth = true;
            }
        }

        IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState()
                .with(NORTH, clickedNorth)
                .with(WEST, clickedWest)
                .with(SOUTH, clickedSouth)
                .with(EAST, clickedEast)
                .with(UNBREAKABLE, false)
                .with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
    }

    // Register properties for a glass pane
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, WEST, SOUTH, UNBREAKABLE, WATERLOGGED);
    }

}
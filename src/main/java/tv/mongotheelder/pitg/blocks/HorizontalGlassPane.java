package tv.mongotheelder.pitg.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mongotheelder.pitg.Config;

public class HorizontalGlassPane extends Block {
    public static final BooleanProperty UNBREAKABLE = BooleanProperty.create("unbreakable");

    protected static final double PANE_SIDE = 16.0;
    protected static final double PANE_THICKNESS = 2.0;
    protected static final double PANE_HEIGHT = 16.0;

    private static final VoxelShape LOWER = Block.box(0.0D, 0.0D, 0.0D, PANE_SIDE, PANE_THICKNESS, PANE_SIDE);
    private static final VoxelShape UPPER = Block.box(0.0D, PANE_HEIGHT - PANE_THICKNESS, 0.0D, PANE_SIDE, PANE_HEIGHT, PANE_SIDE);

    protected static final Logger LOGGER = LogManager.getLogger();

    public HorizontalGlassPane(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(UNBREAKABLE, Boolean.FALSE)
                .setValue(BlockStateProperties.WATERLOGGED, Boolean.FALSE)
                .setValue(BlockStateProperties.HALF, Half.BOTTOM));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return (state.getValue(BlockStateProperties.HALF) == Half.BOTTOM) ? LOWER : UPPER;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getDestroyProgress(BlockState state, PlayerEntity player, IBlockReader worldIn, BlockPos pos) {
        return Config.ENABLE_UNBREAKABLE.get() && state.getValue(UNBREAKABLE) ? -1.0f : 0.3f;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {

        BlockPos blockpos = context.getClickedPos();

        boolean clickedUpper = ((context.getClickLocation().y - (double) blockpos.getY()) >= 0.5);

        FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(BlockStateProperties.HALF, clickedUpper ? Half.TOP : Half.BOTTOM)
                .setValue(BlockStateProperties.WATERLOGGED, ifluidstate.getType() == Fluids.WATER)
                .setValue(UNBREAKABLE, false);
    }

    // Register properties for a glass pane
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HALF, UNBREAKABLE, BlockStateProperties.WATERLOGGED);
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return !state.getValue(BlockStateProperties.WATERLOGGED);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}

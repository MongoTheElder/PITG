package tv.mongotheelder.pitg.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mongotheelder.pitg.blocks.GlassPane;


public class GlazingTool extends Item {
    private static final Logger LOGGER = LogManager.getLogger();

    public GlazingTool(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote) {
            PlayerEntity player = context.getPlayer();
            ItemStack itemstack = context.getItem();
            BlockPos blockpos = context.getPos();
            Direction direction = context.getFace();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            if (block instanceof GlassPane) {
                // If player is crouching, rotate the pane keeping the facing style consistent (i.e. N->E, SW->NW, etc)
                // Note: COUNTERCLOCKWISE_90 is being used to pass player crouch context and doesn't represent direction (all rotations are CLOCKWISE_90)
                world.setBlockState(blockpos, block.rotate(blockstate, world, blockpos, player.isCrouching() ? Rotation.COUNTERCLOCKWISE_90: Rotation.CLOCKWISE_90));
            }
        }
        return ActionResultType.SUCCESS;
    }
}

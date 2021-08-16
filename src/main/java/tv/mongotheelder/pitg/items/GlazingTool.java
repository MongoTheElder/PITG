package tv.mongotheelder.pitg.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mongotheelder.pitg.Config;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.blocks.GlassPane;
import tv.mongotheelder.pitg.blocks.HorizontalGlassPane;

import javax.annotation.Nullable;
import java.util.List;

public class GlazingTool extends Item {
    private static final Logger LOGGER = LogManager.getLogger();

    public GlazingTool(Item.Properties properties) {
        super(properties);
    }

    public static GlazingToolMode getMode(ItemStack itemStack) {
        if (itemStack.getItem() instanceof GlazingTool) {
            return readMode(itemStack);
        }
        return GlazingToolMode.ROTATE;
    }

    public void writeMode(ItemStack itemStack, GlazingToolMode mode) {
        if (itemStack.getItem() instanceof GlazingTool) {
            CompoundNBT tag = itemStack.getOrCreateTag();
            tag.putString(Pitg.GLAZING_TOOL_TAG, mode.getSerializedName().toLowerCase());
        }
    }

    public static GlazingToolMode readMode(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if (tag == null || !(stack.getItem() instanceof GlazingTool)) return GlazingToolMode.ROTATE;
        return GlazingToolMode.byName(tag.getString(Pitg.GLAZING_TOOL_TAG).toLowerCase());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (Config.ENABLE_PANE_BREAK.get() || Config.ENABLE_UNBREAKABLE.get()) {
            tooltip.add(new StringTextComponent("Mode: " + readMode(stack).getSerializedName()).withStyle(TextFormatting.DARK_GREEN));
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return (Config.ENABLE_PANE_BREAK.get() && readMode(stack) == GlazingToolMode.BREAK) || stack.isEnchanted();
    }

    private Half flip(BlockState state) {
        return state.getValue(BlockStateProperties.HALF) == Half.TOP ? Half.BOTTOM : Half.TOP;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        if (!world.isClientSide) {
            PlayerEntity player = context.getPlayer();
            BlockPos blockpos = context.getClickedPos();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            GlazingToolMode mode = readMode(context.getItemInHand());
            if (block instanceof GlassPane || block instanceof HorizontalGlassPane) {
                if (Config.ENABLE_PANE_BREAK.get() && mode == GlazingToolMode.BREAK) {
                    if (player != null && player.isCrouching()) {
                        world.removeBlock(blockpos, false);
                        Block.popResource(world, blockpos, block.getPickBlock(blockstate, null, world, blockpos, player));
                    }

                } else if (Config.ENABLE_UNBREAKABLE.get() && mode == GlazingToolMode.UNBREAKABLE) {
                    world.setBlockAndUpdate(blockpos, blockstate.setValue(GlassPane.UNBREAKABLE, !blockstate.getValue(GlassPane.UNBREAKABLE)));
                } else {
                    if (block instanceof GlassPane) {
                        // If player is crouching, rotate the pane keeping the facing style consistent (i.e. N->E, SW->NW, etc)
                        // Note: COUNTERCLOCKWISE_90 is being used to pass player crouch context and doesn't represent direction (all rotations are CLOCKWISE_90)
                        world.setBlockAndUpdate(blockpos, block.rotate(blockstate, world, blockpos, player != null && player.isCrouching() ? Rotation.COUNTERCLOCKWISE_90 : Rotation.CLOCKWISE_90));

                    } else if (block instanceof HorizontalGlassPane) {
                        world.setBlockAndUpdate(blockpos, blockstate.setValue(BlockStateProperties.HALF, flip(blockstate)));
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        // change mode
        if (!worldIn.isClientSide()) {
            ItemStack toolItem = playerIn.getItemInHand(handIn);
            writeMode(toolItem, readMode(toolItem).advanceMode());
        }
        return ActionResult.success(playerIn.getItemInHand(handIn));
    }
}

package tv.mongotheelder.pitg.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
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
            tag.putString(Pitg.GLAZING_TOOL_TAG, mode.getString().toLowerCase());
        }
    }

    public static GlazingToolMode readMode(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if (tag == null || !(stack.getItem() instanceof GlazingTool)) return GlazingToolMode.ROTATE;
        return GlazingToolMode.byName(tag.getString(Pitg.GLAZING_TOOL_TAG).toLowerCase());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (Config.ENABLE_PANE_BREAK.get() || Config.ENABLE_UNBREAKABLE.get()) {
            tooltip.add(new StringTextComponent("Mode: " + readMode(stack).getString()).mergeStyle(TextFormatting.DARK_GREEN));
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return (Config.ENABLE_PANE_BREAK.get() && readMode(stack) == GlazingToolMode.BREAK) || stack.isEnchanted();
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote) {
            PlayerEntity player = context.getPlayer();
            BlockPos blockpos = context.getPos();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            GlazingToolMode mode = readMode(context.getItem());
            if (block instanceof GlassPane) {
                if (Config.ENABLE_PANE_BREAK.get() && mode == GlazingToolMode.BREAK) {
                    if (player != null && player.isCrouching()) {
                        world.removeBlock(blockpos, false);
                        Block.spawnAsEntity(world, blockpos, block.getPickBlock(blockstate, null, world, blockpos, player));
                    }

                } else if (Config.ENABLE_UNBREAKABLE.get() && mode == GlazingToolMode.UNBREAKABLE) {
                    world.setBlockState(blockpos, blockstate.with(GlassPane.UNBREAKABLE, !blockstate.get(GlassPane.UNBREAKABLE)));
                } else {
                    // If player is crouching, rotate the pane keeping the facing style consistent (i.e. N->E, SW->NW, etc)
                    // Note: COUNTERCLOCKWISE_90 is being used to pass player crouch context and doesn't represent direction (all rotations are CLOCKWISE_90)
                    world.setBlockState(blockpos, block.rotate(blockstate, world, blockpos, player != null && player.isCrouching() ? Rotation.COUNTERCLOCKWISE_90 : Rotation.CLOCKWISE_90));
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        // change mode
        if (!worldIn.isRemote()) {
            ItemStack toolItem = playerIn.getHeldItem(handIn);
            writeMode(toolItem, readMode(toolItem).advanceMode());
        }
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }
}

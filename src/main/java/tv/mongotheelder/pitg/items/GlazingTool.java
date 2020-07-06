package tv.mongotheelder.pitg.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.blocks.GlassPane;
import tv.mongotheelder.pitg.networking.ModePacket;
import tv.mongotheelder.pitg.networking.PacketHandler;
import tv.mongotheelder.pitg.setup.Config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class GlazingTool extends Item {
    private static final Logger LOGGER = LogManager.getLogger();

    public GlazingTool(Item.Properties properties) {
        super(properties);
        this.addPropertyOverride(new ResourceLocation("pitg:unbreaking"), (itemStack, world, livingEntity) -> {
            if (livingEntity != null) {
                boolean flag = livingEntity.getHeldItemMainhand() == itemStack;
                if (livingEntity.getHeldItemMainhand().getItem() instanceof GlazingTool) {
                    return flag && getMode(itemStack) == GlazingToolMode.UNBREAKABLE ? 1.0f : 0.0f;
                }
             }
            return 0.0F;
        });
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (Config.ENABLE_PANE_BREAK.get() || Config.ENABLE_UNBREAKABLE.get()) {
            tooltip.add(new StringTextComponent("Mode: " + getMode(stack).getTitle()));
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return (Config.ENABLE_PANE_BREAK.get() && getMode(stack) == GlazingToolMode.BREAK) || stack.isEnchanted();
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
                if (Config.ENABLE_PANE_BREAK.get() && getMode(itemstack) == GlazingToolMode.BREAK) {
                    if (player != null && player.isCrouching()) {
                        world.removeBlock(blockpos, false);
                        Block.spawnAsEntity(world, blockpos, block.getPickBlock(blockstate, null, world, blockpos, player));
                    }

                } else if (Config.ENABLE_UNBREAKABLE.get() && getMode(itemstack) == GlazingToolMode.UNBREAKABLE) {
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
        if (!worldIn.isRemote()) {
            ItemStack stack = playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            if (!stack.isEmpty() && stack.getItem() instanceof GlazingTool) {
                GlazingToolMode mode = advanceMode(stack);
                ((GlazingTool) (stack.getItem())).setMode(stack, mode);
                PacketHandler.sendToPlayer(new ModePacket(mode, EquipmentSlotType.MAINHAND), (ServerPlayerEntity) playerIn);
            } else {
                LOGGER.error("GlazingTool::onItemRightClick was triggered for an illegal item: " + stack);
            }
        }
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    public GlazingToolMode getMode(ItemStack stack) {
        return hasValidTag(stack) ? GlazingToolMode.byIndex(stack.getTag().getInt(Pitg.GLAZING_TOOL_MODE_KEY)) : GlazingToolMode.ROTATE;
    }

    public void setMode(ItemStack stack, GlazingToolMode mode) {
        CompoundNBT tag = stack.getOrCreateTag();
        tag.putInt(Pitg.GLAZING_TOOL_MODE_KEY, mode.getIndex());
        stack.setTag(tag);
    }

    public GlazingToolMode advanceMode(@Nonnull ItemStack stack) {
        GlazingToolMode mode = getMode(stack);
        GlazingToolMode newMode = getMode(stack).advanceMode();
        if (mode != newMode) {
            setMode(stack, newMode);
        }
        return getMode(stack);
    }

    private boolean hasValidTag(ItemStack stack) {
        if (stack == null || stack.getTag() == null) return false;
        CompoundNBT tag = stack.getOrCreateTag();
        return tag.contains(Pitg.GLAZING_TOOL_MODE_KEY, Constants.NBT.TAG_INT);
    }
}

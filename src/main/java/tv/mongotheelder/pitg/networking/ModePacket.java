package tv.mongotheelder.pitg.networking;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.items.GlazingTool;
import tv.mongotheelder.pitg.items.GlazingToolMode;

import java.util.function.Supplier;

public class ModePacket {
    private static final Logger LOGGER = LogManager.getLogger();

    private final GlazingToolMode mode;
    private final EquipmentSlotType slot;

    public ModePacket(GlazingToolMode mode, EquipmentSlotType slot) {
        this.mode = mode;
        this.slot = slot;
    }

    public static void handle(ModePacket msg, Supplier<Context> context) {
        PlayerEntity player = Pitg.proxy.getPlayer(context);
        if (player == null) {
            LOGGER.error("Received a Glazing Tool mode change packet from a null player");
            return;
        }
        context.get().enqueueWork(() -> {
            ItemStack stack = player.getItemStackFromSlot(msg.slot);
            if (!stack.isEmpty() && stack.getItem() instanceof GlazingTool) {
                ((GlazingTool) stack.getItem()).setMode(stack, msg.mode);
                LOGGER.debug("Setting mode on client to " + ((GlazingTool) (stack.getItem())).getMode(stack));
            } else {
                LOGGER.warn("Glazing Tool mode change could not locate the tool in the player");
            }
        });
        context.get().setPacketHandled(true);
    }

    public static void encode(ModePacket pkt, PacketBuffer buf) {
        buf.writeEnumValue(pkt.mode);
        buf.writeEnumValue(pkt.slot);
    }

    public static ModePacket decode(PacketBuffer buf) {
        return new ModePacket(buf.readEnumValue(GlazingToolMode.class), buf.readEnumValue(EquipmentSlotType.class));
    }
}

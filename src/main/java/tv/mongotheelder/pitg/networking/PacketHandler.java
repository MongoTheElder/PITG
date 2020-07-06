package tv.mongotheelder.pitg.networking;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import tv.mongotheelder.pitg.Pitg;

public class PacketHandler {
    private static SimpleChannel NETWORK_CHANNEL;
    private static int messageId = 0;

    public static void registerMessages() {
        NETWORK_CHANNEL = createChannel(Pitg.NETWORK_CHANNEL);
        NETWORK_CHANNEL.messageBuilder(ModePacket.class, nextMessageId())
                .encoder(ModePacket::encode)
                .decoder(ModePacket::decode)
                .consumer(ModePacket::handle)
                .add();
    }

    private static int nextMessageId() {
        return messageId++;
    }

    private static SimpleChannel createChannel(ResourceLocation channelName) {
        return NetworkRegistry.ChannelBuilder.named(channelName)
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .networkProtocolVersion(() -> "1.0")
                .simpleChannel();
    }

    public static void sendToPlayer(Object pkt, ServerPlayerEntity player) {
        NETWORK_CHANNEL.sendTo(pkt, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }
}

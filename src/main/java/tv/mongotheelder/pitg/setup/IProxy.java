package tv.mongotheelder.pitg.setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

public interface IProxy {

    World getClientWorld();

    PlayerEntity getPlayer(Supplier<Context> context);
}

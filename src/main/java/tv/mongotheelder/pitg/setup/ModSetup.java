package tv.mongotheelder.pitg.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.networking.PacketHandler;

@Mod.EventBusSubscriber(modid = Pitg.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    public static final ItemGroup ITEM_GROUP = new ItemGroup("pitg") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.GLASS_PANE.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {
        PacketHandler.registerMessages();
    }
}

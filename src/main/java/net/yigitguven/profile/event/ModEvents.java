package net.yigitguven.profile.event;

import net.yigitguven.profile.ProfileMod;
import net.yigitguven.profile.network.ProfileNetwork;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProfileMod.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof Player targetPlayer) {
            if (!event.getLevel().isClientSide && event.getEntity() instanceof ServerPlayer serverPlayer) {
                ProfileNetwork.sendProfileToClient(serverPlayer, targetPlayer);
            }
            event.setCanceled(true);
        }
    }
}

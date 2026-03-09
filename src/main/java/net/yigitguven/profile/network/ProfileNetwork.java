package net.yigitguven.profile.network;

import net.yigitguven.profile.api.ProfileRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class ProfileNetwork {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("profile", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    public static void init() {
        CHANNEL.registerMessage(0, S2CProfilePacket.class, S2CProfilePacket::encode, S2CProfilePacket::new,
                S2CProfilePacket::handle);
    }

    public static void sendProfileToClient(ServerPlayer receiver, Player targetPlayer) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> receiver),
                new S2CProfilePacket(ProfileRegistry.getComponents(targetPlayer)));
    }
}

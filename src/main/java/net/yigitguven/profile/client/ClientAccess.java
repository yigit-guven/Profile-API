package net.yigitguven.profile.client;

import net.yigitguven.profile.network.S2CProfilePacket;
import net.minecraft.client.Minecraft;
import java.util.List;

public class ClientAccess {
    public static void openProfileScreen(List<S2CProfilePacket.ProfileData> data) {
        Minecraft.getInstance().setScreen(new ProfileScreen(data));
    }
}

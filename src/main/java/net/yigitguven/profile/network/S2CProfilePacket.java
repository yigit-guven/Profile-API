package net.yigitguven.profile.network;

import net.yigitguven.profile.api.ProfileComponent;
import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;
import net.minecraft.world.item.ItemStack;
import java.util.UUID;
import java.util.ArrayList;

public class S2CProfilePacket {
    private final UUID targetUuid;
    private final String targetName;
    private final List<ItemStack> armorItems;
    private final List<ProfileData> data;

    public S2CProfilePacket(UUID targetUuid, String targetName, List<ItemStack> armorItems,
            List<ProfileComponent> components) {
        this.targetUuid = targetUuid;
        this.targetName = targetName;
        this.armorItems = armorItems;
        this.data = new ArrayList<>();
        for (ProfileComponent comp : components) {
            this.data.add(new ProfileData(comp.getId(), comp.getTitle(), comp.getValue(), comp.getIcon()));
        }
    }

    public S2CProfilePacket(FriendlyByteBuf buf) {
        this.targetUuid = buf.readUUID();
        this.targetName = buf.readUtf();
        this.armorItems = buf.readCollection(ArrayList::new, FriendlyByteBuf::readItem);
        this.data = buf.readList(b -> new ProfileData(
                b.readResourceLocation(),
                b.readComponent(),
                b.readComponent(),
                b.readBoolean() ? b.readResourceLocation() : null));
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(targetUuid);
        buf.writeUtf(targetName);
        buf.writeCollection(armorItems, FriendlyByteBuf::writeItem);
        buf.writeCollection(data, (b, d) -> {
            b.writeResourceLocation(d.id);
            b.writeComponent(d.title);
            b.writeComponent(d.value);
            if (d.icon != null) {
                b.writeBoolean(true);
                b.writeResourceLocation(d.icon);
            } else {
                b.writeBoolean(false);
            }
        });
    }

    public UUID getTargetUuid() {
        return targetUuid;
    }

    public String getTargetName() {
        return targetName;
    }

    public List<ItemStack> getArmorItems() {
        return armorItems;
    }

    public List<ProfileData> getData() {
        return data;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Handle on client: Open Screen with this data
            net.yigitguven.profile.client.ClientAccess.openProfileScreen(this);
        });
        ctx.get().setPacketHandled(true);
    }

    public static class ProfileData {
        public final ResourceLocation id;
        public final Component title;
        public final Component value;
        public final ResourceLocation icon;

        public ProfileData(ResourceLocation id, Component title, Component value, ResourceLocation icon) {
            this.id = id;
            this.title = title;
            this.value = value;
            this.icon = icon;
        }
    }
}

package net.yigitguven.profile.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.yigitguven.profile.network.S2CProfilePacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ProfileScreen extends Screen {
    private final S2CProfilePacket packet;
    private int guiWidth = 248;
    private int guiHeight = 166;
    private int leftPos;
    private int topPos;

    public ProfileScreen(S2CProfilePacket packet) {
        super(Component.literal("Player Profile"));
        this.packet = packet;
    }

    @Override
    protected void init() {
        this.leftPos = (this.width - this.guiWidth) / 2;
        this.topPos = (this.height - this.guiHeight) / 2;
    }

    private int parseColor(String hex, int defaultColor) {
        try {
            if (hex != null && hex.startsWith("#")) {
                return (int) Long.parseLong(hex.substring(1), 16);
            }
        } catch (Exception e) {
        }
        return defaultColor;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);

        // Config settings
        int bgColor = parseColor(net.yigitguven.profile.Config.BACKGROUND_COLOR.get(), 0xC0101010);
        int textColor = parseColor(net.yigitguven.profile.Config.TEXT_COLOR.get(), 0xFFFFFF);
        boolean showModel = net.yigitguven.profile.Config.SHOW_PLAYER_MODEL.get();
        int modelScale = net.yigitguven.profile.Config.MODEL_SCALE.get();

        // Draw custom background
        guiGraphics.fill(leftPos, topPos, leftPos + guiWidth, topPos + guiHeight, bgColor);

        // Draw Borders
        guiGraphics.renderOutline(leftPos, topPos, guiWidth, guiHeight, 0xFFFFFFFF);

        // Title
        guiGraphics.drawString(this.font, packet.getTargetName() + "'s Profile",
                leftPos + guiWidth / 2 - this.font.width(packet.getTargetName() + "'s Profile") / 2, topPos + 8,
                textColor, true);

        // Draw Armor
        int armorX = leftPos + 10;
        int armorY = topPos + 30;
        if (packet.getArmorItems() != null) {
            for (int i = packet.getArmorItems().size() - 1; i >= 0; i--) {
                net.minecraft.world.item.ItemStack stack = packet.getArmorItems().get(i);
                guiGraphics.fill(armorX - 1, armorY - 1, armorX + 17, armorY + 17, 0x80000000); // slot bg
                guiGraphics.renderItem(stack, armorX, armorY);
                guiGraphics.renderItemDecorations(this.font, stack, armorX, armorY);
                armorY += 20;
            }
        }

        // Draw Player Model
        if (showModel && this.minecraft != null && this.minecraft.level != null) {
            net.minecraft.world.entity.player.Player targetEntity = this.minecraft.level
                    .getPlayerByUUID(packet.getTargetUuid());
            if (targetEntity == null)
                targetEntity = this.minecraft.player; // fallback
            if (targetEntity != null) {
                int modelX = leftPos + 60;
                int modelY = topPos + 110;
                net.minecraft.client.gui.screens.inventory.InventoryScreen.renderEntityInInventoryFollowsMouse(
                        guiGraphics, modelX, modelY, modelScale, modelX - mouseX, modelY - 50 - mouseY, targetEntity);
            }
        }

        // Draw Data
        int yOffset = 30;
        for (S2CProfilePacket.ProfileData data : packet.getData()) {
            guiGraphics.drawString(this.font, data.title.getString() + ": " + data.value.getString(), leftPos + 100,
                    topPos + yOffset, textColor, true);
            yOffset += 15;
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

package net.yigitguven.profile.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.yigitguven.profile.network.S2CProfilePacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import java.util.List;

public class ProfileScreen extends Screen {
    private static final ResourceLocation BACKGROUND = new ResourceLocation("minecraft",
            "textures/gui/demo_background.png");
    private final List<S2CProfilePacket.ProfileData> profileData;
    private int guiWidth = 248;
    private int guiHeight = 166;
    private int leftPos;
    private int topPos;

    public ProfileScreen(List<S2CProfilePacket.ProfileData> data) {
        super(Component.literal("Player Profile"));
        this.profileData = data;
    }

    @Override
    protected void init() {
        this.leftPos = (this.width - this.guiWidth) / 2;
        this.topPos = (this.height - this.guiHeight) / 2;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        guiGraphics.blit(BACKGROUND, leftPos, topPos, 0, 0, guiWidth, guiHeight);

        guiGraphics.drawString(this.font, this.title, leftPos + 8, topPos + 8, 4210752, false);

        int yOffset = 30;
        for (S2CProfilePacket.ProfileData data : profileData) {
            guiGraphics.drawString(this.font, data.title.getString() + ": " + data.value.getString(), leftPos + 15,
                    topPos + yOffset, 0xFFFFFF, true);
            yOffset += 15;
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

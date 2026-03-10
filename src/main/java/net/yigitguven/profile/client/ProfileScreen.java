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
        int highlightColor = parseColor(net.yigitguven.profile.Config.HIGHLIGHT_COLOR.get(), 0x55FFFF);
        boolean showModel = net.yigitguven.profile.Config.SHOW_PLAYER_MODEL.get();
        int modelScale = net.yigitguven.profile.Config.MODEL_SCALE.get();
        boolean vanillaStyle = net.yigitguven.profile.Config.VANILLA_STYLE_UI.get();
        boolean showSkinHead = net.yigitguven.profile.Config.SHOW_SKIN_HEAD.get();
        boolean showIcons = net.yigitguven.profile.Config.SHOW_ICONS.get();
        boolean fancyPanels = net.yigitguven.profile.Config.USE_FANCY_PANELS.get();
        boolean enableTooltips = net.yigitguven.profile.Config.ENABLE_ARMOR_TOOLTIPS.get();

        // Draw custom background
        if (vanillaStyle) {
            guiGraphics.blit(new ResourceLocation("textures/gui/demo_background.png"), leftPos, topPos, 0, 0, guiWidth,
                    guiHeight, 256, 256);
        } else {
            guiGraphics.fill(leftPos, topPos, leftPos + guiWidth, topPos + guiHeight, bgColor);
            guiGraphics.renderOutline(leftPos, topPos, guiWidth, guiHeight, 0xFFFFFFFF);
        }

        // --- Render Header ---
        net.minecraft.world.entity.player.Player target = null;
        if (this.minecraft != null && this.minecraft.level != null) {
            target = this.minecraft.level.getPlayerByUUID(packet.getTargetUuid());
        }
        int titleColor = highlightColor;
        if (target != null) {
            // Try to get team color or name color
            net.minecraft.network.chat.Style style = target.getDisplayName().getStyle();
            if (style != null && style.getColor() != null) {
                titleColor = style.getColor().getValue();
            }
        }

        int titleWidth = this.font.width(packet.getTargetName() + "'s Profile");
        int titleX = leftPos + guiWidth / 2 - titleWidth / 2;
        int titleY = topPos + 8;

        if (fancyPanels) {
            // Title background bar
            guiGraphics.fill(leftPos + 5, topPos + 5, leftPos + guiWidth - 5, topPos + 20, 0x40000000);
            guiGraphics.renderOutline(leftPos + 5, topPos + 5, guiWidth - 10, 15, 0x80FFFFFF);
        }

        guiGraphics.drawString(this.font, packet.getTargetName() + "'s Profile", titleX, titleY, titleColor,
                !vanillaStyle);

        // Draw Skin Head
        if (showSkinHead) {
            ResourceLocation skinLoc = net.minecraft.client.resources.DefaultPlayerSkin
                    .getDefaultSkin(packet.getTargetUuid());
            if (this.minecraft != null && this.minecraft.getConnection() != null) {
                net.minecraft.client.multiplayer.PlayerInfo playerInfo = this.minecraft.getConnection()
                        .getPlayerInfo(packet.getTargetUuid());
                if (playerInfo != null)
                    skinLoc = playerInfo.getSkinLocation();
            }
            try {
                // centered vertically with text (text is ~9px high, head is 8px)
                net.minecraft.client.gui.components.PlayerFaceRenderer.draw(guiGraphics, skinLoc, titleX - 12,
                        titleY + 1, 8);
            } catch (Exception e) {
            }
        }

        // --- Render Model Area ---
        if (showModel) {
            int modelBoxX = leftPos + 10;
            int modelBoxY = topPos + 25;
            int modelBoxW = 75;
            int modelBoxH = 130;

            if (fancyPanels) {
                // Recessed frame for model
                guiGraphics.fill(modelBoxX, modelBoxY, modelBoxX + modelBoxW, modelBoxY + modelBoxH, 0x60000000);
                guiGraphics.renderOutline(modelBoxX, modelBoxY, modelBoxW, modelBoxH, 0xFF707070); // Dark border
                guiGraphics.renderOutline(modelBoxX - 1, modelBoxY - 1, modelBoxW + 2, modelBoxH + 2, 0xFF303030); // Outer
                                                                                                                   // edge

                // Header for model box - a bit lower
                guiGraphics.fill(modelBoxX, modelBoxY, modelBoxX + modelBoxW, modelBoxY + 12, 0x80000000);
                guiGraphics.drawString(this.font, "Appearance",
                        modelBoxX + (modelBoxW / 2 - this.font.width("Appearance") / 2), modelBoxY + 2, 0xFFAAAAAA,
                        false);
            }

            net.minecraft.world.entity.player.Player targetEntity = target;
            if (targetEntity == null && this.minecraft != null)
                targetEntity = this.minecraft.player;

            if (targetEntity != null) {
                // Repositioned: higher and a bit right to align chest with slots
                int renderX = modelBoxX + modelBoxW / 2 + 10;
                int renderY = modelBoxY + modelBoxH - 45;
                net.minecraft.client.gui.screens.inventory.InventoryScreen.renderEntityInInventoryFollowsMouse(
                        guiGraphics, renderX, renderY, modelScale, renderX - mouseX, renderY - 50 - mouseY,
                        targetEntity);
            }

            // Draw Armor
            int armorX = modelBoxX + 5;
            int armorY = modelBoxY + 15; // lower to fit banner
            if (packet.getArmorItems() != null) {
                for (int i = packet.getArmorItems().size() - 1; i >= 0; i--) {
                    net.minecraft.world.item.ItemStack stack = packet.getArmorItems().get(i);
                    if (vanillaStyle) {
                        guiGraphics.blit(new ResourceLocation("textures/gui/container/inventory.png"), armorX - 1,
                                armorY - 1, 7, 7, 18, 18, 256, 256);
                    } else {
                        guiGraphics.fill(armorX - 1, armorY - 1, armorX + 17, armorY + 17, 0x80000000);
                    }
                    guiGraphics.renderItem(stack, armorX, armorY);
                    guiGraphics.renderItemDecorations(this.font, stack, armorX, armorY);

                    // Tooltip logic
                    if (enableTooltips && mouseX >= armorX && mouseX < armorX + 16 && mouseY >= armorY
                            && mouseY < armorY + 16) {
                        guiGraphics.renderTooltip(this.font, stack, mouseX, mouseY);
                    }

                    armorY += 20;
                }
            }
        }

        // --- Render Data Area ---
        int dataAreaX = leftPos + 95;
        int dataAreaY = topPos + 25;
        int dataAreaW = 143;

        if (fancyPanels) {
            guiGraphics.fill(dataAreaX, dataAreaY, dataAreaX + dataAreaW, topPos + guiHeight - 10, 0x30000000);
            guiGraphics.renderOutline(dataAreaX, dataAreaY, dataAreaW, (topPos + guiHeight - 10) - dataAreaY,
                    0x40FFFFFF);
        }

        int yOffsetForRows = 5;
        for (S2CProfilePacket.ProfileData data : packet.getData()) {
            int rowX = dataAreaX + 2;
            int rowY = dataAreaY + yOffsetForRows;
            int rowH = 14;

            if (fancyPanels) {
                // Subtle row background
                guiGraphics.fill(rowX, rowY, rowX + dataAreaW - 4, rowY + rowH, 0x20FFFFFF);
            }

            int iconOffset = 2;
            int rowTextColor = textColor;
            if (showIcons) {
                ResourceLocation icon = data.icon;
                String titleStr = data.title.getString().toLowerCase();
                if (icon == null) {
                    if (titleStr.contains("health") || titleStr.contains("hp")) {
                        icon = new ResourceLocation("textures/gui/icons.png");
                        guiGraphics.blit(icon, rowX + 2, rowY + 3, 52, 0, 9, 9, 256, 256);
                        iconOffset = 13;
                        rowTextColor = 0xFFFF2222; // Red
                    } else if (titleStr.contains("hunger") || titleStr.contains("food")) {
                        icon = new ResourceLocation("textures/gui/icons.png");
                        guiGraphics.blit(icon, rowX + 2, rowY + 3, 16, 27, 9, 9, 256, 256);
                        iconOffset = 13;
                    } else if (titleStr.contains("level") || titleStr.contains("exp")) {
                        icon = new ResourceLocation("textures/gui/icons.png");
                        // Correct XP orb icon: from icons.png, around 0, 64 is often where orbs are,
                        // but let's use the one in the bar area 185, 64 etc.
                        // Vanilla XP orb is often in textures/entity/experience_orb.png but for GUI we
                        // use icons.png
                        // Actually, vanilla icons.png has XP bar bits. Let's use 18x18 orb-like from
                        // generic sprites or 9x9 orb at 0, 0 (usually heart empty)
                        // Wait, XP orb in icons.png is actually at x=0, y=64 (roughly) ?? No, it's 185,
                        // 64.
                        guiGraphics.blit(icon, rowX + 2, rowY + 3, 185, 64, 9, 9, 256, 256);
                        iconOffset = 13;
                        rowTextColor = 0xFF55FF55; // Greenish
                    }
                } else {
                    guiGraphics.blit(icon, rowX + 2, rowY + 3, 0, 0, 9, 9, 9, 9);
                    iconOffset = 13;
                }
            }

            // Draw Key (Gold/Yellow for labels)
            String label = data.title.getString() + ": ";
            guiGraphics.drawString(this.font, label, rowX + iconOffset, rowY + 3, 0xFFFFAA00, !vanillaStyle);

            // Draw Value (White or conditional)
            guiGraphics.drawString(this.font, data.value.getString(), rowX + iconOffset + this.font.width(label),
                    rowY + 3, rowTextColor, !vanillaStyle);

            yOffsetForRows += rowH + 2;
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

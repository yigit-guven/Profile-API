package net.yigitguven.profile.api;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

/**
 * Represents a single element in the player profile UI.
 */
public interface ProfileComponent {
    /**
     * Unique identifier for this component type.
     */
    ResourceLocation getId();

    /**
     * The title displayed for this component.
     */
    Component getTitle();

    /**
     * The value or text content of the component.
     */
    Component getValue();

    /**
     * Optional icon to display. If null, a default will be used.
     */
    default ResourceLocation getIcon() {
        return null;
    }
}

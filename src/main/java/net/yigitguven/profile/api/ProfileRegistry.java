package net.yigitguven.profile.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import net.minecraft.world.entity.player.Player;

/**
 * Registry for profile components.
 */
public class ProfileRegistry {
    private static final List<Function<Player, ProfileComponent>> FACTORIES = new ArrayList<>();

    /**
     * Register a factory that creates a profile component for a given player.
     * @param factory The factory function.
     */
    public static void register(Function<Player, ProfileComponent> factory) {
        FACTORIES.add(factory);
    }

    /**
     * Gets all registered component factories.
     */
    public static List<Function<Player, ProfileComponent>> getFactories() {
        return Collections.unmodifiableList(FACTORIES);
    }

    /**
     * Computes all components for a specific player.
     */
    public static List<ProfileComponent> getComponents(Player player) {
        List<ProfileComponent> components = new ArrayList<>();
        for (Function<Player, ProfileComponent> factory : FACTORIES) {
            ProfileComponent component = factory.apply(player);
            if (component != null) {
                components.add(component);
            }
        }
        return components;
    }
}

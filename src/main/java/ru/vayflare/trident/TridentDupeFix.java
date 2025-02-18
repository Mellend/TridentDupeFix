package ru.vayflare.trident;

import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
import ru.vayflare.trident.events.InventoryClick;
import ru.vayflare.trident.events.PlayerLaunchProjectile;

import java.util.HashMap;
import java.util.Map;

/**
 * A plugin that fixes a duplication glitch related to Tridents in Minecraft.
 * This plugin listens for specific player actions and prevents the dupe exploit.
 */
public final class TridentDupeFix extends JavaPlugin {

    /**
     * A map that tracks players who have performed actions that could trigger the dupe glitch.
     */
    public static final Map<HumanEntity, Integer> cancel = new HashMap<>();

    /**
     * Called when the plugin is enabled.
     * Initializes the plugin by setting up event listeners.
     *
     * @apiNote Internal use only
     */
    @ApiStatus.Internal
    @Override
    public void onEnable() {
        // register events
        getServer().getPluginManager().registerEvents(new InventoryClick(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLaunchProjectile(this), this);
    }

    /**
     * Called when the plugin is disabled.
     * Clears the tracking map to free up resources.
     *
     * @apiNote Internal use only
     */
    @ApiStatus.Internal
    @Override
    public void onDisable() {
        cancel.clear(); // clear hashmap
    }
}
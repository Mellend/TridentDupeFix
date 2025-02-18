package ru.vayflare.trident.events;

import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.vayflare.trident.TridentDupeFix;

/**
 * Listens for when a player launches a Trident and prevents the dupe glitch if necessary.
 */
public class PlayerLaunchProjectile implements Listener {

    /**
     * Reference to the main plugin instance.
     */
    private final TridentDupeFix plugin;

    /**
     * Constructs a new PlayerLaunchProjectile listener.
     *
     * @param plugin The TridentDupeFix plugin instance.
     */
    public PlayerLaunchProjectile(TridentDupeFix plugin) {
        this.plugin = plugin;
    }

    /**
     * Handles the PlayerLaunchProjectileEvent. If the player is launching a Trident and the action
     * matches the tracked tick, the event is cancelled to prevent dupe.
     *
     * @param e The PlayerLaunchProjectileEvent.
     */
    @EventHandler()
    public void onPlayerLaunchProjectile(PlayerLaunchProjectileEvent e) {
        if (e.getItemStack().getType() != Material.TRIDENT) {
            return;
        }
        e.getPlayer().closeInventory();
        int tick = TridentDupeFix.cancel.getOrDefault(e.getPlayer(), -1);
        if (plugin.getServer().getCurrentTick() != tick) {
            return;
        }
        e.setCancelled(true);
    }
}
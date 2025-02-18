package ru.vayflare.trident.events;

import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.vayflare.trident.TridentDupeFix;

public class PlayerLaunchProjectile implements Listener {
    private final TridentDupeFix plugin;

    public PlayerLaunchProjectile(TridentDupeFix plugin) {
        this.plugin = plugin;
    }

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
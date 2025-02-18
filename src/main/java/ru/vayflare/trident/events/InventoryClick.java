package ru.vayflare.trident.events;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.vayflare.trident.TridentDupeFix;

public class InventoryClick implements Listener {
    private final TridentDupeFix plugin;

    public InventoryClick(TridentDupeFix plugin) {
        this.plugin = plugin;
    }

    @EventHandler()
    public void onPlayerStopUsingItem(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() != Material.TRIDENT) {
            return;
        }
        if (event.getAction() == InventoryAction.HOTBAR_SWAP) {
            TridentDupeFix.cancel.put(event.getWhoClicked(), plugin.getServer().getCurrentTick());
            event.setResult(Event.Result.DENY);
        }
    }
}
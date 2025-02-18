package ru.vayflare.trident.events;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.vayflare.trident.TridentDupeFix;

/**
 * Listens for inventory click events and tracks players who perform actions that could trigger the dupe glitch.
 */
public class InventoryClick implements Listener {

    /**
     * Reference to the main plugin instance.
     */
    private final TridentDupeFix plugin;

    /**
     * Constructs a new InventoryClick listener.
     *
     * @param plugin The TridentDupeFix plugin instance.
     */
    public InventoryClick(TridentDupeFix plugin) {
        this.plugin = plugin;
    }

    /**
     * Handles the InventoryClickEvent. If the player interacts with a Trident using a hotbar swap action,
     * the player is tracked to prevent the dupe glitch.
     *
     * @param e The InventoryClickEvent.
     */
    @EventHandler()
    public void onPlayerStopUsingItem(InventoryClickEvent e) {
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() != Material.TRIDENT) {
            return;
        }
        if (e.getAction() != InventoryAction.HOTBAR_SWAP) {
            return;
        }
        TridentDupeFix.cancel.put(e.getWhoClicked(), plugin.getServer().getCurrentTick());
        e.setResult(Event.Result.DENY);
    }
}
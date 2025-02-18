package ru.vayflare.trident;

import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;
import ru.vayflare.trident.events.InventoryClick;
import ru.vayflare.trident.events.PlayerLaunchProjectile;

import java.util.HashMap;
import java.util.Map;

public final class TridentDupeFix extends JavaPlugin {
    public static final Map<HumanEntity, Integer> cancel = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new InventoryClick(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLaunchProjectile(this), this);
    }

    @Override
    public void onDisable() {
        cancel.clear();
    }
}
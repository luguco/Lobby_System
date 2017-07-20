package me.luguco.lobbysystem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by luguco on 14.07.2017.
 */
public class DenyTasks implements Listener {
    private Main plugin;
    public DenyTasks(Main main) {
        this.plugin = main;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void clickInv(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(plugin.getConfig().getBoolean("DenyTasks.MoveItems")){
            if(!p.hasPermission("lobby.moveitems")){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void dropItem(PlayerDropItemEvent e){
        Player p =  e.getPlayer();
        if(plugin.getConfig().getBoolean("DenyTasks.DropItems")){
            if(!p.hasPermission("lobby.dropitems")){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void pickupItem(PlayerPickupItemEvent e){
        Player p =  e.getPlayer();
        if(plugin.getConfig().getBoolean("DenyTasks.TakeItems")){
            if(!p.hasPermission("lobby.takeitems")){
                e.setCancelled(true);
            }
        }
    }
}

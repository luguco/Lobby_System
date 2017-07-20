package me.luguco.lobbysystem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Created by luguco on 14.07.2017.
 */
public class Give_Items implements Listener {
    private Main plugin;
    public Give_Items(Main main) {
        this.plugin = main;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void join(PlayerJoinEvent e){
        Player p = e.getPlayer();

        ItemStack warp = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Items.Warp.Material")), 1);
        ItemMeta warpmeta = warp.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        warpmeta.setDisplayName(plugin.getConfig().getString("Items.Warp.Name"));
        lore.add(plugin.getConfig().getString("Items.Warp.Lore"));
        warpmeta.setLore(lore);
        warp.setItemMeta(warpmeta);

        p.getInventory().setItem(plugin.getConfig().getInt("Items.Warp.Slot"), warp);


        if(plugin.hideall.contains(p.getName())){
            ItemStack hideall = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Items.Hider_non.Material")), 1);
            ItemMeta allmeta = hideall.getItemMeta();
            ArrayList<String> lore2 = new ArrayList<>();
            allmeta.setDisplayName(plugin.getConfig().getString("Items.Hider_non.Name"));
            lore2.add(plugin.getConfig().getString("Items.Hider_non.Lore"));
            allmeta.setLore(lore2);
            hideall.setItemMeta(allmeta);

            p.getInventory().setItem(plugin.getConfig().getInt("Items.Hider_non.Slot"), hideall);

        }else
            if(plugin.seevip.contains(p.getName())){
                ItemStack vip = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Items.Hider_vip.Material")), 1);
                ItemMeta vipmeta = vip.getItemMeta();
                ArrayList<String> lore3 = new ArrayList<>();
                vipmeta.setDisplayName(plugin.getConfig().getString("Items.Hider_vip.Name"));
                lore3.add(plugin.getConfig().getString("Items.Hider_vip.Lore"));
                vipmeta.setLore(lore3);
                vip.setItemMeta(vipmeta);

                p.getInventory().setItem(plugin.getConfig().getInt("Items.Hider_vip.Slot"), vip);

            }else{
                ItemStack all = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Items.Hider_all.Material")), 1);
                ItemMeta allmeta = all.getItemMeta();
                ArrayList<String> lore4 = new ArrayList<>();
                allmeta.setDisplayName(plugin.getConfig().getString("Items.Hider_all.Name"));
                lore4.add(plugin.getConfig().getString("Items.Hider_all.Lore"));
                allmeta.setLore(lore4);
                all.setItemMeta(allmeta);

                p.getInventory().setItem(plugin.getConfig().getInt("Items.Hider_all.Slot"), all);
            }

            if(plugin.shieldon.contains(p.getName())){
                ItemStack shieldon = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Items.Shield_on.Material")), 1);
                ItemMeta shieldonmeta = shieldon.getItemMeta();
                ArrayList<String> lore5 = new ArrayList<>();
                shieldonmeta.setDisplayName(plugin.getConfig().getString("Items.Shield_on.Name"));
                lore5.add(plugin.getConfig().getString("Items.Shield_on.Lore"));
                shieldonmeta.setLore(lore5);
                shieldon.setItemMeta(shieldonmeta);

                p.getInventory().setItem(plugin.getConfig().getInt("Items.Shield_on.Slot"), shieldon);

            }else{
                if(p.hasPermission("lobby.shield")){
                    ItemStack shield = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Items.Shield_off.Material")), 1);
                    ItemMeta shieldmeta = shield.getItemMeta();
                    ArrayList<String> lore6 = new ArrayList<>();
                    shieldmeta.setDisplayName(plugin.getConfig().getString("Items.Shield_off.Name"));
                    lore6.add(plugin.getConfig().getString("Items.Shield_off.Lore"));
                    shieldmeta.setLore(lore6);
                    shield.setItemMeta(shieldmeta);

                    p.getInventory().setItem(plugin.getConfig().getInt("Items.Shield_off.Slot"), shield);
                }
            }

            if(p.hasPermission("lobby.silentlobby")){

                ItemStack silent = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Items.Silent.Material")), 1);
                ItemMeta silentmeta = silent.getItemMeta();
                ArrayList<String> lore7 = new ArrayList<>();
                silentmeta.setDisplayName(plugin.getConfig().getString("Items.Silent.Name"));
                lore7.add(plugin.getConfig().getString("Items.Silent.Lore"));
                silentmeta.setLore(lore7);
                silent.setItemMeta(silentmeta);

                p.getInventory().setItem(plugin.getConfig().getInt("Items.Silent.Slot"), silent);
            }
        p.updateInventory();

    }
}

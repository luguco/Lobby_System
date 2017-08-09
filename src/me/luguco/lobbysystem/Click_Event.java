package me.luguco.lobbysystem;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luguco on 13.07.2017.
 */
public class Click_Event implements Listener {

    private int i;
    private Main plugin;
    public Click_Event(Main main) {
        this.plugin = main;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Material m = e.getMaterial();
            if(m == Material.getMaterial(plugin.getConfig().getString("Items.Hider_all.Material"))){
                e.setCancelled(true);

                plugin.seevip.add(p.getName());
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(!player.hasPermission("lobby.vip")){
                        p.hidePlayer(player);
                    }
                }
                p.sendMessage("§aDu siehst nun nur noch VIP");
                ItemStack vip = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Items.Hider_vip.Material")), 1);
                ItemMeta vipmeta = vip.getItemMeta();
                ArrayList<String> lore3 = new ArrayList<>();
                vipmeta.setDisplayName(plugin.getConfig().getString("Items.Hider_vip.Name"));
                lore3.add(plugin.getConfig().getString("Items.Hider_vip.Lore"));
                vipmeta.setLore(lore3);
                vip.setItemMeta(vipmeta);

                p.getInventory().setItem(plugin.getConfig().getInt("Items.Hider_vip.Slot"), vip);
                p.updateInventory();
                return;
            }


            if(m == Material.getMaterial(plugin.getConfig().getString("Items.Hider_vip.Material"))){
                e.setCancelled(true);

                plugin.hideall.add(p.getName());
                for(Player player : Bukkit.getOnlinePlayers()){
                    p.hidePlayer(player);
                }
                p.sendMessage("§aDu siehst nun niemanden mehr");
                ItemStack hideall = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Items.Hider_non.Material")), 1);
                ItemMeta allmeta = hideall.getItemMeta();
                ArrayList<String> lore2 = new ArrayList<>();
                allmeta.setDisplayName(plugin.getConfig().getString("Items.Hider_non.Name"));
                lore2.add(plugin.getConfig().getString("Items.Hider_non.Lore"));
                allmeta.setLore(lore2);
                hideall.setItemMeta(allmeta);

                p.getInventory().setItem(plugin.getConfig().getInt("Items.Hider_non.Slot"), hideall);
                p.updateInventory();
                return;
            }


            if(m == Material.getMaterial(plugin.getConfig().getString("Items.Hider_non.Material"))){
                e.setCancelled(true);

                plugin.seevip.remove(p.getName());
                plugin.hideall.remove(p.getName());

                for(Player players : Bukkit.getOnlinePlayers()){
                    p.showPlayer(players);
                }
                p.sendMessage("§aAlle Spieler sind nun wieder sichtbar");
                ItemStack all = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Items.Hider_all.Material")), 1);
                ItemMeta allmeta = all.getItemMeta();
                ArrayList<String> lore4 = new ArrayList<>();
                allmeta.setDisplayName(plugin.getConfig().getString("Items.Hider_all.Name"));
                lore4.add(plugin.getConfig().getString("Items.Hider_all.Lore"));
                allmeta.setLore(lore4);
                all.setItemMeta(allmeta);

                p.getInventory().setItem(plugin.getConfig().getInt("Items.Hider_all.Slot"), all);
                p.updateInventory();
                return;
            }


            if(m == Material.getMaterial(plugin.getConfig().getString("Items.Warp.Material"))){
                e.setCancelled(true);

                Inventory warps = Bukkit.createInventory(p, 45, plugin.getConfig().getString("Warps.Invname"));

                String c = plugin.getConfig().getString("Warps.Fillerblock.Color");
                ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.valueOf(c).getDyeData());
                ItemMeta im = is.getItemMeta();
                im.setDisplayName("Placeholder");
                List<String> lore = new ArrayList<>();
                lore.add("");
                im.setLore(lore);
                is.setItemMeta(im);



                for(int i = 1; i < 45; i++){

                    if(plugin.getConfig().contains("Warps." + i)){
                        String name = plugin.getConfig().getString("Warps." + i + ".Name");
                        String material = plugin.getConfig().getString("Warps." + i + ".Material");
                        String ilore = plugin.getConfig().getString("Warps." + i + ".Lore");
                        int slot = plugin.getConfig().getInt("Warps." + i + ".Slot");

                        ItemStack ist = new ItemStack(Material.getMaterial(material), 1);
                        ItemMeta ima = ist.getItemMeta();
                        ima.setDisplayName(name);
                        List<String> loreend = new ArrayList<>();
                        loreend.add(ilore);
                        ima.setLore(loreend);
                        ist.setItemMeta(ima);

                        warps.setItem(slot, ist);
                    }

                }


                ItemStack[] items = warps.getContents();
                for(int i = 0; i < 45; i++){

                    if((items[i] == null || items[i].getType() == Material.AIR)) {
                        warps.setItem(i, is);
                    }
                }
                p.openInventory(warps);
                return;
            }


            if(m == Material.getMaterial(plugin.getConfig().getString("Items.Shield_on.Material"))){
                e.setCancelled(true);
                return;
            }


            if(m == Material.getMaterial(plugin.getConfig().getString("Items.Shield_off.Material"))) {
                e.setCancelled(true);
                return;
            }


            if(m == Material.getMaterial(plugin.getConfig().getString("Items.Silent.Material"))) {
                e.setCancelled(true);

            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        for(Player players : Bukkit.getOnlinePlayers()){
            if(plugin.hideall.contains(players.getName())){
                players.hidePlayer(e.getPlayer());
            }else
                if(plugin.seevip.contains(players.getName()) &&  !e.getPlayer().hasPermission("lobby.vip")){
                    players.hidePlayer(e.getPlayer());

            }
        }
    }

    @EventHandler
    public void clickWarp(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        int slot = e.getSlot();

        for(i = 1; i < 45; i++){
            if(plugin.getConfig().contains("Warps." + i)){
                int compare = plugin.getConfig().getInt("Warps." + i + ".Slot");

                if(slot == compare){

                    double x = plugin.getConfig().getDouble("Warps." + i + ".X");
                    double y = plugin.getConfig().getDouble("Warps." + i + ".Y");
                    double z = plugin.getConfig().getDouble("Warps." + i + ".Z");
                    float yaw = (float) plugin.getConfig().getDouble("Warps." + i + ".Yaw");
                    float pitch = (float) plugin.getConfig().getDouble("Warps." + i + ".Pitch");
                    Location loc = new Location(p.getWorld(), x, y, z, yaw, pitch);

                    p.teleport(loc);
                    i = 99;
                }
            }
        }
    }
}

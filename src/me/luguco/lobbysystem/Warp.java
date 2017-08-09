package me.luguco.lobbysystem;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by luguco on 21.07.2017.
 */
public class Warp implements CommandExecutor{

    private int i;
    private int i2;
    private Main plugin;
    public Warp(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst ein Spieler sein, um den command ausführen zu können!");
            return true;
        }

        Player p = (Player) sender;
        if(args.length != 2){
            p.sendMessage("§cNutze: /warp <set|delete> <name>");
            return true;
        }

        if(args[0].equalsIgnoreCase("set")){

            if(!p.hasPermission("lobby.setwarp")){
                p.sendMessage("§cDu hast keine Rechte, um diesen command zu benutzen!");
                return true;
            }
            Material m = p.getItemInHand().getType();
            if(m.equals(Material.AIR)){
                p.sendMessage("§cDas Item darf nicht Air sein!");
                return true;
            }
            String material = m.toString();
            material.replace("!!org.bukkit.Material ", "");

            double x = p.getLocation().getX();
            double y = p.getLocation().getY();
            double z = p.getLocation().getZ();
            double yaw = p.getLocation().getYaw();
            double pitch = p.getLocation().getPitch();

            String name = args[1];

            for(i = 1; i < 45; i++ ){
                if(!plugin.getConfig().contains("Warps." + i)){

                    plugin.getConfig().set("Warps." + i + ".Material", material);
                    plugin.getConfig().set("Warps." + i + ".Name", name);
                    plugin.getConfig().set("Warps." + i + ".Lore", "");
                    plugin.getConfig().set("Warps." + i + ".Slot", i);
                    plugin.getConfig().set("Warps." + i + ".X", x);
                    plugin.getConfig().set("Warps." + i + ".Y", y);
                    plugin.getConfig().set("Warps." + i + ".Z", z);
                    plugin.getConfig().set("Warps." + i + ".Yaw", yaw);
                    plugin.getConfig().set("Warps." + i + ".Pitch", pitch);
                    plugin.saveConfig();
                    i = 99 ;
                }
            }
            if(!(i >= 55)){
                p.sendMessage("§cAlle 54 warps wurden bereits belegt!");
                return true;
            }
            p.sendMessage("§aEin warp wurde an deiner Stelle mit dem Namen " + name + " und dem Item " + material + " erstellt.");
            p.sendMessage("§aGehe in die Config um weitere Einstellungen zu teffen.");
        }else
            if(args[0].equalsIgnoreCase("delete")){

                if(!p.hasPermission("lobby.deletewarp")){
                    p.sendMessage("§cDu hast keine Rechte, um diesen command zu benutzen!");
                    return true;
                }

                String todelete = args[1];

                for( i2 = 1; i2 < 45; i2++){
                    p.sendMessage("check");
                    if(plugin.getConfig().contains("Warps." + i2)){
                        p.sendMessage("got" + i2);
                        String name = plugin.getConfig().getString("Warps." + i2 + ".Name");
                        if(todelete.equalsIgnoreCase(name)){
                            p.sendMessage("delete" + i2);
                            plugin.getConfig().set("Warps." + i2, null);
                            plugin.saveConfig();
                            i2 = 99;
                        }
                    }
                }
                if(!(i2 >= 55)){
                    p.sendMessage("§cEs existiert kein Warp mit dem Namen " + todelete);
                    return true;
                }

                p.sendMessage("§aDer Warp " + todelete + " wurde erfolgreich gelöscht!");
            }
        return true;
    }
}

package me.luguco.lobbysystem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by Andreas on 13.07.2017.
 */
public class Main extends JavaPlugin{

    public boolean licence = true;

    public ArrayList<String> seevip = new ArrayList<>();
    public ArrayList<String> hideall = new ArrayList<>();

    public ArrayList<String> shieldon = new ArrayList<>();
    @Override
    public void onEnable(){
        Bukkit.getConsoleSender().sendMessage("§a[Lobby_System] enabled!");
        new Click_Event(this);
        new Give_Items(this);
        new DenyTasks(this);
        defaultCfg();
    }

    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage("§c[Lobby_System] disabled!");
    }

    public void defaultCfg(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}

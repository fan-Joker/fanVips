package me.fanjoker.vips;

import me.fanjoker.vips.config.ConfigManager;
import me.fanjoker.vips.listeners.JoinEvent;
import me.fanjoker.vips.manager.PlayerManager;
import me.fanjoker.vips.manager.VipConexao;
import me.fanjoker.vips.cmds.*;
import me.fanjoker.vips.manager.VipSettings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public static ConfigManager configManager;
    private static PlayerManager playerManager;
    private static VipSettings vipSettings;
    private static VipConexao vipConexao;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager();
        playerManager = new PlayerManager();
        vipConexao = new VipConexao();
        vipSettings = new VipSettings();
        configManager.loadConfig("config");
        register();
        vipConexao.openConnectionMySQL();
    }
    public void onDisable() {
        vipConexao.close();
    }
    private void register() {
        getCommand("darvip").setExecutor(new DarVip());
        getCommand("tempovip").setExecutor(new TempoVip());
        getCommand("rvip").setExecutor(new RemoveVip());
        getCommand("trocarvip").setExecutor(new TrocarVip());
        getCommand("fanVips").setExecutor(new fanVipsCommand());

        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
    }
    public static PlayerManager getPlayerManager() { return playerManager; }
    public static VipConexao getConexao() { return vipConexao; }
    public static VipSettings getSettings() { return vipSettings;}
    public static Main getInstance() {
        return (Main) Bukkit.getServer().getPluginManager().getPlugin("fanVips");
    }
}

package fanjoker.vips;

import fanjoker.vips.cmds.*;
import fanjoker.vips.config.ConfigManager;
import fanjoker.vips.listeners.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public static ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager();
        configManager.loadConfig("config");
        configManager.loadConfig("vips");
        register();
    }
    private void register() {
        getCommand("darvip").setExecutor(new DarVip());
        getCommand("tempovip").setExecutor(new TempoVip());
        getCommand("rvip").setExecutor(new RemoveVip());
        getCommand("trocarvip").setExecutor(new TrocarVip());
        getCommand("fanVips").setExecutor(new fanVipsCommand());
        getCommand("criarkey").setExecutor(new CriarKey());
        getCommand("listarkeys").setExecutor(new ListarKeys());
        getCommand("usarkey").setExecutor(new UsarKey());
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
    }
    public static Main getInstance() {
        return (Main) Bukkit.getServer().getPluginManager().getPlugin("fanVips");
    }
}

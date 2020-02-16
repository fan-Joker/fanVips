package fanjoker.vips.listeners;

import fanjoker.vips.Main;
import fanjoker.vips.utils.Vips;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinEvent implements Listener {

    static YamlConfiguration config = Main.configManager.getConfig("vips").getYaml();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String name = e.getPlayer().getName();
        if(Vips.getVips(name) > 0) {
            for (String lista : config.getConfigurationSection("Vips." + name + ".tempo").getKeys(false)) {
                long entrag1 = config.getLong("Vips." + name + ".tempo." + lista);
                if(!(entrag1 > System.currentTimeMillis())) {
                    if(Vips.getVips(name) == 1) {
                        if(Vips.hasVipPerm(name, lista)) {
                            config.set("Vips." + name, null);
                            Main.configManager.getConfig("vips").save();
                        }
                    } else {
                        if(Vips.hasVipPerm(name, lista)) {
                            config.set("Vips." + name + ".tempo." + lista, null);
                            Main.configManager.getConfig("vips").save();
                        }
                    }
                    new BukkitRunnable(){
                        public void run() {
                            if(Vips.getVips(name) == 1) {
                                if(Vips.hasVipPerm(name, lista)) {
                                    p.sendMessage(new String[]{"",
                                            " §cO atual vip '" + lista + "' acabou.",
                                            " §cPara adquirir um outro, acesse: §eloja.redewell.com", ""});
                                }
                            } else {
                                if(Vips.hasVipPerm(name, lista)) {
                                    p.sendMessage(new String[]{"",
                                            " §cO atual vip '" + lista + "' acabou.",
                                            " §cPara adquirir um outro, acesse: §eloja.redewell.com", ""});
                                }
                            }

                        }
                    }.runTaskLater(Main.instance, 40L);
                }
            }
        }
    }
}

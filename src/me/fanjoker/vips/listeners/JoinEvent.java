package me.fanjoker.vips.listeners;

import me.fanjoker.vips.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.nio.Buffer;

public class JoinEvent implements Listener {



    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String name = e.getPlayer().getName();
        if(Main.getPlayerManager().hasVip(name)) {
            for (String vip : Main.getPlayerManager().getVips(name).keySet()) {
                if (Main.getPlayerManager().getVips(name).get(vip).equalsIgnoreCase("perm")) continue;
                long time = Long.valueOf(Main.getPlayerManager().getVips(name).get(vip));
                if(System.currentTimeMillis() > time) {
                    Main.getPlayerManager().removeVip(name, vip);
                    Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
                        p.sendMessage(new String[]{"",
                                " §cO atual vip '" + vip + "' acabou.",
                                " §cPara adquirir outro, acessa: §estore.skyepicnetwork.com", ""});
                    },40L);
                }
            }
        }
    }
}

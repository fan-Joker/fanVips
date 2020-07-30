package me.fanjoker.vips.cmds;

import me.fanjoker.vips.Main;
import me.fanjoker.vips.utils.TFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TempoVip implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(p.hasPermission("fanvips.admin")) {
            if (args.length == 0) {
                if (Main.getPlayerManager().hasVip(p.getName())) {
                    showVip(p, p.getName());
                } else {
                    p.sendMessage("§cUso: /tempovip <nome>");
                }
                return true;
            }
            if (args.length == 1) {
                String target = args[0];
                if (Main.getPlayerManager().hasVip(target)) {
                    showVip(p, target);
                } else {
                    p.sendMessage("§cO jogador '" + target + "' não tem nenhum vip.");
                }
            }
        } else {
            if (Main.getPlayerManager().hasVip(p.getName())) {
                showVip(p, p.getName());
            } else {
                p.sendMessage("§cVocê não tem nenhum vip.");
            }
        }


        return false;
    }
    private void showVip(Player p, String target) {
        HashMap<String, String> hashMap = Main.getPlayerManager().getVips(target);
        p.sendMessage(new String[] { "", " §e§lVips: §7Lista de vips de: " + target, ""});
        for (String vip : hashMap.keySet()) {
            String time = hashMap.get(vip);
            if (time.equalsIgnoreCase("perm")) {
                p.sendMessage("  §e- " + Main.getPlayerManager().cap(vip) + ": §7Permanente");

            } else {
                long time2 = Long.valueOf(hashMap.get(vip)) - System.currentTimeMillis();
                if (System.currentTimeMillis() > Long.valueOf(hashMap.get(vip))) {
                    p.sendMessage("  §e- " + Main.getPlayerManager().cap(vip) + ": §7Acabou");
                    p.sendMessage("");
                    return;
                }
                p.sendMessage("  §e- " + Main.getPlayerManager().cap(vip) + ": §7" + TFormat.format(time2));

            }
        }
        p.sendMessage("");
    }
//    public static void showVip(String target) {
//        StringBuilder sb = new StringBuilder();
//        for (String lista : config.getConfigurationSection("Vips." + target + ".tempo").getKeys(false)) {
//            String ab = config.getString("Vips." + target + ".tempo." + lista);
//            if(ab.equalsIgnoreCase("Perm")) {
//                sb.append("\n §e- " + lista.toUpperCase() + ": §fPermanente");
//                sb.append("§e\n");
//            } else {
//                long a = config.getLong("Vips." + target + ".tempo." + lista) - System.currentTimeMillis();
////                            if (!(a < 0)) {
//                sb.append("\n §e- " + lista.toUpperCase() + ": §f" + TFormat.format(a));
//                sb.append("§e\n");
////                            }
//            }
//        }
//        Bukkit.getPlayer(target).sendMessage(new String[]{"§6Tempo dos vips: ", "" + sb.toString(), ""});
}

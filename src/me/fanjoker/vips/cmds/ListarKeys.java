package me.fanjoker.vips.cmds;

import me.fanjoker.vips.Messages;
import me.fanjoker.vips.utils.TFormat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class ListarKeys implements CommandExecutor {



    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(p.hasPermission("fanVips.admin")) {
//            if (Vips.getKeys() > 0) {
//                StringBuilder sb = new StringBuilder();
//                for (String target : config.getConfigurationSection("Keys").getKeys(false)) {
//                    String a = config.getString("Keys." + target + ".vip");
//                    long b = config.getLong("Keys." + target + ".tempo");
//                    long days = TimeUnit.SECONDS.toMillis(b);
//                    sb.append("\n §e- " + target + " §7(" + a + " de " + TFormat.format(days) + ")");
//                    sb.append("§e\n");
//                }
//                p.sendMessage(new String[]{"§6Lista de keys: ", "" + sb.toString(), ""});

//            } else {
//                p.sendMessage("§cNenhuma key foi encontrada.");
//            }
        } else {
            p.sendMessage(Messages.noperm());
        }






        return false;
    }
}

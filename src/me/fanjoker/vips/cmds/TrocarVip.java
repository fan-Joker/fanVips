package me.fanjoker.vips.cmds;

import me.fanjoker.vips.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class TrocarVip implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(!Main.getPlayerManager().hasVip(p.getName())) {
            p.sendMessage("§cVocê não tem nenhum vip.");
            return true;

        } else if(Main.getPlayerManager().getVips(p.getName()).size() == 1) {
            for (String str : Main.getPlayerManager().getVips(p.getName()).keySet()) {
                Main.getPlayerManager().setVipUsing(p.getName(), str);
                p.sendMessage("§aVip trocado com êxito.");
            }

            return true;

        }
        if (args.length == 0) {
            p.sendMessage("§cUso: /trocarvip <grupo>");
            return true;

        }
        if(args.length == 1) {
            String vip = args[0];
            if (Main.getPlayerManager().getVipUsing(p.getName()).equalsIgnoreCase(vip)) {
                p.sendMessage("§cJá estás a usar esse vip.");
                return true;
            }
            if (Main.getPlayerManager().hasVip(p.getName(), vip)) {
                Main.getPlayerManager().setVipUsing(p.getName(), vip);
                p.sendMessage("§aVip '" + Main.getPlayerManager().cap(vip) + "' trocado com êxito.");
            } else {
                p.sendMessage("§cVocê não tem esse vip.");
            }


        }
        return false;
    }
}

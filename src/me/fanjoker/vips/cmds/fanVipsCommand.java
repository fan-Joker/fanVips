package me.fanjoker.vips.cmds;

import me.fanjoker.vips.Main;
import me.fanjoker.vips.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class fanVipsCommand implements CommandExecutor {



    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(p.hasPermission("fanVips.admin")) {
            if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                Main.configManager.getConfig("config").reload();
                p.sendMessage("§aArquivo de configurações recarregado com êxito.");
                return true;
            }
            p.sendMessage(new String[] {"",
                    " §e§lVips: §7Lista de comandos:",
                    "",
                    "  §e/tempovip: §7Para amostar o tempo de seus vips.",
                    "  §e/darvip: §7Para dar um vip a um jogador.",
                    "  §e/rvip: §7Para remover um vip de um jogador.",
                    "  §e/trocarvip: §7Para trocar um vip que o jogador tem.",""});
        } else {
            p.sendMessage(Messages.noperm());
        }





        return false;
    }
}

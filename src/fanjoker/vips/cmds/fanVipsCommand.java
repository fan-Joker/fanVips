package fanjoker.vips.cmds;

import fanjoker.vips.Main;
import fanjoker.vips.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import sun.security.acl.AllPermissionsImpl;

public class fanVipsCommand implements CommandExecutor {

    static YamlConfiguration config = Main.configManager.getConfig("vips").getYaml();

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(p.hasPermission("fanVips.admin")) {
            if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                Main.configManager.getConfig("config").reload();
                p.sendMessage("§aArquivo de configurações recarregado com êxito.");
                return true;
            }
            p.sendMessage(new String[] {"", "§6Comandos do fanVips:",
                            "§e/tempovip §f- Para amostar o tempo de seus vips.",
                            "§e/darvip §f- Para dar um vip a um jogador.",
                            "§e/rvip §f- Para remover um vip de um jogador.",
                            "§e/trocarvip §f- Para trocar um vip que o jogador tem.",
                            "§e/criarkey §f- Para criar uma key de um vip determinado.",
                            "§e/listarkeys §f- Para ver todas as keys criadas.",""});
        } else {
            p.sendMessage(Messages.noperm());
        }





        return false;
    }
}

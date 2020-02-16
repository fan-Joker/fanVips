package fanjoker.vips.cmds;

import fanjoker.vips.Main;
import fanjoker.vips.Messages;
import fanjoker.vips.utils.Vips;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class RemoveVip implements CommandExecutor {

    static YamlConfiguration config = Main.configManager.getConfig("vips").getYaml();

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(p.hasPermission("fanvips.admin")) {
            if (args.length == 0) {
                p.sendMessage("§cUso: /rvip <nome>");
                return true;
            }
            if (args.length == 1) {
                String target = args[0];
                if (Vips.getVips(target) > 0) {
//                if(Vips.getVips(p) == 1) {
//                    Vips.execCommands(target, grupo, "ativacao");
                    config.set("Vips." + target, null);
                    Main.configManager.getConfig("vips").save();
                    p.sendMessage("§aVip(s) do jogador '" + target + "' removido com êxito.");
//                    return;
//                }
//                homes.set("Vips." + p.getName() + ".tempo" + , null);
//                Main.configManager.getConfig("homes").save();
                } else {
                    p.sendMessage("§cO jogador '" + target + "' não nenhum vip.");
                }
            }
        } else {
            p.sendMessage(Messages.noperm());
        }
        return false;
    }
}

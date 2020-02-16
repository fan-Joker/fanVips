package fanjoker.vips.cmds;

import fanjoker.vips.Main;
import fanjoker.vips.utils.Vips;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class TrocarVip implements CommandExecutor {

    static YamlConfiguration config = Main.configManager.getConfig("vips").getYaml();

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(Vips.getVips(p.getName()) == 0) {
            p.sendMessage("§cVocê não tem nenhum vip.");
            return true;
        } else if(Vips.getVips(p.getName()) == 1) {
            p.sendMessage("§cVocê não tem 2 vips ou mais para fazer a troca.");
            return true;
        }
        if (args.length == 0) {
            p.sendMessage("§cUso: /trocarvip <grupo>");
            return true;
        }
        if(args.length == 1) {
            String vip = args[0];
            if(Vips.existsVip(vip)) {
                if(!Vips.usingVip(p.getName()).equalsIgnoreCase(vip)) {
                    String oldvip = config.getString("Vips." + p.getName() + ".usando");
                    Vips.execCommandsT(p.getName(), oldvip, vip);
                    p.sendMessage("§aVip '" + vip + "' trocado com êxito.");
                    config.set("Vips." + p.getName() + ".usando", vip);
                    Main.configManager.getConfig("vips").save();
                } else {
                    p.sendMessage("§cVocê já está usando esse vip.");
                }
            } else {
                p.sendMessage("§cGrupo não encontrado.");
            }
        }
        return false;
    }
}

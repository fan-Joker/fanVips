package fanjoker.vips.cmds;

import fanjoker.vips.Main;
import fanjoker.vips.utils.Vips;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class UsarKey implements CommandExecutor {

    static YamlConfiguration config = Main.configManager.getConfig("vips").getYaml();

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(args.length == 0) {
            p.sendMessage("§cUse: /usarkey <key>");
            return true;
        }
        if(args.length == 1) {
            String key = args[0];
            if (keyEquals(key)) {
                String vip = config.getString("Keys." + key + ".vip");
                int dias = config.getInt("Keys." + key + ".vip");
                if(config.getString("Vips." + p.getName() + ".tempo." + vip) != null) {
                    if (config.getString("Vips." + p.getName() + ".tempo." + vip).equals("Perm")) {
                        p.sendMessage("§cVocê já possui o máximo de dias do vip.");
                        return true;
                    }
                    Vips.darVip(p.getName(), dias, vip);
                }
                Vips.darVip(p.getName(), dias, vip);
            } else {
                p.sendMessage("§cKey não encontrada.");
            }
        }

        return false;
    }
    public static boolean keyEquals(String args) {
        for (String target : config.getConfigurationSection("Keys").getKeys(false)) {
            if (args.equals(target)) {
                return true;
            }
        }
        return false;
    }
}

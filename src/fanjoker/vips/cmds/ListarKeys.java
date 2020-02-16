package fanjoker.vips.cmds;

import fanjoker.vips.Main;
import fanjoker.vips.Messages;
import fanjoker.vips.utils.TFormat;
import fanjoker.vips.utils.Vips;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class ListarKeys implements CommandExecutor {

    static YamlConfiguration config = Main.configManager.getConfig("vips").getYaml();

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(p.hasPermission("fanVips.admin")) {
            if (Vips.getKeys() > 0) {
                StringBuilder sb = new StringBuilder();
                for (String target : config.getConfigurationSection("Keys").getKeys(false)) {
                    String a = config.getString("Keys." + target + ".vip");
                    String b = config.getString("Keys." + target + ".tempo");
                    BigDecimal bb = new BigDecimal(b).multiply(new BigDecimal("1000"));
                    sb.append("\n §e- " + target + " §7(" + a + " de " + TFormat.format(bb.longValue()) + ")");
                    sb.append("§e\n");
                }
                p.sendMessage(new String[]{"§6Lista de keys: ", "" + sb.toString(), ""});
            } else {
                p.sendMessage("§cNenhuma key foi encontrada.");
            }
        } else {
            p.sendMessage(Messages.noperm());
        }






        return false;
    }
}

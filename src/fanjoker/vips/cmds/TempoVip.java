package fanjoker.vips.cmds;

import fanjoker.vips.Main;
import fanjoker.vips.Messages;
import fanjoker.vips.utils.TFormat;
import fanjoker.vips.utils.Vips;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class TempoVip implements CommandExecutor {

    static YamlConfiguration config = Main.configManager.getConfig("vips").getYaml();

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(p.hasPermission("fanvips.admin")) {
            if (args.length == 0) {
                String target = p.getName();
                if (Vips.getVips(target) > 0) {
                    StringBuilder sb = new StringBuilder();
                    StringBuilder sbb = new StringBuilder();
                    for (String lista : config.getConfigurationSection("Vips." + target + ".tempo").getKeys(false)) {
                        String ab = config.getString("Vips." + target + ".tempo." + lista);
                        if(ab.equalsIgnoreCase("Perm")) {
                            sb.append("\n §e- " + lista.toUpperCase() + ": §fPermanente");
                            sb.append("§e\n");
                        } else {
                            long a = config.getLong("Vips." + target + ".tempo." + lista) - System.currentTimeMillis();
//                            if (!(a < 0)) {
                                sb.append("\n §e- " + lista.toUpperCase() + ": §f" + TFormat.format(a));
                                sb.append("§e\n");
//                            }
                        }
                    }
                    p.sendMessage(new String[]{"§6Tempo dos vips: ", "" + sb.toString(), ""});
                } else {
                    p.sendMessage("§cUso: /tempovip <nome>");
                }
                return true;
            }
            if (args.length == 1) {
                String target = args[0];
                if (Vips.getVips(target) > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (String lista : config.getConfigurationSection("Vips." + target + ".tempo").getKeys(false)) {
                        sb.append("\n §e- " + lista.toUpperCase() + ": §f" + TFormat.format(Vips.getVipTime(target, lista)));
                        sb.append("§e\n");
                    }
                    p.sendMessage(new String[]{"§6Tempo dos vips: ", "" + sb.toString(), ""});
                } else {
                    p.sendMessage("§cO jogador '" + target + "' não nenhum vip.");
                }
            }
        } else {
            if (Vips.getVips(p.getName()) > 0) {
                StringBuilder sb = new StringBuilder();
                for (String lista : config.getConfigurationSection("Vips." + p.getName() + ".tempo").getKeys(false)) {
                    sb.append("\n §e- " + lista.toUpperCase() + ": §f" + TFormat.format(Vips.getVipTime(p.getName(), lista)));
                    sb.append("§e\n");
                }
                p.sendMessage(new String[]{"§6Tempo dos vips: ", "" + sb.toString(), ""});
            } else {
                p.sendMessage("§cVocê não tem nenhum vip.");
            }
        }


        return false;
    }
}

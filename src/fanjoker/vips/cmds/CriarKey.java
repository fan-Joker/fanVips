package fanjoker.vips.cmds;

import fanjoker.vips.Main;
import fanjoker.vips.Messages;
import fanjoker.vips.utils.RandomString;
import fanjoker.vips.utils.TFormat;
import fanjoker.vips.utils.Vips;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fanjoker.vips.utils.RandomString.getString;

import javax.jws.WebParam;
import java.math.BigDecimal;
import java.util.Random;

public class CriarKey implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if (p.hasPermission("fanvips.admin")) {
            if(args.length == 0) {
                p.sendMessage("§cUse: /criarkey <grupo> <tempo>");
                return true;
            }
            if(args.length == 1) {
                String vip = args[0];
                if(!Vips.existsVip(vip)) {
                    p.sendMessage("§cGrupo não foi encontrado.");
                    return true;
                }
            }
            if(args.length == 2) {
                String target = args[0];
                String vip = args[0];
                if(Vips.existsVip(vip)) {
                    if (args[1].endsWith("m")) {
                        try {
                            int dias = Integer.valueOf(args[1].replace("m", ""));
                            criar(p, vip, dias * 60);
                            return true;
                        } catch (NumberFormatException e) {
                            p.sendMessage("§cValor incorreto, por favor insira um valor válido.");
                            return true;
                        }
                    }
                    if (args[1].endsWith("h")) {
                        try {
                            int dias = Integer.valueOf(args[1].replace("h", ""));
                            criar(p, vip, dias * 60 * 60);
                            return true;
                        } catch (NumberFormatException e) {
                            p.sendMessage("§cValor incorreto, por favor insira um valor válido.");
                            return true;
                        }
                    }
                    if (args[1].endsWith("d")) {
                        try {
                            int dias = Integer.valueOf(args[1].replace("d", ""));
                            criar(p, vip, dias * 60 * 60 * 24);
                            return true;
                        } catch (NumberFormatException e) {
                            p.sendMessage("§cValor incorreto, por favor insira um valor válido.");
                            return true;
                        }
                    }
                    p.sendMessage("§c1m (minutos), 1h (horas) e 1d (dias)");
                } else {
                    p.sendMessage("§cGrupo não foi encontrado.");
                }
            } else {
                p.sendMessage("§cUse: /criarkey <grupo> <tempo>");
            }
        } else {
            p.sendMessage(Messages.noperm());
        }
        return false;
    }
    public static void criar(Player p, String vip, int dias){
        String key = getString(Main.configManager.getConfig("config").getYaml().getInt("Key_tamanho"));
        BigDecimal onee = new BigDecimal(dias).multiply(new BigDecimal("1000"));
        BigDecimal one = new BigDecimal(dias).divide(new BigDecimal("1000"));
        Vips.createKey(key, vip.toLowerCase(), one.intValue());
        p.sendMessage(new String[] { "",
                "§6 Informações da key:",
                "§6  Grupo: §e" + vip.toUpperCase(),
                "§6  Tempo: §e" + TFormat.format(onee.longValue()), "",
                "§6  Key: §u" + key, ""});
    }
}

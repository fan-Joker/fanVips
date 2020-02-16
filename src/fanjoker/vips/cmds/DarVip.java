package fanjoker.vips.cmds;

import fanjoker.vips.Main;
import fanjoker.vips.Messages;
import fanjoker.vips.utils.Vips;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DarVip implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(p.hasPermission("fanvips.admin")) {
            if(args.length == 2) {
                String vip = args[1];
                if(!Vips.existsVip(vip)) {
                    p.sendMessage("§cVip não encontrado");
                    return true;
                }
            }
            if (args.length == 3) {
                String target = args[0];
                String vip = args[1];
                if(Vips.existsVip(vip)) {
                    if(args[2].equalsIgnoreCase("perm")) {
                        if(Vips.hasVipPerm(target, vip)) {
                            Vips.darVipPerm(target, vip.trim());
                            return true;
                        } else {
                            p.sendMessage("§cJogador '" + args[0] + "' já tem vip permanente.");
                            return true;
                        }
                    }
                    if (args[2].endsWith("m")) {
                        try {
                            if(Vips.hasVipPerm(target, vip)) {
                                int dias = Integer.valueOf(args[2].replace("m", ""));
                                Vips.darVip(target, dias * 60, vip.trim());
                                return true;
                            } else {
                                p.sendMessage("§cO jogador '" + args[0] + "' já tem o máximo de dias de vip.");
                            }
                        } catch (NumberFormatException e) {
                            p.sendMessage("§cValor incorreto, por favor insira um valor válido.");
                            return true;
                        }
                    }
                    if (args[2].endsWith("h")) {
                        try {
                            if(Vips.hasVipPerm(target, vip)) {
                                int dias = Integer.valueOf(args[2].replace("h", ""));
                                Vips.darVip(target, dias * 60 * 60, vip.trim());
                                return true;
                            } else {
                                p.sendMessage("§cO jogador '" + args[0] + "' já tem o máximo de dias de vip.");
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            p.sendMessage("§cValor incorreto, por favor insira um valor válido.");
                            return true;
                        }
                    }
                    if (args[2].endsWith("d")) {
                        try {
                            if(Vips.hasVipPerm(target, vip)) {
                                int dias = Integer.valueOf(args[2].replace("d", ""));
                                Vips.darVip(target, dias * 60 * 60 * 24, vip.trim());
                                return true;
                            } else {
                                p.sendMessage("§cO jogador '" + args[0] + "' já tem o máximo de dias de vip.");
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            p.sendMessage("§cValor incorreto, por favor insira um valor válido.");
                            return true;
                        }
                    }
                    p.sendMessage("§c1m (minutos), 1h (horas), 1d (dias) ou perm (permanente).");

                } else {
                    p.sendMessage("§cGrupo não encontrado.");
//                    p.sendMessage("§cValor incorreto, por favor insira um valor válido.");
                }
            } else {
                p.sendMessage("§cUse: /darvip <nome> <vip> <tempo>");
            }
        } else {
            p.sendMessage(Messages.noperm());
        }
        return false;
    }
//                        Main.configManager.getConfig("vips").getYaml().set("Vips." + p.getName() + "." + vip.trim() + ".inicio", fmt.format(now.getTime()));
//                    Main.configManager.getConfig("vips").save();
}

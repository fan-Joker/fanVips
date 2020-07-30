package me.fanjoker.vips.cmds;

import me.fanjoker.vips.Main;
import me.fanjoker.vips.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class DarVip implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        if(s.hasPermission("fanvips.admin")) {
            if(args.length == 2) {
                String vip = args[1];
                if(!Main.getSettings().existsVip(vip)) {
                    s.sendMessage("§cGrupo não encontrado.");
                    return true;
                }
            }
            if (args.length == 3) {
                String target = args[0];
                String vip = args[1];
                if(Main.getSettings().existsVip(vip)) {
                    if(args[2].equalsIgnoreCase("perm")) {
                        if (!Main.getPlayerManager().hasVipPerm(target, vip)) {
                            Main.getPlayerManager().addVip(target, vip, 0, true);
                            return true;
                        } else {
                            s.sendMessage("§cO jogador '" + args[0] + "' já tem vip permanente.");
                            return true;
                        }
                    } else if (args[2].endsWith("m")) {
                        try {
                            if(!Main.getPlayerManager().hasVipPerm(target, vip)) {
                                long num = Long.valueOf(args[2].replace("m", ""));
                                long lon = System.currentTimeMillis() + (1000 * 60 * num);
                                Main.getPlayerManager().addVip(target, vip, lon, false);
                                return true;
                            } else {
                                s.sendMessage("§cO jogador '" + args[0] + "' já tem o máximo de dias de vip.");
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            s.sendMessage("§cValor incorreto, por favor insira um valor válido.");
                            return true;
                        }
                    } else if (args[2].endsWith("h")) {
                        try {
                            if(!Main.getPlayerManager().hasVipPerm(target, vip)) {
                                long num = Long.valueOf(args[2].replace("h", ""));
                                long lon = System.currentTimeMillis() + (1000 * 60 * 60 * num);
                                Main.getPlayerManager().addVip(target, vip, lon, false);
                                return true;
                            } else {
                                s.sendMessage("§cO jogador '" + args[0] + "' já tem o máximo de dias de vip.");
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            s.sendMessage("§cValor incorreto, por favor insira um valor válido.");
                            return true;
                        }
                    } else if (args[2].endsWith("d")) {
                        try {
                            if(!Main.getPlayerManager().hasVipPerm(target, vip)) {
                                long num = Long.valueOf(args[2].replace("d", ""));
                                long num1 = 60 * 24 * 60 * num;
                                BigDecimal no = new BigDecimal(num1).multiply(BigDecimal.valueOf(1000));
                                Main.getPlayerManager().addVip(target, vip, (no.longValue() + System.currentTimeMillis()), false);
                                return true;
                            } else {
                                s.sendMessage("§cO jogador '" + args[0] + "' já tem o máximo de dias de vip.");
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            s.sendMessage("§cValor incorreto, por favor insira um valor válido.");
                            e.printStackTrace();
                            return true;
                        }
                    }
                    s.sendMessage("§c1m (minutos), 1h (horas), 1d (dias) ou perm (permanente).");

                } else {
                    s.sendMessage("§cGrupo não encontrado.");
//                    p.sendMessage("§cValor incorreto, por favor insira um valor válido.");
                }
            } else {
                s.sendMessage("§cUse: /darvip <nome> <vip> <tempo>");
            }
        } else {
            s.sendMessage(Messages.noperm());
        }
        return false;
    }
//                        Main.configManager.getConfig("vips").getYaml().set("Vips." + p.getName() + "." + vip.trim() + ".inicio", fmt.format(now.getTime()));
//                    Main.configManager.getConfig("vips").save();
}

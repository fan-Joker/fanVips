package me.fanjoker.vips.cmds;

import org.bukkit.command.CommandExecutor;

public class UsarKey {



//    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
//        Player p = (Player) s;
//        if(args.length == 0) {
//            p.sendMessage("§cUse: /usarkey <key>");
//            return true;
//        }
//        if(args.length == 1) {
//            String key = args[0];
//            if (keyEquals(key)) {
//                String vip = config.getString("Keys." + key + ".vip");
//                int dias = config.getInt("Keys." + key + ".tempo");
//                if(config.getString("Vips." + p.getName() + ".tempo." + vip) != null) {
//                    if (config.getString("Vips." + p.getName() + ".tempo." + vip).equals("Perm")) {
//                        p.sendMessage("§cVocê já possui o máximo de dias do vip.");
//                        return true;
//                    }
//                    Vips.darVip(p.getName(), dias/2, vip);
//                }
//                Vips.darVip(p.getName(), dias/2, vip);
//            } else {
//                p.sendMessage("§cKey não encontrada.");
//            }
//        }
//
//        return false;
//    }
//    public static boolean keyEquals(String args) {
//        for (String target : config.getConfigurationSection("Keys").getKeys(false)) {
//            if (args.equals(target)) {
//                return true;
//            }
//        }
//        return false;
//    }
}

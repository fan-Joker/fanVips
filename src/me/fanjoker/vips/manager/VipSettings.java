package me.fanjoker.vips.manager;

import me.fanjoker.vips.Main;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class VipSettings {

//    public String getVipName(String name) {
//        if (name.equalsIgnoreCase("vip")) {
//            return "VIP";
//        } else if (name.equalsIgnoreCase("vip+")) {
//            return "VIP+";
//        } else if (name.equalsIgnoreCase("pro")) {
//            return "PRO";
//        } else if (name.equalsIgnoreCase("noob")) {
//            return "Noob";
//        }
//        return "";
//    }
    public boolean existsVip(String vip) {
        List<String> lista = new ArrayList<>();
        List<String> list = Main.configManager.getConfig("config").getYaml().getStringList("Vip_grupos");
        for (String str : list) {
            lista.add(str.toLowerCase());
        }
        return lista.contains(vip);
    }
    public void changeCommands(String name, String oldvip, String newvip) {
        for (String i : Main.configManager.getConfig("config").getYaml().getStringList("Comandos_trocar")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" + i
                    .replace("%name%", name)
                    .replace("%oldgroup%", oldvip)
                    .replace("%newgroup%", newvip));
        }
    }
    public void ativationCommands(String name, String vip) {
        if (Main.getPlayerManager().getVips(name).size() > 1) return;

        for (String i : Main.configManager.getConfig("config").getYaml().getStringList("Comandos_ativacao")) {
            String group = i.split(",")[0];
            if (group.toLowerCase().equalsIgnoreCase(vip)) {
                String comando = i.split(",")[1];
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" + comando.replace("%name%", name));
            }
        }
    }
    public void removeCommands(String name, String vip) {
        for (String i : Main.configManager.getConfig("config").getYaml().getStringList("Comandos_remover")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" + i
                    .replace("%name%", name)
                    .replace("%grupo%", vip));
        }
    }
}

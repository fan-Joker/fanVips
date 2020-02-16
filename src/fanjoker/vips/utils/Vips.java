package fanjoker.vips.utils;

import com.sun.org.apache.xerces.internal.xs.StringList;
import fanjoker.vips.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import static fanjoker.vips.utils.RandomString.getString;

public class Vips {

    static YamlConfiguration config = Main.configManager.getConfig("vips").getYaml();

    public static boolean hasVip(String name) {
        if(getVips(name) == 0) {
            return true;
        }
        return false;
    }
    public static boolean hasTheVip(String name, String vip) {
        if(getVips(name) == 0) {
            return true;
        }
        if (config.get("Vips." + name + ".tempo." + vip) == null) {
                return true;
        }
        return false;
//        if(config.getConfigurationSection("Vips." + name) == null) {
//            return true;
//        }
//        for (String vips : config.getConfigurationSection("Vips." + name + ".tempo").getKeys(false)) {
//            if (vips.equalsIgnoreCase(vip)) {
//                return true;
//            }
//        }
//        return false;
    }
    public static boolean hasVipPerm(String name, String vip) {
        if(getVips(name) == 0) {
            return true;
        }
        try {
            if (!config.getString("Vips." + name + ".tempo." + vip).equalsIgnoreCase("Perm")) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }
    public static int getVips(String name) {
        try {
            int count = 0;
            for (String homes : config.getConfigurationSection("Vips." + name + ".tempo").getKeys(false)) {
                count++;
            }
            return count;
        } catch (Exception e){
            return 0;
        }
    }
    public static int getKeys() {
        try {
            int count = 0;
            for (String homes : config.getConfigurationSection("Keys").getKeys(false)) {
                count++;
            }
            return count;
        } catch (Exception e){
            return 0;
        }
    }
    public static String getKeysNames() {
        for (String target : config.getConfigurationSection("Keys").getKeys(false)) {
            return target;
        }
        return "";
    }
    public static String usingVip(String name) {
        return Main.configManager.getConfig("vips").getYaml().getString("Vips." + name + ".usando");
    }
    public static boolean existsVip(String vip) {
        for (String gs : Main.configManager.getConfig("config").getYaml().getStringList("Vip_grupos")) {
            if (gs.trim().equalsIgnoreCase(vip)) {
                return true;
            }
        }
        return false;
    }
    public static String getVip(Player p) {
        for (String gs : Main.configManager.getConfig("config").getYaml().getStringList("Vip_grupos")) {
            if (config.getConfigurationSection("Vips." + p.getName() + ".tempo." + gs) == null) {
                return gs;
            }
        }
        return "";
    }
    public static long getVipTime(String name, String vip) {
        if (config.getConfigurationSection("Vips." + name + ".tempo." + vip) == null) {
            long a = config.getLong("Vips." + name + ".tempo." + vip);
            return a - System.currentTimeMillis();
        }
        return 0L;
    }
    public static void createKey(String key, String vip, int dias) {
        BigDecimal one = new BigDecimal("1000").multiply(BigDecimal.valueOf(dias));

        config.set("Keys." + key + ".tempo", one.longValue());
        config.set("Keys." + key + ".vip", vip);
        Main.configManager.getConfig("vips").save();

    }
    public static void darVipPerm(String nome, String grupo) {
        if(hasTheVip(nome, grupo)) {
            Calendar now = Calendar.getInstance();
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            config.set("Vips." + nome + ".inicio", fmt.format(now.getTime()));
            config.set("Vips." + nome + ".usando", grupo);
            config.set("Vips." + nome + ".tempo." + grupo, "Perm");
            Main.configManager.getConfig("vips").save();
        } else {
            config.set("Vips." + nome + ".tempo." + grupo, "Perm");
            Main.configManager.getConfig("vips").save();
        }
    }
    public static void darVip(String nome, int dias, String grupo) {
        if (hasTheVip(nome, grupo)) {
            Calendar now = Calendar.getInstance();
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            config.set("Vips." + nome + ".inicio", fmt.format(now.getTime()));
            config.set("Vips." + nome + ".usando", grupo);
            Main.configManager.getConfig("vips").save();

            BigDecimal one = new BigDecimal("1000").multiply(BigDecimal.valueOf(dias));

            long delay1 = System.currentTimeMillis() + one.longValue();

            long entrag1 = config.getLong("Vips." + nome + ".tempo." + grupo);
            if (entrag1 > System.currentTimeMillis()) {
                return;
            }
            config.set("Vips." + nome + ".tempo." + grupo, delay1);
            Main.configManager.getConfig("vips").save();

            execCommandsA(nome, grupo);
//        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + nome + " group add " + grupo);
//        if(dias == 1) {
//            Bukkit.broadcastMessage("§eO jogador §6" + nome + "§e acabou de ativar o vip §6[" + grupo + "] §epor §6" + dias + "§e dia");
//        } else {
//            Bukkit.broadcastMessage("§eO jogador §6" + nome + "§e acabou de ativar o vip §6[" + grupo + "] §epor §6" + dias + "§e dias");
//        }
        } else {
            long entrag1 = config.getLong("Vips." + nome + ".tempo." + grupo);
            BigDecimal on = new BigDecimal("1000").multiply(BigDecimal.valueOf(dias));
            BigDecimal one = on.add(new BigDecimal(entrag1));
            config.set("Vips." + nome + ".tempo." + grupo, one.longValue());
            Main.configManager.getConfig("vips").save();
        }
    }
    public static void execCommandsA(String name, String vip) {
        for (String i : Main.configManager.getConfig("config").getYaml().getStringList("Comandos_ativacao")) {
            String group = i.split(",")[0];
            if (group.toLowerCase().equalsIgnoreCase(vip)) {
                String comando = i.split(",")[1];
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" + comando.replace("@player", name));
            }
        }
    }
    public static void execCommandsT(String name, String oldvip, String newvip) {
        for (String i : Main.configManager.getConfig("config").getYaml().getStringList("Comandos_trocar")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" + i
                        .replace("%name%", name)
                        .replace("%oldgroup%", oldvip)
                        .replace("%newgroup%", newvip));
        }
    }
}

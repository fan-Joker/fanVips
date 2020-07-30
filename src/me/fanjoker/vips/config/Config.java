package me.fanjoker.vips.config;

import me.fanjoker.vips.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class Config {


    public static String getString(String string) {
        return Main.configManager.getConfig("config").getYaml().getString(string);
    }

    public static YamlConfiguration getConfig() {
        return Main.configManager.getConfig("config").getYaml();
    }

    public static int getInt(String string) {
        return Main.configManager.getConfig("config").getYaml().getInt(string);
    }

    public static List getStringL(String string) {
        return Main.configManager.getConfig("config").getYaml().getStringList(string);
    }

    public static boolean getBoolean(String a) {
        return Main.configManager.getConfig("config").getYaml().getBoolean(a);
    }

    public static void set(String path, Object value) {
        Main.configManager.getConfig("config").getYaml().set(path, value);
    }

    public void save() {
        Main.configManager.getConfig("config").save();
    }
}

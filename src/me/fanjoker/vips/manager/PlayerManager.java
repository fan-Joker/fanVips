package me.fanjoker.vips.manager;

import me.fanjoker.vips.Main;
import me.fanjoker.vips.utils.TFormat;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PlayerManager {

    private Connection getCon() {
        return Main.getConexao().getConnection();
    }

    public void createPlayer(String target) {
        PreparedStatement stm = null;
        try {
            stm = getCon().prepareStatement("INSERT INTO `Vip_PLAYER` (`player`, `vips`, `using`) VALUES(?,?,?)");
            stm.setString(1, target);
            stm.setString(2, "");
            stm.setString(3, "");
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletePlayer(String player) {
        PreparedStatement stm = null;
        try {
            stm = getCon().prepareStatement("DELETE FROM `Vip_PLAYER` WHERE LOWER(`player`) = ?");
            stm.setString(1, player.toLowerCase());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeVip(String target, String vip) {
        if (hasVip(target)) {
            if (getVips(target).size() == 1) {
                Main.getSettings().removeCommands(target, vip);
                deletePlayer(target);
                return;
            }
            String str = get("vips", target);
            String time = getVips(target).get(vip);
            set("vips", str.replace("" + vip + ";" + time + " ", ""), target);
            Main.getSettings().removeCommands(target, vip);
        }


    }
    public boolean contaisJogador(String name) {
        PreparedStatement stm = null;
        try {
            stm = getCon().prepareStatement("SELECT `player` FROM `Vip_PLAYER` WHERE LOWER(`player`) = ?");
            stm.setString(1, name.toLowerCase());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean hasVipPerm(String target, String vip) {
        if (hasVip(target, vip)) {
            for (String str : getVips(target).keySet()) {
                if (str.equalsIgnoreCase(vip)) {
                    return getVips(target).get(str).equalsIgnoreCase("perm");
                }
            }
        }
        return false;
    }
    public String cap(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }


    public void addVip(String player, String vip, long time, boolean perm) {
        if (!Main.getPlayerManager().contaisJogador(player)) {
            Main.getPlayerManager().createPlayer(player);
        }
        String str = get("vips", player);
        if (hasVip(player, vip)) {
            String replace1 = vip + ";" + getVips(player).get(vip) + " ";
            long lon = Long.valueOf(getVips(player).get(vip)) + (time - System.currentTimeMillis());
            String replace2 = vip + ";" + lon + " ";
            set("vips", str.replace(replace1, replace2), player);
            Bukkit.getPlayerExact(player).sendMessage(
                    "§aFoi adicionado '" + TFormat.format(time - System.currentTimeMillis()) + "' ao seu vip '" + cap(vip) + "'");
            return;
        }

        if (perm) {
            if (vipSize(player) >= 1) {
                set("vips", str + vip + ";perm ", player);
                setVipUsing(player, vip);
                return;
            }
            set("vips", str + vip + ";perm ", player);
            set("using", vip, player);
            Main.getSettings().ativationCommands(player, vip);
            return;
        }
        if (vipSize(player) >= 1) {
            set("vips", str + vip + ";" + time + " ", player);
            setVipUsing(player, vip);
            return;
        }
        set("vips", str + vip + ";" + time + " ", player);
        set("using", vip, player);
        Main.getSettings().ativationCommands(player, vip);
    }
    private int vipSize(String target) {
        if (hasVip(target)) {
            return getVips(target).size();
        }
        return 0;
    }
    public String getVipUsing(String target) {
        return get("using", target);
    }
    public void setVipUsing(String target, String vip) {
        String oldvip = get("using", target);
        Main.getSettings().changeCommands(target, oldvip, vip);
        set("using", vip, target);
    }
    public HashMap<String, String> getVips(String player) {
        HashMap<String, String> hash = new HashMap<>();
        String string = get("vips", player);
        if (string.contains(" ")) {
            String[] args = string.split(" ");
            for (String str : args) {
                String[] strs = str.split(";");
                hash.put(strs[0], strs[1]);
            }
        } else {
            String[] strs = get("vips", player).split(";");
            hash.put(strs[0], strs[1]);
            return hash;
        }
        return hash;
    }
    public boolean hasVip(String target) {
        List<String> list = Main.configManager.getConfig("config").getYaml().getStringList("Vip_grupos");
        for (String str : list) {
            if (hasVip(target, str)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasVip(String target, String vip) {
        return !get("vips", target).equalsIgnoreCase("") && getVips(target).containsKey(vip);
    }
    public boolean has1Vip(String target) {
        if (hasVip(target)) {
            return getVips(target).size() == 1;
        }
        return false;
    }


//
//    PRIVATE STATEMENTS
//

    private String get(String path, String name) {
        String result = "";
        PreparedStatement stm = null;
        try {
            stm = getCon().prepareStatement("SELECT * FROM `Vip_PLAYER` WHERE LOWER(`player`) = ?");
            stm.setString(1, name.toLowerCase());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                result =rs.getString(path);
            }
            rs.close();
            stm.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void set(String path, String value, String name) {
        PreparedStatement stm = null;
        try {
            stm = getCon().prepareStatement("UPDATE `Vip_PLAYER` SET `" + path + "` = ? WHERE LOWER(`player`) = ?");
            stm.setString(1, value);
            stm.setString(2, name.toLowerCase());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

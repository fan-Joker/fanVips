package me.fanjoker.vips.manager;

import me.fanjoker.vips.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KeyManager {

    private Connection getCon() {
        return Main.getConexao().getConnection();
    }

    public void createKey(String vip, String time) {
        PreparedStatement stm = null;
        try {
            stm = getCon().prepareStatement("INSERT INTO `Vip_KEY` (`key`, `vip`, `time`) VALUES(?,?,?)");
            stm.setString(1, getString(10));
            stm.setString(2, "");
            stm.setString(3, "");
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static String getString(int lenght){
        StringBuilder builder = new StringBuilder();
        String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i<lenght; i++){
            double index = Math.random() * s.length();
            builder.append(s.charAt((int)index));
        }
        return builder.toString();
    }
}

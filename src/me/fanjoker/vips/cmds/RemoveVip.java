package me.fanjoker.vips.cmds;

import me.fanjoker.vips.Main;
import me.fanjoker.vips.Messages;
import me.fanjoker.vips.utils.TFormat;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class RemoveVip implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        if(p.hasPermission("fanvips.admin")) {
            if (args.length == 0) {
                p.sendMessage("§cUso: /rvip <nome>");
                return true;
            } else if (args.length == 1) {
                String target = args[0];
                if (Main.getPlayerManager().hasVip(target)) {
                    jsonMessage(p, target);
                } else {
                    p.sendMessage("§cO jogador '" + target + "' não nenhum vip.");
                }
            } else if (args.length == 2) {
                String target = args[0];
                String vip = args[1];
                if (vip.equalsIgnoreCase("*")) {
                    for (String str : Main.getPlayerManager().getVips(target).keySet()) {
                        Main.getSettings().removeCommands(target, str);
                    }
                    p.sendMessage("§aVip(s) do jogador '" + target + "' removido(s) com êxito.");
                    Main.getPlayerManager().deletePlayer(target);
                } else if (Main.getSettings().existsVip(vip)) {
                    Main.getPlayerManager().removeVip(target, vip);
                    p.sendMessage("§aVip '" + vip + "' do jogador '" + target + "' removido(s) com êxito.");
                }
            }
        } else {
            p.sendMessage(Messages.noperm());
        }
        return false;
    }
    private void jsonMessage(Player p, String target) {
        p.sendMessage(new String[]{"", " §e§lVips: §7Lista de vips de: " + target, ""});
        for (String vip : Main.getPlayerManager().getVips(target).keySet()) {
            TextComponent msg = new TextComponent("  §e- " + Main.getPlayerManager().cap(vip) + " §a(Clique para remover)");
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aClique confirmar a ação").create()));
            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rvip " + target + " " + vip));
            p.spigot().sendMessage(msg);
        }
        if (Main.getPlayerManager().getVips(target).size() >= 2) {
            TextComponent msg = new TextComponent("  §e- Todos §a(Clique para remover todos os vips)");
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aClique confirmar a ação").create()));
            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rvip " + target + " *"));
            p.spigot().sendMessage(msg);
        }
        p.sendMessage("");
    }
}

package io.github.Keyami.KeyCombatlog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.Keyami.KeyCombatlog.CombatEvent.combatLogged;
import static io.github.Keyami.KeyCombatlog.KeyCombatlog.pl;

public class CombatlogCommands implements CommandExecutor {

    String prefix = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("prefix"));

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        command.setUsage(prefix + ChatColor.translateAlternateColorCodes('&', pl.config.getString("command-usage")));
        if (sender instanceof Player && sender.hasPermission("cl.use")) {
            Player p = (Player) sender;
            if (!(args.length == 1) || args.length > 1) {
                p.sendMessage(command.getUsage());
                return true;
            }
            if (args[0].equalsIgnoreCase("check")) {
                if (combatLogged.containsKey(p.getUniqueId())) {
                    p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',pl.config.getString("is-combat-logged")));
                } else {
                    p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',pl.config.getString("is-not-combat-logged")));
                }
                return true;

            }
            if (args[0].equalsIgnoreCase("on")) {
                combatLogged.put(p.getUniqueId(), pl.config.getInt("timer"));

                p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',pl.getConfig().getString("defender-logged")));

                Bukkit.getServer().getScheduler().runTaskLater(pl, () -> {
                    combatLogged.remove(p.getUniqueId());
                    p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',pl.config.getString("safe-to-leave")));
                }, combatLogged.get(p.getUniqueId()) * 1200L);
                return true;
            }
            if (args[0].equalsIgnoreCase("off")) {
                combatLogged.remove(p.getUniqueId());
                p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',pl.config.getString("safe-to-leave")));
                return true;
            }
        } else {
            sender.sendMessage(prefix + "Only players can use this command!");
            return false;
        }

        return false;
    }
}

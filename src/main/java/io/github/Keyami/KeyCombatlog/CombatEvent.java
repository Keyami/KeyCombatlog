package io.github.Keyami.KeyCombatlog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

import static io.github.Keyami.KeyCombatlog.KeyCombatlog.pl;

public class CombatEvent implements Listener {

    String prefix = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("prefix"));
    int time = pl.getConfig().getInt("timer");

    public static HashMap<UUID, Integer> combatLogged = new HashMap<UUID, Integer>();

    @EventHandler (priority = EventPriority.HIGH)
    public void combatLog (EntityDamageByEntityEvent e) {
        if (pl.config.getBoolean("mobs-trigger-combatlog")) {
            if (!(e.getDamager() instanceof Player)
                && e.getEntity() instanceof Player) {
                Player defendingPlayer = (Player) e.getEntity();
                defendingPlayer.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',pl.getConfig().getString("defender-logged")));

                if (pl.config.getBoolean("disable-flight")) {
                    defendingPlayer.setFlying(false);
                    defendingPlayer.setAllowFlight(false);
                }

                combatLogged.put(defendingPlayer.getUniqueId(), time);

                Bukkit.getServer().getScheduler().runTaskLater(pl, () -> {
                    combatLogged.remove(defendingPlayer.getUniqueId());
                    defendingPlayer.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',pl.config.getString("safe-to-leave")));
                }, combatLogged.get(defendingPlayer.getUniqueId()) * 1200L);

            }
        } else {
            if (e.getDamager() instanceof Player
                && e.getEntity() instanceof Player) {

                Player enemyPlayer = (Player) e.getDamager();
                enemyPlayer.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',pl.getConfig().getString("attacker-logged")));

                if (pl.config.getBoolean("disable-flight")) {
                    enemyPlayer.setFlying(false);
                    enemyPlayer.setAllowFlight(false);
                }

                combatLogged.put(enemyPlayer.getUniqueId(), time);

                Bukkit.getServer().getScheduler().runTaskLater(pl, () -> {
                    combatLogged.remove(enemyPlayer.getUniqueId());
                    enemyPlayer.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',pl.config.getString("safe-to-leave")));
                }, combatLogged.get(enemyPlayer.getUniqueId()) * 1200L);

                Player defendingPlayer = (Player) e.getEntity();
                defendingPlayer.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',pl.getConfig().getString("defender-logged")));

                if (pl.config.getBoolean("disable-flight")) {
                    defendingPlayer.setFlying(false);
                    defendingPlayer.setAllowFlight(false);
                }

                combatLogged.put(defendingPlayer.getUniqueId(), time);

                Bukkit.getServer().getScheduler().runTaskLater(pl, () -> {
                    combatLogged.remove(defendingPlayer.getUniqueId());
                    defendingPlayer.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',pl.config.getString("safe-to-leave")));
                }, combatLogged.get(defendingPlayer.getUniqueId()) * 1200L);

            }
        }

    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onDisconnect (PlayerQuitEvent e) {
        if (pl.getConfig().getBoolean("kill-on-disconnect")
                && combatLogged.containsKey(e.getPlayer().getUniqueId())
                    && combatLogged.get(e.getPlayer().getUniqueId()) > 0) {
            e.getPlayer().damage(1000);
        }
    }

}

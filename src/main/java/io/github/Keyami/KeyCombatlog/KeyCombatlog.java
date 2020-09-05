package io.github.Keyami.KeyCombatlog;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class KeyCombatlog extends JavaPlugin {

    FileConfiguration config = this.getConfig();
    public static KeyCombatlog pl;

    @Override
    public void onEnable() {
        pl = this;
        getLogger().info("KeyCombatlog has been enabled!");
        getServer().getPluginManager().registerEvents(new CombatEvent(), this);

        this.getCommand("cl").setExecutor(new CombatlogCommands());
        this.getCommand("cl").setTabCompleter(new TabCompletion());
    }

    @Override
    public void onDisable() {
        getLogger().info("KeyCombatlog has been disabled!");
    }
}

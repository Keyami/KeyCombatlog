package io.github.Keyami.KeyCombatlog;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabCompletion implements TabCompleter {
    public static final String[] cl = {"check", "on", "off"};

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<String>();
        StringUtil.copyPartialMatches(args[0], Arrays.asList(cl), completions);
        Collections.sort(completions);
        return completions;

    }
}
package me.deadorfd.simplespawn.utils;

import static me.deadorfd.simplespawn.utils.Config.*;

import java.util.ArrayList;

import org.bukkit.entity.Player;

/**
 * @Author DeaDorfd
 * @Project simplespawn
 * @Package me.deadorfd.simplespawn.utils
 * @Date 06.06.2023
 * @Time 14:28:24
 */
public class Utils {
	public static boolean hasPermission(Player player, String permission) {
		return player.hasPermission(Config.getPermission("Admin"))
				|| player.hasPermission(Config.getPermission(permission));
	}

	public static String wrongCommand(String command) {
		return getString("Prefix") + getMessage("WrongCommand").replace("%command%", command);
	}

	public static String noPermission() {
		return getString("Prefix") + getMessage("NoPermission");
	}

	public static String mustPlayer() {
		return getMessage("MustPlayer");
	}

	public static String noPlayerFound(String name) {
		return getString("Prefix") + getMessagePlayer("NoPlayerFound", name);
	}

	public static ArrayList<String> tabComplete(String[] args, ArrayList<String> subcommands) {
		final ArrayList<String> l = new ArrayList<String>();
		if (!args[0].isEmpty()) {
			subcommands.forEach(s -> {
				if (s.toLowerCase().startsWith(args[0].toLowerCase())) l.add(s);
			});
		} else {
			subcommands.forEach(s -> l.add(s));
		}
		return l;
	}
}

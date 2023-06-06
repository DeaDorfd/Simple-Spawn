package me.deadorfd.simplespawn.utils;

import static me.deadorfd.simplespawn.utils.Config.*;

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
}
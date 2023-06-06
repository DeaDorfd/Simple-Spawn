package me.deadorfd.simplespawn.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.deadorfd.simplespawn.utils.Check;
import me.deadorfd.simplespawn.utils.Config;

/**
 * @Author DeaDorfd
 * @Project simplespawn
 * @Package me.deadorfd.simplespawn.listener
 * @Date 06.06.2023
 * @Time 14:08:10
 */
public class Join_Listener implements Listener {

	@EventHandler
	private static void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (Config.getBoolean("Teleport Player Spawn")) {
			if (Config.isSpawnSet()) Config.teleportSpawn(player);
		} else if (Config.getBoolean("Teleport new Player Spawn")) {
			if (!player.hasPlayedBefore()) {
				if (Config.isSpawnSet()) Config.teleportSpawn(player);
			}
		}

		if (!Config.getBoolean("Check for Updates")) return;
		if (Check.isUpdated()) return;
		if (player.hasPermission(Config.getPermission("Admin"))) {
			player.sendMessage(Config.getMessage("Notify on Join").replaceAll("%link%",
					"https://www.spigotmc.org/resources/" + Check.getResourceID() + "/"));
		}
	}

}

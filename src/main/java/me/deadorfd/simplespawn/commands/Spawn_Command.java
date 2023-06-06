package me.deadorfd.simplespawn.commands;

import static me.deadorfd.simplespawn.utils.Utils.*;
import static me.deadorfd.simplespawn.utils.Config.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Author DeaDorfd
 * @Project simplespawn
 * @Package me.deadorfd.simplespawn.commands
 * @Date 06.06.2023
 * @Time 14:08:26
 */
public class Spawn_Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("setSpawn")) {
			if (!hasPermission(player, "SetSpawn")) {
				player.sendMessage(noPermission());
				return true;
			}
			if (args.length != 0) {
				player.sendMessage(wrongCommand("setSpawn"));
				return true;
			}
			setSpawn(player);
			player.sendMessage(getString("Prefix") + getMessage("SpawnSet"));
		} else if (label.equalsIgnoreCase("Spawn")) {
			if (args.length != 0) {
				player.sendMessage(wrongCommand("Spawn"));
				return true;
			}
			if (!isSpawnSet()) {
				player.sendMessage(getString("Prefix") + getMessage("SpawnWasNotSet"));
				return true;
			}
			teleportSpawn(player);
			player.sendMessage(getString("Prefix") + getMessage("SpawnTeleported"));
		}
		return false;
	}
}
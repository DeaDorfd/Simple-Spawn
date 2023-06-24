package me.deadorfd.simplespawn.commands;

import static me.deadorfd.simplespawn.utils.Utils.*;

import java.util.ArrayList;

import static me.deadorfd.simplespawn.utils.Config.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.deadorfd.simplespawn.SimpleSpawn;

/**
 * @Author DeaDorfd
 * @Project simplespawn
 * @Package me.deadorfd.simplespawn.commands
 * @Date 06.06.2023
 * @Time 14:08:26
 */
public class Spawn_Command implements CommandExecutor {

	private ArrayList<Player> cooldown = new ArrayList<>();

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
			int cooldownTime = getInt("Cooldown time");
			if (cooldown.contains(player)) {
				player.sendMessage(getString("Prefix")
						+ getMessage("InCooldown").replaceAll("%time%", cooldownTime + ""));
				return true;
			}
			teleportSpawn(player);
			cooldown.add(player);
			player.sendMessage(getString("Prefix") + getMessage("SpawnTeleported"));
			if (cooldownTime != 0) {
				new BukkitRunnable() {
					int time = 0;

					@Override
					public void run() {
						time++;
						if (time >= cooldownTime) {
							cooldown.remove(player);
							cancel();
						}

					}
				}.runTaskTimer(SimpleSpawn.getInstance(), 20, 20);
			}
		}
		return false;
	}
}
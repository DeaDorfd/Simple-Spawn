package me.deadorfd.simplespawn.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.deadorfd.simplespawn.SimpleSpawn;

/**
 * @Author DeaDorfd
 * @Project simplespawn
 * @Package me.deadorfd.simplespawn.utils
 * @Date 06.06.2023
 * @Time 14:28:30
 */
public class Config {
	public static FileConfiguration cfg = SimpleSpawn.getInstance().getConfig();

	public static String getMessage(String path) {
		return cfg.getString("Messages." + path).replaceAll("&", "§");
	}

	public static String getPermission(String path) {
		return cfg.getString("Permissions." + path);
	}

	public static String getString(String path) {
		return cfg.getString(path).replaceAll("&", "§");
	}

	public static int getInt(String path) {
		return cfg.getInt(path);
	}

	public static boolean getBoolean(String path) {
		return cfg.getBoolean(path);
	}

	public static void setSpawn(Player player) {
		File file = new File(SimpleSpawn.getInstance().getDataFolder().getPath() + "//spawn.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {}
		}
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Location loc = player.getLocation();
		cfg.set("X", loc.getX());
		cfg.set("Y", loc.getY());
		cfg.set("Z", loc.getZ());
		cfg.set("Yaw", loc.getYaw());
		cfg.set("Pitch", loc.getPitch());
		cfg.set("WorldName", loc.getWorld().getName());
		try {
			cfg.save(file);
		} catch (IOException e) {}
	}

	public static void teleportSpawn(Player player) {
		File file = new File(SimpleSpawn.getInstance().getDataFolder().getPath() + "//spawn.yml");
		if (!file.exists()) return;
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		World world = Bukkit.getWorld(cfg.getString("WorldName"));
		double yaw = cfg.getDouble("Yaw");
		double pitch = cfg.getDouble("Pitch");
		player.teleport(new Location(world, cfg.getDouble("X"), cfg.getDouble("Y"),
				cfg.getDouble("Z"), (float) yaw, (float) pitch));
	}

	public static boolean isSpawnSet() {
		return new File(SimpleSpawn.getInstance().getDataFolder().getPath() + "//spawn.yml")
				.exists();
	}
}

package me.deadorfd.simplespawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.deadorfd.simplespawn.commands.Spawn_Command;
import me.deadorfd.simplespawn.listener.Join_Listener;

/**
 * @Author DeaDorfd
 * @Project simplespawn
 * @Package me.deadorfd.simplespawn
 * @Date 06.06.2023
 * @Time 14:00:07
 */
public class SimpleSpawn extends JavaPlugin {

	private static SimpleSpawn instance;

	@Override
	public void onEnable() {
		instance = this;
		getDataFolder().mkdir();
		saveDefaultConfig();
		getCommand("setSpawn").setExecutor(new Spawn_Command());
		getCommand("Spawn").setExecutor(new Spawn_Command());
		Bukkit.getPluginManager().registerEvents(new Join_Listener(), instance);
	}

	public static SimpleSpawn getInstance() {
		return instance;
	}
}
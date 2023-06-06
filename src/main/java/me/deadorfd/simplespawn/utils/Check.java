package me.deadorfd.simplespawn.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import me.deadorfd.simplespawn.SimpleSpawn;

/**
 * @Author DeaDorfd
 * @Project simplespawn
 * @Package me.deadorfd.simplespawn.utils
 * @Date 06.06.2023
 * @Time 14:36:32
 */
public class Check {
	private static int resourceID = 110301;
	private static boolean updated = false;

	public static void check() {
		String check = readURL().replace(".", "_");
		int version = Integer.valueOf(check.split("_")[0]);
		int minorversion = Integer.valueOf(check.split("_")[1]);
		String stringversion = SimpleSpawn.getInstance().getDescription().getVersion().replace(".", "_");
		int pversion = Integer.valueOf(stringversion.split("_")[0]);
		int pminorversion = Integer.valueOf(stringversion.split("_")[1]);
		if (version > pversion) {
			setUpdated(false);
			return;
		}
		if (minorversion > pminorversion) {
			setUpdated(false);
			return;
		}
		setUpdated(true);
		return;
	}

	private static String readURL() {
		String re = "";
		try {
			URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceID);
			Reader is = new InputStreamReader(url.openStream());
			BufferedReader in = new BufferedReader(is);
			String s;
			while ((s = in.readLine()) != null)
				re = re + s;
			in.close();
		} catch (Exception e) {
			setUpdated(false);
		}
		return re;
	}

	public static boolean isUpdated() {
		return updated;
	}

	private static void setUpdated(boolean state) {
		updated = state;
	}

	public static int getResourceID() {
		return resourceID;
	}
}

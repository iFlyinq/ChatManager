package me.h1dd3nxn1nja.chatmanager.hooks;

import me.h1dd3nxn1nja.chatmanager.Methods;
import org.bukkit.Bukkit;

public class HookManager {

	protected static PlaceholderAPIHook placeholderApi;
	protected static VaultHook vault;
	protected static ProtocolLibHook protocolLib;
	protected static SuperVanishHook superVanish;
	protected static SuperVanishHook premiumVanish;
	protected static EssentialsHook essentials;

	public HookManager() {}

	public void hookManager() {
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			placeholderApi = new PlaceholderAPIHook();
		}
	}

	public static void loadDependencies() {

		if (Methods.doesPluginExist("PlaceholderAPI")) {
			placeholderApi = new PlaceholderAPIHook();
			new PlaceholderAPIHook(Methods.getPlugin()).register();
		}

		if (Methods.doesPluginExist("Essentials")) {
			essentials = new EssentialsHook();
		}

		if (Methods.doesPluginExist("Vault")) {
			vault = new VaultHook();
		}

		if (Methods.doesPluginExist("SuperVanish")) {
			superVanish = new SuperVanishHook();
		}
		
		if (Methods.doesPluginExist("PremiumVanish")) {
			premiumVanish = new SuperVanishHook();
		}
		
		//older version

		if (Methods.doesPluginExist("Factions")) {
			if (Bukkit.getPluginManager().getPlugin("Factions").getDescription().getVersion().startsWith("1.6")) {
				Bukkit.getConsoleSender().sendMessage("Chat Manager only supports the free version of factions v2.x");
				return;
			}
			if (Bukkit.getPluginManager().getPlugin("Factions").getDescription().getVersion().startsWith("3")) {
				Bukkit.getConsoleSender().sendMessage("Chat Manager doesn't support factions v3.x");
				return;
			}
			Class<?> mplayer = null;
			try {
				mplayer = Class.forName("com.massivecraft.factions.entity.MPlayer");
			} catch (ClassNotFoundException ex) {
				Bukkit.getConsoleSender().sendMessage("Chat Manager only supports the free version of factions.");
			}
		}
	}

	public static boolean isEssentialsLoaded() {
		return essentials != null;
	}

	public static boolean isSuperVanishLoaded() {
		return superVanish != null;
	}
	
	public static boolean isPremiumVanishLoaded() {
		return premiumVanish != null;
	}

	public static boolean isPlaceholderAPILoaded() {
		return placeholderApi != null;
	}
	
	public static boolean isProtocolLibLoaded() {
		return protocolLib != null;
	}

	public static boolean isVaultLoaded() {
		return vault != null;
	}
}
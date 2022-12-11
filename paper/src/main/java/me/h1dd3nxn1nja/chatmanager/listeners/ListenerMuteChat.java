package me.h1dd3nxn1nja.chatmanager.listeners;

import me.h1dd3nxn1nja.chatmanager.ChatManager;
import me.h1dd3nxn1nja.chatmanager.Methods;
import me.h1dd3nxn1nja.chatmanager.commands.CommandMuteChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ListenerMuteChat implements Listener {
	
	public ListenerMuteChat(CommandMuteChat plugin) {}
	
	public ListenerMuteChat(ChatManager plugin) {}
	
	@EventHandler
	public void muteChat(AsyncPlayerChatEvent event) {
		
		FileConfiguration messages = ChatManager.settings.getMessages();
		
		Player player = event.getPlayer();
		
		if (!player.hasPermission("chatmanager.bypass.mutechat")) {
			if (Methods.getMuted()) {
				player.sendMessage(Methods.color(player, messages.getString("Mute_Chat.Denied_Message").replace("{Prefix}", messages.getString("Message.Prefix"))));
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		
		FileConfiguration config = ChatManager.settings.getConfig();
		FileConfiguration messages = ChatManager.settings.getMessages();
		
		Player player = event.getPlayer();
		
		if (config.getBoolean("Mute_Chat.Disable_Commands")) {
			if (!player.hasPermission("chatmanager.bypass.mutechat")) {
				if (Methods.getMuted()) {
					for (String command : config.getStringList("Mute_Chat.Disabled_Commands")) {
						if (event.getMessage().toLowerCase().contains(command)) {
							player.sendMessage(Methods.color(player, messages.getString("Mute_Chat.Blocked_Commands.Message").replace("{Prefix}", messages.getString("Message.Prefix"))));
							event.setCancelled(true);
							return;
						}
					}
				}
			}
		}
	}
}
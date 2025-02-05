package io.flydev.chatmanager.processors.spam;

import io.flydev.chatmanager.processors.FormattedChatProcessor;
import me.h1dd3nxn1nja.chatmanager.configuration.Message;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Niels Warrens - 23/12/2022
 */
public class RepeatingChatMessageProcessor extends FormattedChatProcessor<AsyncPlayerChatEvent> {

	private static final String BYPASS_PERMISSION = "chatmanager.bypass.dupe.chat";
	private final Map<UUID, String> lastMessages = new HashMap<>();

	@Override
	public void process(AsyncPlayerChatEvent event) {
		if (event.getPlayer().hasPermission(BYPASS_PERMISSION)) return;

		String message = event.getMessage();
		UUID uuid = event.getPlayer().getUniqueId();

		// Check if the message is the same as the last message
		if (this.lastMessages.getOrDefault(uuid, "").equalsIgnoreCase(message)) {
			event.getPlayer().sendMessage(Message.SPAM_BLOCK_REPETITIVE_MESSAGE.string());
			event.setCancelled(true);
			return;
		}

		// Overwrite the last message
		this.lastMessages.put(uuid, message);
	}

}

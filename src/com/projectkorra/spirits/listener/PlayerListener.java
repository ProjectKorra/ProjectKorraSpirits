package com.projectkorra.spirits.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.projectkorra.spirits.ProjectKorraSpirits;
import com.projectkorra.spirits.api.PlayerData;

public class PlayerListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		new PlayerData(event.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		PlayerData playerData = ProjectKorraSpirits.plugin.getPlayerDataManager().get(event.getPlayer().getUniqueId());
		if (playerData != null) {
			playerData.cache();
		}
	}

}

package com.projectkorra.spirits.api;

import java.util.UUID;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.spirits.ProjectKorraSpirits;

public class PlayerData {

	private UUID uuid;
	private BendingPlayer bPlayer;

	public PlayerData(UUID uuid) {
		this.uuid = uuid;
		this.bPlayer = BendingPlayer.getBendingPlayer(ProjectKorraSpirits.plugin.getServer().getPlayer(uuid));

		/*
		 * TODO load PlayerData data from memory
		 */

		ProjectKorraSpirits.plugin.getPlayerDataManager().register(uuid, this);
	}

	/**
	 * Store all data relating to {@link this} instance to memory.
	 */
	public void cache() {

	}

	public UUID getUUID() {
		return uuid;
	}

	public BendingPlayer getBendingPlayer() {
		return bPlayer;
	}

}

package com.projectkorra.spirits.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.projectkorra.spirits.api.PlayerData;

public class PlayerDataManager {

	private final Map<UUID, PlayerData> PLAYER_DATA = new HashMap<>();

	public PlayerDataManager() {

	}

	/**
	 * Store the specified {@link PlayerData} Object in memory with the UUID
	 * key.
	 * 
	 * @param uuid
	 * @param playerData
	 */
	public void register(UUID uuid, PlayerData playerData) {
		PLAYER_DATA.put(uuid, playerData);
	}

	/**
	 * Unload the specified UUID from memory.
	 * 
	 * @param uuid
	 */
	public void unregister(UUID uuid) {
		if (registered(uuid)) {
			PLAYER_DATA.remove(uuid);
		}
	}

	/**
	 * Check if UUID has been registered.
	 * 
	 * @param uuid
	 * @return true if {@link PlayerDataManager#PLAYER_DATA} contains uuid
	 */
	public boolean registered(UUID uuid) {
		return PLAYER_DATA.containsKey(uuid);
	}

	/**
	 * Get the {@link PlayerData} Object stored with the specified UUID as key.
	 * 
	 * @param uuid
	 * @return the linked PlayerData object
	 */
	public PlayerData get(UUID uuid) {
		return registered(uuid) ? PLAYER_DATA.get(uuid) : null;
	}

	/**
	 * Get the Map containing all instances of {@link PlayerData} by UUID.
	 * 
	 * @return {@link PlayerDataManager#PLAYER_DATA} Map
	 */
	public Map<UUID, PlayerData> getPlayerData() {
		return PLAYER_DATA;
	}

}

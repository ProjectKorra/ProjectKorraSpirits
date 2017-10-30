package com.projectkorra.spirits;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.projectkorra.spirits.api.PlayerData;
import com.projectkorra.spirits.configuration.ConfigManager;
import com.projectkorra.spirits.listener.PlayerListener;
import com.projectkorra.spirits.listener.SpiritEntityListener;
import com.projectkorra.spirits.manager.CommandManager;
import com.projectkorra.spirits.manager.PlayerDataManager;
import com.projectkorra.spirits.manager.SpiritEntityManager;

public class ProjectKorraSpirits extends JavaPlugin {

	public static ProjectKorraSpirits plugin;

	private CommandManager commandManager;
	private ConfigManager configManager;
	private PlayerDataManager playerDataManager;
	private SpiritEntityManager spiritEntityManager;
	private SpiritsMethods spiritsMethods;

	@Override
	public void onEnable() {
		plugin = this;
		this.configManager = new ConfigManager();
		this.commandManager = new CommandManager();
		this.commandManager.initializeCommands();
		this.playerDataManager = new PlayerDataManager();
		this.spiritEntityManager = new SpiritEntityManager();
		this.spiritEntityManager.init();
		this.spiritsMethods = new SpiritsMethods();

		getServer().getScheduler().scheduleSyncRepeatingTask(this, spiritEntityManager, 0, 1);

		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new SpiritEntityListener(), this);

		for (Player player : getServer().getOnlinePlayers()) {
			new PlayerData(player.getUniqueId());
		}
	}

	@Override
	public void onDisable() {
		spiritEntityManager.unregisterAll();

		for (Player player : getServer().getOnlinePlayers()) {
			PlayerData playerData = playerDataManager.get(player.getUniqueId());
			if (playerData != null) {
				playerData.cache();
			}
		}
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	public PlayerDataManager getPlayerDataManager() {
		return playerDataManager;
	}

	public SpiritEntityManager getSpiritEntityManager() {
		return spiritEntityManager;
	}

	public SpiritsMethods getMethods() {
		return spiritsMethods;
	}

}

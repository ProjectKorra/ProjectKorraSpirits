package com.projectkorra.spirits.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

	private Config defaultConfig;
	private Config languageConfig;

	public ConfigManager() {
		this.defaultConfig = new Config(new File("config.yml"), Config.Type.CONFIG);
		this.languageConfig = new Config(new File("language.yml"), Config.Type.LANGUAGE);
		init(Config.Type.CONFIG);
		init(Config.Type.LANGUAGE);
	}

	public void init(Config.Type type) {
		FileConfiguration config;
		if (type.equals(Config.Type.CONFIG)) {
			config = defaultConfig.get();

			config.addDefault("SpiritWorld.Name", "spiritworld");

			defaultConfig.save();
		} else if (type.equals(Config.Type.LANGUAGE)) {
			config = languageConfig.get();

			List<String> generalHelp = new ArrayList<>();
			generalHelp.add("&7>> &9/b spirits help &7- &3 General help");
			generalHelp.add("&7>> &9/b spirits command1 &7- &3 Description");
			generalHelp.add("&7>> &9/b spirits command2 &7- &3 Description");
			config.addDefault("Commands.GeneralHelp", generalHelp);
			config.addDefault("Commands.InsufficientPermission", "&7Insufficient permission...");
			config.addDefault("Commands.InsufficientArgs", "&7Not enough arguments...");
			config.addDefault("Commands.InvalidArgs", "&7Invalid arguments...");
			config.addDefault("Commands.MustBePlayer", "&7Only players can use this command...");

			languageConfig.save();
		}
	}

	public Config getConfig() {
		return defaultConfig;
	}

	public Config getLanguageConfig() {
		return languageConfig;
	}

}

package com.projectkorra.spirits.configuration;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.projectkorra.spirits.ProjectKorraSpirits;

public class Config {

	public static enum Type {
		CONFIG, LANGUAGE;
	}

	private File file;
	private FileConfiguration config;
	private Type type;

	public Config(File file, Type type) {
		this.file = new File(ProjectKorraSpirits.plugin.getDataFolder() + File.separator + file);
		this.config = YamlConfiguration.loadConfiguration(this.file);
		this.type = type;
		reload();
	}

	public void create() {
		if (!file.getParentFile().exists()) {
			try {
				file.getParentFile().mkdir();
				ProjectKorraSpirits.plugin.getLogger().info("Generating new directory for " + file.getName());
			}
			catch (Exception e) {
				ProjectKorraSpirits.plugin.getLogger().info("Failed to generate directory for " + file.getName());
				e.printStackTrace();
			}
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
				ProjectKorraSpirits.plugin.getLogger().info("Generating new " + file.getName());
			}
			catch (Exception e) {
				ProjectKorraSpirits.plugin.getLogger().info("Failed to generate " + file.getName());
				e.printStackTrace();
			}
		}
	}

	public FileConfiguration get() {
		return config;
	}

	public Type getType() {
		return type;
	}

	public void reload() {
		create();
		try {
			config.load(file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			config.options().copyDefaults(true);
			config.save(file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

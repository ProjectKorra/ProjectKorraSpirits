package com.projectkorra.spirits;

import org.bukkit.plugin.java.JavaPlugin;

public class ProjectKorraSpirits extends JavaPlugin {
	public static ProjectKorraSpirits plugin;

	@Override
	public void onEnable() {
		plugin = this;

		getLogger().info("ProjectKorraSpirits has been enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("ProjectKorraSpirits has been disabled!");
	}
}

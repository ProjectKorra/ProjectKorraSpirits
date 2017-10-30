package com.projectkorra.spirits;

import org.bukkit.World;

public class SpiritsMethods {

	public boolean isSpiritWorld(World world) {
		return world.getName().equalsIgnoreCase(ProjectKorraSpirits.plugin.getConfigManager().getConfig().get().getString("SpiritWorld.Name"));
	}

	public World getSpiritWorld() {
		return ProjectKorraSpirits.plugin.getServer().getWorld(ProjectKorraSpirits.plugin.getConfigManager().getConfig().get().getString("SpiritWorld.Name"));
	}

}

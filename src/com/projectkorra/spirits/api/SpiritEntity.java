package com.projectkorra.spirits.api;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.projectkorra.spirits.ProjectKorraSpirits;

public abstract class SpiritEntity implements ISpiritEntity {

	protected LivingEntity spirit;
	private Location spawnLocation;

	public SpiritEntity(Location spawnLocation) {
		// We do not want to setup an instance for placeholder entities
		if (spawnLocation == null) {
			return;
		}
		Entity entity = spawnLocation.getWorld().spawnEntity(spawnLocation, getEntityType());
		if (!(entity instanceof LivingEntity)) {
			// Developer has messed something up, cancel everything
			ProjectKorraSpirits.plugin.getLogger().severe("Uh oh! Something has gone wrong! Please check your " + getClass().getName() + " entity for any possible issues.");
			entity.remove();
			return;
		}
		entity.setCustomName(ChatColor.translateAlternateColorCodes('&', getDisplayName()));

		this.spirit = (LivingEntity) entity;
		this.spawnLocation = spawnLocation;
		this.onSpawn();

		ProjectKorraSpirits.plugin.getSpiritEntityManager().register(this);
	}

	public LivingEntity getEntity() {
		return spirit;
	}

	public Location getSpawnLocation() {
		return spawnLocation;
	}

}

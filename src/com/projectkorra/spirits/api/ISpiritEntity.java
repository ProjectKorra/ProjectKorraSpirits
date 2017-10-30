package com.projectkorra.spirits.api;

import java.util.Map;

import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public interface ISpiritEntity {

	/**
	 * Get the name identifier of the {@link SpiritEntity}
	 * 
	 * @return - The name used as an identifier for the {@link SpiritEntity}
	 */
	public String getName();

	/**
	 * Get the display name of the {@link SpiritEntity}
	 * 
	 * @return - The {@link SpiritEntity}'s display name
	 */
	public String getDisplayName();

	/**
	 * Get the {@link EntityType} of the {@link SpiritEntity}
	 * 
	 * @return {@link SpiritEntity}'s {@link EntityType}
	 */
	public EntityType getEntityType();

	/**
	 * Spawn the {@link SpiritEntity} at the specified {@link Location}
	 * 
	 * @param location - The location to spawn the {@link SpiritEntity}
	 */
	/* public void spawn(Location location); */

	/**
	 * Get all the droppable {@link ItemStack} instances, with {@link Map} key
	 * as the item, and value as the probability
	 * 
	 * @return {@link Map}
	 */
	public Map<ItemStack, Double> getDrops();

	/**
	 * Code to be executed upon the event of the {@link SpiritEntity}'s spawning
	 */
	public void onSpawn();

	/**
	 * Code to be executed upon the event of the {@link SpiritEntity}'s death
	 */
	public void onDeath(EntityDeathEvent event);

	/**
	 * Update the {@link SpiritEntity}. Code in this method will be run every
	 * tick.
	 */
	public void progress();

}

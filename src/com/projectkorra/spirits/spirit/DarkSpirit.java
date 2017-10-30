package com.projectkorra.spirits.spirit;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.projectkorra.spirits.api.SpiritEntity;

public class DarkSpirit extends SpiritEntity {

	public DarkSpirit(Location spawnLocation) {
		super(spawnLocation);
	}

	@Override
	public String getName() {
		return "DarkSpirit";
	}

	@Override
	public String getDisplayName() {
		return "&5&lDark Spirit";
	}

	@Override
	public EntityType getEntityType() {
		return EntityType.BAT;
	}

	@Override
	public Map<ItemStack, Double> getDrops() {
		return new HashMap<>();
	}

	@Override
	public void onSpawn() {
		spirit.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
		spirit.setSilent(true);
		spirit.setCustomNameVisible(true);
	}

	@Override
	public void onDeath(EntityDeathEvent event) {

	}

	@Override
	public void progress() {
		ParticleEffect.WITCH_MAGIC.display(spirit.getLocation(), 0.3F, 0.3F, 0.3F, 0.02F, 5);
		ParticleEffect.SMOKE.display(spirit.getLocation(), 0.3F, 0.3F, 0.3F, 0.02F, 25);
	}

}

package com.projectkorra.spirits.listener;

import java.util.UUID;

import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.projectkorra.spirits.ProjectKorraSpirits;
import com.projectkorra.spirits.api.SpiritEntity;
import com.projectkorra.spirits.manager.SpiritEntityManager;
import com.projectkorra.spirits.spirit.DarkSpirit;
import com.projectkorra.spirits.spirit.LightSpirit;

public class SpiritEntityListener implements Listener {

	@EventHandler
	public void onSpawn(CreatureSpawnEvent event) {
		if (ProjectKorraSpirits.plugin.getMethods().isSpiritWorld(event.getLocation().getWorld())) {
			if (event.getEntity() instanceof Monster) {
				if (!ProjectKorraSpirits.plugin.getSpiritEntityManager().isSpirit(event.getEntity().getUniqueId())) {
					event.setCancelled(true);
					new DarkSpirit(event.getLocation());
				}
			} else {
				if (!ProjectKorraSpirits.plugin.getSpiritEntityManager().isSpirit(event.getEntity().getUniqueId())) {
					event.setCancelled(true);
					new LightSpirit(event.getLocation());
				}
			}
		}
	}

	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		UUID uuid = event.getEntity().getUniqueId();
		SpiritEntityManager manager = ProjectKorraSpirits.plugin.getSpiritEntityManager();
		if (manager.isSpirit(uuid)) {
			SpiritEntity spirit = manager.getSpirit(uuid);
			manager.unregister(spirit);
			event.getDrops().clear();
			for (ItemStack item : spirit.getDrops().keySet()) {
				double chance = spirit.getDrops().get(item);
				if (chance >= Math.random()) {
					event.getDrops().add(item);
				}
			}
			spirit.onDeath(event);
		}
	}

}

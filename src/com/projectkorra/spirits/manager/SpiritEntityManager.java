package com.projectkorra.spirits.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.projectkorra.spirits.ProjectKorraSpirits;
import com.projectkorra.spirits.api.SpiritEntity;
import com.projectkorra.spirits.spirit.DarkSpirit;
import com.projectkorra.spirits.spirit.LightSpirit;

public class SpiritEntityManager implements Runnable {

	private final Set<SpiritEntity> INSTANCES = new HashSet<>();
	private final Map<UUID, SpiritEntity> INSTANCES_BY_ENTITY = new HashMap<>();
	private final Map<Class<? extends SpiritEntity>, Set<SpiritEntity>> INSTANCES_BY_CLASS = new HashMap<>();

	// Placeholder instances
	private final Map<String, SpiritEntity> ENTITIES_BY_NAME = new HashMap<>();
	private final Map<Class<? extends SpiritEntity>, SpiritEntity> ENTITIES_BY_CLASS = new HashMap<>();

	public SpiritEntityManager() {

	}

	public void init() {
		registerPlaceholderEntity(new DarkSpirit(null));
		registerPlaceholderEntity(new LightSpirit(null));
	}

	public void registerPlaceholderEntity(SpiritEntity entity) {
		ENTITIES_BY_NAME.put(entity.getName().toUpperCase(), entity);
		ENTITIES_BY_CLASS.put(entity.getClass(), entity);
		ProjectKorraSpirits.plugin.getLogger().info("Successfully registered the entity '" + entity.getName() + "'!");
	}

	/**
	 * Store the specified {@link SpiritEntity} Object in memory with the UUID
	 * key.
	 * 
	 * @param uuid - UUID being registered
	 * @param spirit - {@link SpiritEntity} instance being registered
	 */
	public void register(SpiritEntity spirit) {
		Class<? extends SpiritEntity> _class = spirit.getClass();
		UUID uuid = spirit.getEntity().getUniqueId();

		if (!INSTANCES_BY_CLASS.containsKey(_class)) {
			INSTANCES_BY_CLASS.put(_class, new HashSet<>());
		}

		INSTANCES_BY_ENTITY.put(uuid, spirit);
		INSTANCES_BY_CLASS.get(_class).add(spirit);
		INSTANCES.add(spirit);
	}

	/**
	 * Unload the specified UUID from memory.
	 * 
	 * @param uuid - UUID to unregister
	 */
	public void unregister(SpiritEntity spirit) {
		if (INSTANCES_BY_ENTITY.containsKey(spirit.getEntity().getUniqueId())) {
			INSTANCES_BY_ENTITY.remove(spirit.getEntity().getUniqueId());
		}
		if (INSTANCES_BY_CLASS.containsKey(spirit.getClass())) {
			INSTANCES_BY_CLASS.get(spirit.getClass()).remove(spirit);
		}
		INSTANCES.remove(spirit);
	}

	// Returns placeholder entity
	public SpiritEntity getSpirit(String name) {
		return name != null ? ENTITIES_BY_NAME.get(name.toUpperCase()) : null;
	}

	// Returns placeholder entity
	public SpiritEntity getSpirit(Class<? extends SpiritEntity> _class) {
		return _class != null && ENTITIES_BY_CLASS.containsKey(_class) ? ENTITIES_BY_CLASS.get(_class) : null;
	}

	// Returns placeholder entities
	public Collection<SpiritEntity> getSpirits() {
		return ENTITIES_BY_CLASS.values();
	}

	public boolean isSpirit(UUID uuid) {
		return INSTANCES_BY_ENTITY.containsKey(uuid);
	}

	// Returns actual instance
	public SpiritEntity getSpirit(UUID uuid) {
		if (isSpirit(uuid)) {
			return INSTANCES_BY_ENTITY.get(uuid);
		}
		return null;
	}

	//	/**
	//	 * Check if UUID has been registered.
	//	 * 
	//	 * @param uuid - UUID to be tested
	//	 * @return true if {@link SpiritEntityManager#INSTANCES} contains uuid
	//	 */
	//	public boolean registered(UUID uuid) {
	//		return INSTANCES.containsKey(uuid);
	//	}

	//	/**
	//	 * Get the {@link SpiritEntity} Object stored with the specified UUID as
	//	 * key.
	//	 * 
	//	 * @param uuid - {@link SpiritEntity} UUID
	//	 * @return The linked {@link SpiritEntity} Object
	//	 */
	//	public SpiritEntity get(UUID uuid) {
	//		return registered(uuid) ? INSTANCES.get(uuid) : null;
	//	}

	/**
	 * Run the {@link SpiritEntity#progress()} method for all registered
	 * {@link SpiritEntity}.
	 */
	public void progressAll() {
		for (SpiritEntity spirit : INSTANCES) {
			spirit.progress();
		}
	}

	/**
	 * Unregister every class which extends {@link SpiritEntity}.
	 */
	public void unregisterAll() {
		INSTANCES.clear();
		INSTANCES_BY_ENTITY.clear();
		INSTANCES_BY_CLASS.clear();
		ENTITIES_BY_NAME.clear();
		ENTITIES_BY_CLASS.clear();
	}

	@Override
	public void run() {
		progressAll();
	}

}

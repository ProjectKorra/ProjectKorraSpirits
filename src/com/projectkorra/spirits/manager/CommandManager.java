package com.projectkorra.spirits.manager;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

import com.projectkorra.spirits.ProjectKorraSpirits;
import com.projectkorra.spirits.command.HelpCommand;
import com.projectkorra.spirits.command.PKSCommand;
import com.projectkorra.spirits.command.SpiritWorldCommand;
import com.projectkorra.spirits.command.SpiritsCommand;
import com.projectkorra.spirits.command.SummonCommand;
import com.projectkorra.spirits.configuration.ConfigManager;

public class CommandManager {

	private final Map<Class<? extends PKSCommand>, Map<String, Object>> COMMAND_MESSAGES = new HashMap<>();
	private final Map<String, PKSCommand> INSTANCES = new HashMap<>();

	public CommandManager() {

	}

	public void initializeCommands() {
		new SpiritsCommand();

		new HelpCommand();
		new SpiritWorldCommand();
		new SummonCommand();
	}

	public void register(PKSCommand command) {
		INSTANCES.put(command.getName(), command);
	}

	public void unregister(String name) {
		if (registered(name)) {
			INSTANCES.remove(name);
		}
	}

	public void unregister(PKSCommand command) {
		unregister(command.getName());
	}

	public boolean registered(String name) {
		return INSTANCES.containsKey(name);
	}

	public PKSCommand get(String name) {
		return registered(name) ? INSTANCES.get(name) : null;
	}

	public void registerMessages(Class<? extends PKSCommand> commandClass) {
		COMMAND_MESSAGES.put(commandClass, new HashMap<>());
		ConfigManager configManager = ProjectKorraSpirits.plugin.getConfigManager();
		if (configManager == null) {
			return;
		}
		FileConfiguration lang = configManager.getLanguageConfig().get();
		for (String string : lang.getKeys(true)) {
			String line = string.replace(".", ";");
			String[] split = line.split(";");
			if (split.length < 3) {
				continue;
			} else if (!split[1].equals(commandClass.getName())) {
				continue;
			}
			COMMAND_MESSAGES.get(commandClass).put(split[2], lang.get(string));
		}

	}

	public Object getMessage(Class<? extends PKSCommand> commandClass, String key) {
		return COMMAND_MESSAGES.get(commandClass);
	}

	public Map<String, Object> getCommandMessages(Class<? extends PKSCommand> commandClass) {
		Map<String, Object> map = new HashMap<>();
		if (!COMMAND_MESSAGES.containsKey(commandClass)) {
			return map;
		}
		return COMMAND_MESSAGES.get(commandClass);
	}

	public Map<Class<? extends PKSCommand>, Map<String, Object>> getCommandMessages() {
		return COMMAND_MESSAGES;
	}

	public Map<String, PKSCommand> getInstances() {
		return INSTANCES;
	}

}

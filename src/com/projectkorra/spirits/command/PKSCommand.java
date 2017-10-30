package com.projectkorra.spirits.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.command.SubCommand;
import com.projectkorra.spirits.ProjectKorraSpirits;

public abstract class PKSCommand implements SubCommand {

	private final String name;
	private final String properUse;
	private final String description;
	private final String[] aliases;

	protected String insufficientPermission;
	protected String insufficientArgs;
	protected String invalidArgs;
	protected String mustBePlayer;

	public PKSCommand(String name, String properUse, String description, String[] aliases) {
		this.name = name;
		this.properUse = properUse;
		this.description = description;
		this.aliases = aliases;

		FileConfiguration lang = ProjectKorraSpirits.plugin.getConfigManager().getLanguageConfig().get();
		this.insufficientPermission = ChatColor.translateAlternateColorCodes('&', lang.getString("Commands.InsufficientPermission"));
		this.insufficientArgs = ChatColor.translateAlternateColorCodes('&', lang.getString("Commands.InsufficientArgs"));
		this.invalidArgs = ChatColor.translateAlternateColorCodes('&', lang.getString("Commands.InvalidArgs"));
		this.mustBePlayer = ChatColor.translateAlternateColorCodes('&', lang.getString("Commands.MustBePlayer"));

		ProjectKorraSpirits.plugin.getCommandManager().register(this);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getProperUse() {
		return properUse;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String[] getAliases() {
		return aliases;
	}

	public void help(CommandSender sender, boolean description) {
		GeneralMethods.sendBrandingMessage(sender, ChatColor.BLUE + "Usage: " + ChatColor.DARK_AQUA + properUse);
		if (description) {
			sender.sendMessage(ChatColor.AQUA + this.description);
		}
	}

	protected boolean hasPermission(CommandSender sender) {
		if (sender.hasPermission("spirits.command." + name)) {
			return true;
		} else {
			GeneralMethods.sendBrandingMessage(sender, insufficientPermission);
			return false;
		}
	}

	protected boolean hasPermission(CommandSender sender, String extra) {
		if (sender.hasPermission("spirits.command." + name + "." + extra)) {
			return true;
		} else {
			GeneralMethods.sendBrandingMessage(sender, insufficientPermission);
			return false;
		}
	}

	protected boolean correctLength(CommandSender sender, int size, int min, int max) {
		if (size < min || size > max) {
			help(sender, false);
			return false;
		} else {
			return true;
		}
	}

	protected boolean isPlayer(CommandSender sender) {
		if (sender instanceof Player) {
			return true;
		} else {
			GeneralMethods.sendBrandingMessage(sender, mustBePlayer);
			return false;
		}
	}

	protected boolean isNumeric(String id) {
		try {
			Integer.parseInt(id);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	protected List<String> getPage(List<String> entries, String title, int page, boolean sort) {
		List<String> strings = new ArrayList<String>();
		if (sort) {
			Collections.sort(entries);
		}

		if (page < 1) {
			page = 1;
		}
		if ((page * 8) - 8 >= entries.size()) {
			page = Math.round(entries.size() / 8) + 1;
			if (page < 1) {
				page = 1;
			}
		}
		strings.add(ChatColor.translateAlternateColorCodes('&', "&9ProjectKorra &3&lSpirits &8- [&7" + page + "&8/&7" + (int) Math.ceil((entries.size() + .0) / (8 + .0)) + "&8]"));
		strings.add(title);
		if (entries.size() > ((page * 8) - 8)) {
			for (int i = ((page * 8) - 8); i < entries.size(); i++) {
				if (entries.get(i) != null) {
					strings.add(ChatColor.DARK_AQUA + entries.get(i).toString());
				}
				if (i >= (page * 8) - 1) {
					break;
				}
			}
		}
		return strings;
	}

}

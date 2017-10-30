package com.projectkorra.spirits.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.projectkorra.spirits.ProjectKorraSpirits;

public class HelpCommand extends PKSCommand {

	public HelpCommand() {
		super("help", "/b spirits help", "Help command for Spirits", new String[] { "help", "h" });
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, args.size(), 0, 1)) {
			return;
		} else if (args.size() == 0) {
			List<String> strings = new ArrayList<>();
			for (PKSCommand command : ProjectKorraSpirits.plugin.getCommandManager().getInstances().values()) {
				if (!command.getName().equalsIgnoreCase("help") && sender.hasPermission("spirits.command." + command.getName())) {
					strings.add(command.getProperUse());
				}
			}
			Collections.sort(strings);
			Collections.reverse(strings);
			strings.add(ProjectKorraSpirits.plugin.getCommandManager().getInstances().get("help").getProperUse());
			Collections.reverse(strings);
			for (String message : getPage(strings, ChatColor.translateAlternateColorCodes('&', "&9Commands: &3<Required> [Optional]"), 1, false)) {
				sender.sendMessage(message);
			}
			return;
		}

		String arg = args.get(0).toLowerCase();
		if (isNumeric(arg)) {
			List<String> strings = new ArrayList<>();
			for (PKSCommand command : ProjectKorraSpirits.plugin.getCommandManager().getInstances().values()) {
				strings.add(command.getProperUse());
			}
			for (String message : getPage(strings, ChatColor.translateAlternateColorCodes('&', "&9Commands: &3<Required> [Optional]"), Integer.valueOf(arg), true)) {
				sender.sendMessage(message);
			}
		} else if (ProjectKorraSpirits.plugin.getCommandManager().getInstances().keySet().contains(arg.toLowerCase())) {
			ProjectKorraSpirits.plugin.getCommandManager().getInstances().get(arg).help(sender, true);
		}
	}

}

package com.projectkorra.spirits.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.projectkorra.projectkorra.command.PKCommand;
import com.projectkorra.spirits.ProjectKorraSpirits;

public class SpiritsCommand extends PKCommand {

	private List<String> generalHelp;

	public SpiritsCommand() {
		super("spirits", "/bending spirits", "Main command for spirits", new String[] { "spirits", "s" });

		this.generalHelp = ProjectKorraSpirits.plugin.getConfigManager().getLanguageConfig().get().getStringList("Commands.GeneralHelp");
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		// User has typed '/b spirits'
		if (args.size() == 0) {
			for (String line : generalHelp) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
			}
			return;
		}

		List<String> sendingArgs = args.subList(1, args.size());
		for (PKSCommand cmd : ProjectKorraSpirits.plugin.getCommandManager().getInstances().values()) {
			if (Arrays.asList(cmd.getAliases()).contains(args.get(0).toLowerCase())) {
				cmd.execute(sender, sendingArgs);
				return;
			}
		}

		for (String line : generalHelp) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
		}

		return;
	}

}

package com.projectkorra.spirits.command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.spirits.ProjectKorraSpirits;
import com.projectkorra.spirits.api.SpiritEntity;

public class SummonCommand extends PKSCommand {

	public SummonCommand() {
		super("summon", "/bending spirits summon <spirit> [world] [x] [y] [z] [yaw] [pitch]", "Summon the specified spirit", new String[] { "summon", "s" });
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, args.size(), 1, 1) || !isPlayer(sender)) {
			return;
		}

		Player player = (Player) sender;
		Location location = GeneralMethods.getTargetedLocation(player, 64);
		String name = args.get(0);
		SpiritEntity spirit = ProjectKorraSpirits.plugin.getSpiritEntityManager().getSpirit(name);
		if (spirit == null) {
			// TODO: Log error
			return;
		}
		Class<? extends SpiritEntity> spiritClass = spirit.getClass();
		try {
			Constructor<?> constructor = spiritClass.getConstructor(Location.class);
			constructor.newInstance(new Object[] { location });
		}
		catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}

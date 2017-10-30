package com.projectkorra.spirits.command;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.projectkorra.spirits.ProjectKorraSpirits;

public class SpiritWorldCommand extends PKSCommand {

	public SpiritWorldCommand() {
		super("spiritworld", "/bending spirits spiritworld <teleport/download>", "Base command for SpiritWorld", new String[] { "spiritworld", "sw" });
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, args.size(), 1, 1)) {
			return;
		}

		if (args.get(0).equalsIgnoreCase("teleport")) {
			if (!isPlayer(sender)) {
				return;
			}
			((Player) sender).teleport(ProjectKorraSpirits.plugin.getMethods().getSpiritWorld().getSpawnLocation());
			// TODO: Message player of teleportation
		} else if (args.get(0).equalsIgnoreCase("download")) {
			navigate("http://www.mediafire.com/download/aqtmhwvb8yvqclu/SmartSharePC.jar");
		}
		// /bending spirits spiritworld teleport
	}

	private void navigate(String url) {
		String downloadLink;
		try {
			downloadLink = fetchDownloadLink(getUrlSource(url));
			saveUrl(downloadLink);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveUrl(final String urlString) throws MalformedURLException, IOException {
		System.out.println("Downloading...");
		String filename = urlString.substring(urlString.lastIndexOf("/") + 1, urlString.lastIndexOf(".")) + urlString.substring(urlString.lastIndexOf("."), urlString.length());
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			in = new BufferedInputStream(new URL(urlString).openStream());
			fout = new FileOutputStream(filename);

			final byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
		}
		finally {
			if (in != null) {
				in.close();
			}
			if (fout != null) {
				fout.close();
			}
		}
		System.out.println("Success!");
	}

	private String getUrlSource(String urlString) throws UnsupportedEncodingException, IOException {
		System.out.println("Connecting...");
		URL url = new URL(urlString);
		URLConnection urlConnection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		String inputLine;
		String total = "";
		while ((inputLine = in.readLine()) != null) {
			total += inputLine;
		}
		in.close();
		return total;
	}

	private String fetchDownloadLink(String string) {
		System.out.println("Fetching download link");
		String regex = "(?=\\<)|(?<=\\>)";
		String[] data = string.split(regex);
		String found = "NOTFOUND";
		for (String dat : data) {
			if (dat.contains("DLP_mOnDownload(this)")) {
				found = dat;
				break;
			}
		}
		String wentthru = found.substring(found.indexOf("href=\"") + 6);
		wentthru = wentthru.substring(0, wentthru.indexOf("\""));
		return wentthru;
	}

}

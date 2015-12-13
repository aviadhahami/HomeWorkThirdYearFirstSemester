package server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigLoader {
	private final static String[] configKeys = { "port", "maxthreads", "defaultpage", "root" };

	public static ConfigObj load(String configPath) {

		ConfigObj config = new ConfigObj();

		try {
			String content = new String(Files.readAllBytes(Paths.get(configPath)));
			String[] configFile = content.toLowerCase().split("\n");

			// Parse the config file
			for (String str : configFile) {
				for (String key : configKeys) {
					if (str.indexOf(key) > -1) {
						switch (key) {
						case "port":
							config.setPort(praseIntValue(str));
							break;
						case "maxthreads":
							config.setMaxThreads(praseIntValue(str));
							break;
						case "defaultpage":
							config.setDefaultPage(parseValue(str));
							break;
						case "root":
							config.setDefaultRoot(parseValue(str));
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error!");
			e.printStackTrace();
		}
System.out.println(config.getMaxThreads());
		return config;
	}

	// Parsing int value from config file
	private static int praseIntValue(String str) {
		System.out.println(str);
		String val = str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\""));
		int parsedVal;
		try {
			parsedVal = Integer.parseInt(val);
		} catch (NumberFormatException e) {
			System.out.println("Invalid string to parse from config parser");
			parsedVal = 0;
		}

		return parsedVal;
	}

	private static String parseValue(String str) {
		return str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\""));
	}

}

package server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigLoader {

	public static ConfigObj load(String configPath) {

		ConfigObj config = new ConfigObj();

		try {
			String content = new String(Files.readAllBytes(Paths.get(configPath)));
			String[] configFile = content.toLowerCase().split("\n");
			for (String str : configFile) {

				if (str.indexOf("port") > -1) {
					config.setPort(getPort(str));
				} else if (str.indexOf("maxthreads") > -1) {

				} else if (str.indexOf("defaultpage") > -1) {

				} else if (str.indexOf("root") > -1) {

				}
			}
		} catch (IOException e) {
			System.out.println("Error!");
			e.printStackTrace();
		}

		return config;
	}

	private static int getPort(String str) {
		return Integer.parseInt(str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\"")));
	}

}

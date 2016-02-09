package config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import console.Console;

public class ServerConfigLoader {
	private final static String[] configKeys = { "port", "maxThreads", "defaultPage", "root", "socketTimeout",
			"maxDownloaders", "maxAnalyzers", "imageExtensions", "videoExtensions", "documentExtensions" };

	public static ServerConfigObj load(String configPath) {

		ServerConfigObj config = new ServerConfigObj();

		try {
			String content = new String(Files.readAllBytes(Paths.get(configPath)));
			String[] configFile = content.split("\n");

			// Parse the config file
			for (String str : configFile) {

				// Escape comments
				if (str.startsWith("#")) {
					continue;
				}

				for (String key : configKeys) {
					if (str.indexOf(key) > -1) {
						switch (key) {
						case "port":
							config.setPort(parseIntValue(str));
							break;
						case "maxThreads":
							config.setMaxThreads(parseIntValue(str));
							break;
						case "defaultPage":
							config.setDefaultPage(parseValue(str));
							break;
						case "root":
							// Verify root is a folder
							config.setDefaultRoot(parseValue(str));
							break;
						case "maxDownloaders":
							config.setMaxDownloaders(parseIntValue(str));
							break;
						case "maxAnalyzers":
							config.setMaxAnalyzer(parseIntValue(str));
							break;
						case "imageExtensions":
							config.setImageTypes(parseStringArr(str));
							break;
						case "videoExtensions":
							config.setVideoTypes(parseStringArr(str));
							break;
						case "documentExtensions":
							config.setDocumentTypes(parseStringArr(str));
							break;
						case "socketTimeout":
							config.setSocketTimeout(parseIntValue(str));
							break;
						}
					}
				}
			}

			boolean exceptionFlag = false;
			File root = new File(config.getRoot());
			File defaultPage = new File(config.getRoot() + config.getDefaultPage());
			if (!root.exists()) {
				errorLogger("Root does not exist", root.getPath());
				exceptionFlag = true;
			} else if (!root.isDirectory()) {
				errorLogger("Root is not a directory", root.getPath());
				exceptionFlag = true;
			} else if (!defaultPage.exists()) {
				errorLogger("Default page does not exist", defaultPage.getPath());
				exceptionFlag = true;
			} else if (defaultPage.isDirectory()) {
				errorLogger("Default page is a directory", defaultPage.getPath());
				exceptionFlag = true;
			}

			if (exceptionFlag) {
				Console.logErr("The server will now terminate");
				System.exit(1);
			}

		} catch (IOException e) {
			Console.logErr("We hit IO exception loading the config. Server will now terminate");
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			Console.logErr("We hit general exception loading the config. Server will now terminate");
			System.exit(1);
		}

		return config;
	}

	private static String[] parseStringArr(String arrayString) {
		return arrayString.substring(arrayString.indexOf("=") + 1).replace("\r", "").replace(" ", "").split(",");
	}

	private static void errorLogger(String reason, String path) {
		Console.logErr(reason);
		Console.logErr("Path : " + path);
	}

	// Parsing int value from config file
	private static int parseIntValue(String str) {
		String val = str.substring(str.indexOf("=") + 1).replace("\r", "");
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
		return str.substring(str.indexOf("=") + 1).replace("\r", "");
	}

}

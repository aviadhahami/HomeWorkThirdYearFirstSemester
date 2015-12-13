package server;

/**
 *
 * @author aviadh
 */
public class Main {

	public static void main(String[] args) {

		// TODO: load config here and pass it to server

		// Give option to run via terminals. #DevOps
		String defaultConfigPath = "config/config.ini";
		String configPath = args.length > 0 ? args[0] : defaultConfigPath;
		Server s = new Server(ServerConfigLoader.load(configPath));
		s.listen();
	}
}

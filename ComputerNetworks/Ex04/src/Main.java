import java.io.File;

/**
 *
 * @author aviadh
 */
public class Main {

	public static void main(String[] args) {

		// Give option to run via terminals. #DevOps
		String defaultConfigPath = System.getProperty("user.dir") + File.separator + "config" + File.separator + "config.ini";
		String configPath = args.length > 0 ? args[0] : defaultConfigPath;
		Server s = new Server(ServerConfigLoader.load(configPath));
		s.listen();
	}
}


import java.io.File;

import config.ServerConfigLoader;
import server.Server;

/**
 *
 * @author aviadh
 */
public class Main {

	public static void main(String[] args) {

		// Give option to run via terminals. #DevOps

		String defaultConfigPath = System.getProperty("user.dir") 
				+ File.separator 
				+ ".."  
				+File.separator 
				+"config" 
				+ File.separator
				+"config.ini";

		String configPath = args.length > 0 ? args[0] : defaultConfigPath;
		ServerConfigLoader.load(configPath);
		Server s = new Server();
		s.listen();
	}
}

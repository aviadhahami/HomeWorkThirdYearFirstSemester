
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

		//TODO : Change route for running via CLI
		String defaultConfigPath = System.getProperty("user.dir") 
				+ File.separator 
				+ ".."  
				+File.separator 
				+"config" 
				+ File.separator
				+"config.ini";

		String configPath = args.length > 0 ? args[0] : defaultConfigPath;
		Server s = new Server(ServerConfigLoader.load(configPath));
		s.listen();
	}
}

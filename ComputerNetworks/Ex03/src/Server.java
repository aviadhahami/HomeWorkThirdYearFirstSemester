

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author aviadh
 */
public class Server {

	private final int port;
	ThreadPoolManager poolManager;

	public Server(ServerConfigObj config) {
		this.port = config.getPort();

		poolManager = new ThreadPoolManager(config.getMaxThreads());

		// Initialize global routes object
		Routes.initRoutes(config.getRoot(), config.getDefaultPage());
	}

	public void listen() {
		Console.log("Listening on " + port);
		try (ServerSocket server = new ServerSocket(port)) {
			Socket connection;
			boolean listenFlag = true;

			// Listen to incoming connections
			while (listenFlag) {
				connection = server.accept();

				// System.out.println("Client connected, generating thread");
				ConnectionHandler connectionHandler = new ConnectionHandler(connection);

				// Executing via pool manager
				poolManager.submitTask(connectionHandler);
			}
		} catch (Exception e) {
			Console.logErr("Server couldn't start because " + e.getMessage());
			Console.logErr("please try again later");
			System.exit(1);
		}
	}

}

package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author aviadh
 */
public class Server {

	private final int port;
	ExecutorService threadPoolExecutor;

	public Server(ConfigObj config) {
		this.port = config.getPort();
		threadPoolExecutor = Executors.newFixedThreadPool(config.getMaxThreads());
	}

	public void listen() {
		ConsoleNotifier.print("Listening on " + port);
		try (ServerSocket server = new ServerSocket(port)) {
			Socket connection;
			boolean listenFlag = true;

			// Listen to incoming connections
			while (listenFlag) {
				connection = server.accept();
				
				// System.out.println("Client connected, generating thread");
				ConnectionHandler connectionHandler = new ConnectionHandler(connection);

				// Executing via pool manager
				threadPoolExecutor.execute(connectionHandler);
			}
		} catch (Exception e) {
			System.out.println("Server.listen() exception" + e);
			System.exit(1);
		}
	}

}

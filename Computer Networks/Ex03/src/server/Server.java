package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author aviadh
 */
public class Server {

	private final int port;
	private Queue<Socket> que;
	ExecutorService threadPoolExecutor;

	public Server(int port, int poolSize) {
		this.port = port;
		threadPoolExecutor = Executors.newFixedThreadPool(poolSize);
	}

	public void listen() {
		NotifyConsole("Listening on " + port);
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

	// Display to console
	private void NotifyConsole(String msg) {
		System.out.println(msg);
	}

}

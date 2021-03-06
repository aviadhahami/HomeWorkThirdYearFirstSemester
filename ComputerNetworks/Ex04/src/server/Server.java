package server;

import java.net.ServerSocket;
import java.net.Socket;

import config.ServerConfigObj;
import console.Console;
import routes.Routes;
import threadPool.ThreadPoolManager;

/**
 *
 * @author aviadh
 */
public class Server {

	private final int port;
	ThreadPoolManager poolManager;
	private int socketTimeout;


	public Server() {
		this.port = ServerConfigObj.getPort();
		this.socketTimeout = ServerConfigObj.getSocketTimeout();
		poolManager = new ThreadPoolManager(ServerConfigObj.getMaxThreads());

		// Initialize global routes object
		Routes.initRoutes(ServerConfigObj.getRoot(), ServerConfigObj.getDefaultPage());
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
				ConnectionHandler connectionHandler = new ConnectionHandler(connection,this.socketTimeout);

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

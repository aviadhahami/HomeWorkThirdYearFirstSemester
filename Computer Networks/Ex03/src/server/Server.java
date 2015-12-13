package server;

import java.net.BindException;
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
	private ServerConfigObj configPropertiesObject;

	public Server(ServerConfigObj config) {
		this.configPropertiesObject = config;
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
				ConnectionHandler connectionHandler = new ConnectionHandler(connection,
						configPropertiesObject.getRoot(), configPropertiesObject.getDefaultPage());

				// Executing via pool manager
				threadPoolExecutor.execute(connectionHandler);
			}
		} catch (Exception e) {
			ConsoleNotifier.printErr("Server couldn't start because " + e.getMessage());
			ConsoleNotifier.printErr("please try again later");
			System.exit(1);
		}
	}

}

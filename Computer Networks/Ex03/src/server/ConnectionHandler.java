package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aviadh
 */
public class ConnectionHandler extends Thread {

	private final Socket socket;

	public ConnectionHandler(Socket connection) {
		this.socket = connection;
	}

	@Override
	public void run() {
		try {
			// Init readers and writers
			OutputStream out = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(out, true);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			String line;

			// Text feed
			while ((line = reader.readLine()) != null) {
				// Output into console for server side
				NotifyConsole(line);
			}
			closeConnection();
		} catch (IOException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	// Wrapper to close the connection
	private void closeConnection() {
		try {
			this.socket.close();
			NotifyConsole("Client disconnected, killing thread id " + this.getId());
		} catch (Exception e) {
			System.err.println("Error closing socket" + e);
		}
	}

	// Display to console
	private void NotifyConsole(String msg) {
		System.out.println(msg);
	}
}

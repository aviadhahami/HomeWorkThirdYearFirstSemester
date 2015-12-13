package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author aviadh
 */
public class ConnectionHandler extends Thread {

	private final Socket socket;
	private String homePage;
	private String root;
	private HttpHeaderParser headerParser = new HttpHeaderParser();

	public ConnectionHandler(Socket connection, String root, String homePage) {
		this.socket = connection;
		this.root = root;
		this.homePage = homePage;
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

			System.out.println(this.getName());
			// Input feed
			while ((line = reader.readLine()) != null) {
				// Output into console for server side
				if (line.length() == 0) {
					break;
				}
				sb.append(line + '\n');

			}
			
			pw.println(headerParser.parseForResponse(sb.toString()));
			sb.setLength(0);
			
			
			// Close connection
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

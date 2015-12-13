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
			String res = "";

			// Input feed
			line = reader.readLine();
			sb.append(line + '\n');
			if (line.startsWith("POST")) {
				int contentLength = 0;
				// Accept POST request
				while ((line = reader.readLine()) != null) {
					sb.append(line + '\n');
					if (line.startsWith("Content-Length:")) {
						contentLength = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
					}
					if (line.length() == 0) {
						break;
					}
				}

				// Get the request post data
				for (int i = 0; i < contentLength; i++) {
					sb.append((char) reader.read());
				}
				res = headerParser.parsePOST(sb.toString());
			} else if (line.startsWith("GET")) {

				// Accept GET request
				while ((line = reader.readLine()) != null) {
					sb.append(line + '\n');
					if (line.length() == 0) {
						break;
					}
				}
				res = headerParser.parseGET(sb.toString());
			}

			pw.println(res);
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
			Console.printErr("Client disconnected, killing thread id " + this.getId());
		} catch (Exception e) {
			System.err.println("Error closing socket" + e);
		}
	}
}

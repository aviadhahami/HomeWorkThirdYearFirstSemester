
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author aviadh
 */
public class ConnectionHandler extends Thread {

	private final Socket socket;
	private Client client = new Client();

	public ConnectionHandler(Socket connection) {
		this.socket = connection;
	}

	@Override
	public void run() {
		try {
			// Init readers and writers
			OutputStream out = socket.getOutputStream();
			// PrintWriter pw = new PrintWriter(out, true);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			String line;

			// Input feed
			line = reader.readLine();

			// Check request type header validity
			String reqType;
			boolean validReqType = false;
			for (HTTPRequestTypesEnum type : HTTPRequestTypesEnum.values()) {
				reqType = type.toString();
				if (line.startsWith(reqType)) {
					validReqType = true;
				}
			}

			// If not proper HTTP header we don't even try.
			if (!validReqType) {
				out.write(ResponseHandler.buildResponse(null, null));
				closeConnection();
			}

			// If we reached this - means the request type is viable
			HTTPRequest req = new HTTPRequest();
			String[] reqHeader = line.split(" ");
			if (reqHeader.length == 3) {
				// Means we have specific resource requested
				req.setRequestType(reqHeader[0]);
				req.setRequestedResource(reqHeader[1]);
				req.setHTTPVersion(reqHeader[2]);
			} else {
				req.setRequestType(reqHeader[0]);
				req.setRequestedResource("");
				req.setHTTPVersion(reqHeader[1]);
			}

			while ((line = reader.readLine()) != null) {

				// If we hit an empty line then we received all the header
				if (line.length() == 0) {
					break;
				}
				String[] parsedInputLine = line.toLowerCase().replace(" ", "").split(":");
				req.setGenericHeaders(parsedInputLine[0], parsedInputLine[1]);
			}

			// Try to read request body according to accepted content length
			// value
			int contentLength;
			try {
				contentLength = Integer.parseInt(req.getGenericHeaders("content-length"));
			} catch (NumberFormatException e) {
				contentLength = 0;
			}

			// Get the request body as single characters
			for (int i = 0; i < contentLength; i++) {
				sb.append((char) reader.read());
			}
			req.setRequestBody(sb.toString());

			if (req.getGenericHeaders("connection") == "keep-alive") {
				// TODO: implement this
			}

			// Update permission for the client
			updateClient(req.getGenericHeaders("user-agent"), req.getGenericHeaders("cookie"));

			// Spill request to console
			Console.log(req.toString());

			out.write(ResponseHandler.buildResponse(req, client));

			closeConnection();

		} catch (IOException e) {
			Console.logErr(e.getMessage());
		} catch (Exception e) {
			Console.logErr(e.getMessage());
			e.printStackTrace();
		}
	}

	private void updateClient(String UA, String cookie) {
		this.client.UA = UA;

		// FIXME: implement better security methodology

		// Search the "level" cookie to extract user permission level
		String userLevel;
		try {
			String regex = "(.*)level=([0-9]*)(.*)";
			userLevel = cookie.replaceAll(regex, "$2");
		} catch (NullPointerException e) {
			userLevel = "0";
		}
		int level = 0;
		try {
			level = Integer.parseInt(userLevel);
		} catch (Exception e) {
			Console.logErr("Couldn't parse user level");
		}
		this.client.permissionLevel = level;
	}

	// Wrapper to close the connection
	private void closeConnection() {
		try {
			this.socket.close();
			Console.logErr("Client disconnected, killing thread id " + this.getId());
		} catch (Exception e) {
			System.err.println("Error closing socket" + e);
		}
	}
}

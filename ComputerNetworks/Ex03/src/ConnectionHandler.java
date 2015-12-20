
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
	private int timeoutThreshhold; // FIXME : implement
	OutputStream out;
	HTTPResponse res;
	HTTPRequest req;

	public ConnectionHandler(Socket connection, int timout) {
		this.socket = connection;
		this.timeoutThreshhold = timout;
	}

	@Override
	public void run() {
		try {
			// Init readers and writers
			out = socket.getOutputStream();
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
				res = ResponseHandler.buildResponseByCode(400);
				out.write(res.generateBytes());
				closeConnection();
			}

			// If we reached this - means the request type is viable
			this.req = new HTTPRequest();
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
				if (parsedInputLine.length > 0) {
					req.setGenericHeaders(parsedInputLine[0], parsedInputLine.length > 1 ? parsedInputLine[1] : "");
				}

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

			res = ResponseHandler.buildResponse(req, client);
		} catch (Exception e) {
			res.setStatus("500");// "HTTP/1.1 500 Internal Server
									// Error".getBytes();
			Console.logErr(e.getMessage());
			e.printStackTrace();
		}

		try {
			if ("yes".equals(req.getGenericHeaders("chunked"))) {
				// FIXME: Write as chunks
				res.fields.remove("Content-Length"); // Remove content length
														// header
				res.fields.put("Transfer-Encoding", "chunked");

				// Send header to client
				out.write(res.headerToString().getBytes());
				byte[] resBody = res.getBody();
				int bufferSize = 1024 * 2;

				// index for body
				int bodyIndex = 0;
				int spanToCopy = 0;

				// Byte buffer
				byte[] bb;

				// Loop over chunks
				while (bodyIndex < resBody.length) {

					// Copy from main to sub
					spanToCopy = Math.min(resBody.length - bodyIndex, bufferSize) < 0 ? 0
							: Math.min(resBody.length - bodyIndex, bufferSize);
					bb = new byte[spanToCopy];

					// Copy the bytes
					System.arraycopy(resBody, bodyIndex, bb, 0, bb.length);

					// Update index
					bodyIndex += bb.length;
					Console.log("Chunk length" + bb.length);

					// Out the size
					Console.log(Integer.toHexString(17));
					out.write((Integer.toHexString(bb.length) + "\r\n").getBytes());
					// Down a line
					// out.write("\n".getBytes());
					// Out the bytes
					out.write(bb);
					// Down a line
					out.write("\r\n".getBytes());
					// Clear a line
					// out.write("\n".getBytes());
				}
				out.write("0 \r\n".getBytes());
				// Down a line
				 out.write("\r\n".getBytes());
				// // Down a line
				// out.write("\r\n".getBytes());

			} else {
				// Write as chunks
				out.write(res.generateBytes());
			}

		} catch (IOException e) {
			Console.logErr(e.getMessage());
			e.printStackTrace();
		}

		closeConnection();
	}

	private void updateClient(String UA, String cookie) {
		this.client.UA = UA;

		// TODO: implement better security methodology

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

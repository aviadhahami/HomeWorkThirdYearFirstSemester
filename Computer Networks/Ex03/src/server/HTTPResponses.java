/**
 * 
 */
package server;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * <h1>Description:</h1> Implements a private hash where you send an HTTP code
 * and receive the proper header message
 * 
 * @author aviadh
 *
 */

public class HTTPResponses {

	// Private members
	private static final Map<Integer, String> HTTPcodesHash;

	// Final static strings
	private static final String NO_SUCH_CODE = "No such HTTP code";
	private static final String NO_SUCH_CONTENT_TYPE = "No such content type";
	private static final String DATE_HEADER = "Date: " + new Date().toString();
	private static final String SERVER_HEADER = "Server: Badly implemented/1.0 (Ubuntu)";
	private static final String CONTENT_LENGTH = "Content-Length: ";
	private static final String CONTENT_TYPE = "Content-Type: text/html";
	private static final String ASSETST = "assets/";

	// Populate the hash
	static {
		HTTPcodesHash = new HashMap<Integer, String>();
		HTTPcodesHash.put(200, "HTTP/1.1 200 OK");
		HTTPcodesHash.put(404, "HTTP/1.1 404 Not Found");
		HTTPcodesHash.put(400, "HTTP/1.1 400 Bad Request");
		HTTPcodesHash.put(500, "HTTP/1.1 500 Internal Server Error");
		HTTPcodesHash.put(501, "HTTP/1.1 501 Not Implemented");

	}

	// Public getter
	public static String getResponseCodeHeaderByCode(int code) {
		StringBuilder res = new StringBuilder();
		res.append(HTTPcodesHash.get(code) + "\n");
		res.append(DATE_HEADER + "\n");
		res.append(SERVER_HEADER + "\n");
		switch (code) {
		case 500:
			try {
				String content = new String(Files.readAllBytes(Paths.get(ASSETST + "500.html")));
				res.append(CONTENT_LENGTH + content.length() + "\n");
				res.append(CONTENT_TYPE + "\n");
				res.append("\n" + content + "\n");
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		}
		return res == null ? NO_SUCH_CODE : res.toString();
	}

	// FIXME : URGENT
	public static String getContentTypeHeaderByType(String cType) {
		String res = NO_SUCH_CONTENT_TYPE;
		switch (cType) {
		case "html":
		case "text":
			res = CONTENT_TYPE + "text/html";
			break;
		case "image":
			res = CONTENT_TYPE + "image";
			break;
		case "icon":
			res = CONTENT_TYPE + "icon";
			break;
		}
		return res;
	}
}
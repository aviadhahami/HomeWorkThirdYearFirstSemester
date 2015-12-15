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

public class ResponseHandler {

	// Private members
	private static final Map<Integer, String> HTTPcodesHash;

	// Final static strings
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
		HTTPResponse res = new HTTPResponse();
		res.fields.put(HTTPcodesHash.get(code), "");
		res.fields.put("Date", new Date().toString());
		res.fields.put("Server", "Badly implemented/1.0 (Ubuntu)");
		String content = "";
		try {

			switch (code) {
			case 401: {
				// Unauthorized
				content = new String(Files.readAllBytes(Paths.get(ASSETST + "401.html")));
				break;
			}
			case 400: {
				content = new String(Files.readAllBytes(Paths.get(ASSETST + "400.html")));
				break;
			}
			default:
			case 500: {
				content = new String(Files.readAllBytes(Paths.get(ASSETST + "500.html")));
				break;
			}
			}
		} catch (Exception e) {
			// No html then :\
		}
		res.fields.put("Content-Length", Integer.toString(content.length()));
		res.fields.put("Content-Type", "text/html");
		res.fields.put("\n" + content, "");
		return res.toString();
	}

	public static String parseGET(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String parsePOST(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String buildResponse(HTTPRequest req) {
		HTTPResponse res = new HTTPResponse();
		String reqType = req.getRequestType();
		if (reqType == "GET") {

			// TODO : process
		} else if (reqType == "POST") {

			// TODO : process
		} else {

		}
		return res.toString();
	}
}
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
	private static final String ASSETS = Routes.getRoot() + "assets/";

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
	public static String getResponseHeaderByCode(int code) {

		String header = HTTPcodesHash.get(code);

		// If the code is null return server error
		return header.length() == 0 ? HTTPcodesHash.get(500) : header;
	}

	public static String buildResponse(HTTPRequest req, Client client) {
		HTTPResponse res = new HTTPResponse();
		res.fields.put("Date", new Date().toString());
		res.fields.put("Server", "Badly implemented/1.0 (Ubuntu)");
		Console.log(Routes.testRouteAccessibility(req.getRequestedResource(), client.permissionLevel));
		String reqType = req.getRequestType();
		if (reqType == "GET") {

			// TODO: implement
		} else if (reqType == "POST") {

			// TODO : process
		} else {

			// Else is server error
			res.setStatus(getResponseHeaderByCode(500));
			res.setBody(getHTMLErrorAssetsByCode(500));
			res.fields.put("Content-Length", Integer.toString(res.getBody().length()));
			res.fields.put("Content-Type", "text/html");
		}
		return res.toString();
	}

	private static String getHTMLErrorAssetsByCode(int code) {
		String content = null;
		try {
			content = new String(Files.readAllBytes(Paths.get(ASSETS + code + ".html")));
		} catch (Exception e) {
			Console.log("Couldn't load asset for " + code + ".html");
			Console.logErr(e.getMessage());
			e.printStackTrace();
		}
		return content;
	}
}
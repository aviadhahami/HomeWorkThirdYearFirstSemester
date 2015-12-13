/**
 * 
 */
package server;

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
	private static final String CONTENT_TYPE = "content-type: ";

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
		String res = HTTPcodesHash.get(code);
		return res == null ? NO_SUCH_CODE: res;
	}

	
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
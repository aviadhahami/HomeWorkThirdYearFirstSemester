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

	private static final Map<Integer, String> HTTPcodesHash;
	private static final String NO_SUCH_CODE = "No such HTTP code";

	// Populate the hash
	static {
		HTTPcodesHash = new HashMap<Integer, String>();
		HTTPcodesHash.put(200, "200 OK");
		HTTPcodesHash.put(404, "404 Not Found");
		HTTPcodesHash.put(400, "400 Bad Request");
		HTTPcodesHash.put(500, "500 Internal Server Error");
		HTTPcodesHash.put(501, "501 Not Implemented");

	}

	// Public getter
	public static String getResponse(int code) {
		String res = HTTPcodesHash.get(code);
		return res == null ? NO_SUCH_CODE : res;
	}
}
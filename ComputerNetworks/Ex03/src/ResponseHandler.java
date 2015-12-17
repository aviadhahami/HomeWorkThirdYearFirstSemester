
/**
 * 
 */

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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
		HTTPcodesHash.put(403, "HTTP/1.1 403 Forbidden");
		HTTPcodesHash.put(400, "HTTP/1.1 400 Bad Request");
		HTTPcodesHash.put(500, "HTTP/1.1 500 Internal Server Error");
		HTTPcodesHash.put(501, "HTTP/1.1 501 Not Implemented");
	}

	// Public getter

	// FIXME: Verify this
	public static String getResponseHeaderByCode(int code) {

		String header = HTTPcodesHash.get(code);

		// If the status code is null return server error
		return header.length() == 0 ? HTTPcodesHash.get(501) : header;
	}

	public static String buildResponse(HTTPRequest req, Client client) {
		HTTPResponse res = new HTTPResponse();
		res.fields.put("Date", new Date().toString());
		res.fields.put("Server", "Badly implemented/1.0 (Ubuntu)");
		if (req == null) {
			// Then it's a bad request already
			res.setStatus(getResponseHeaderByCode(400));
			res.setBody(getHTMLErrorAssetsByCode(400));
			res.fields.put("Content-Length", Integer.toString(res.getBody().length()));
			res.fields.put("Content-Type", ContentTypeDictionary.getContentTypeByExt("html"));
		} else {

			try {

				// Access frequently accessed variables once
				String reqType = req.getRequestType();
				String requestedResource = PathUtils.toFullPath(req.getRequestedResource());

				// Verify security
				if (!Routes.testRouteAccessibility(requestedResource, client.permissionLevel)) {
					res.setStatus(getResponseHeaderByCode(403));
					res.setBody(getHTMLErrorAssetsByCode(403));
					res.fields.put("Content-Length", Integer.toString(res.getBody().length()));
					res.fields.put("Content-Type", ContentTypeDictionary.getContentTypeByExt("html"));
				} else if (reqType.equals("GET")) {

					// TODO: Isolate params from req
					// TODO: Verify proper path
					// TODO: Look for resource
					// TODO: Send params to resource
					// TODO: Respond with resource
					String content = new String(Files.readAllBytes(Paths.get(requestedResource)));
					String ext = requestedResource.replaceAll("^.*\\.(.*)$", "$1");
					res.setStatus(getResponseHeaderByCode(200));
					res.setBody(content);
					res.fields.put("Content-Length", Integer.toString(res.getBody().length()));
					res.fields.put("Content-Type", ContentTypeDictionary.getContentTypeByExt(ext));

				} else if (reqType.equals("POST")) {

					// TODO: Verify proper path
					// TODO: Look for resource
					// TODO: Send body to resource
					// TODO: Respond from resource
				} else if (reqType.equals("HEAD")) {
					// TODO: Implement
				} else if (reqType.equals("TRACE")) {
					res.setStatus(getResponseHeaderByCode(200));
					res.setBody(req.toString());
					res.fields.put("Content-Length", Integer.toString(res.getBody().length()));
					res.fields.put("Content-Type", ContentTypeDictionary.getContentTypeByExt(null));

				} else {

					// Else means we haven't implemented this
					res.setStatus(getResponseHeaderByCode(501));
					res.setBody(getHTMLErrorAssetsByCode(501));
					res.fields.put("Content-Length", Integer.toString(res.getBody().length()));
					res.fields.put("Content-Type", ContentTypeDictionary.getContentTypeByExt("html"));
				}
			} catch (NoSuchFileException e) {
				res.setStatus(getResponseHeaderByCode(404));
				res.setBody(getHTMLErrorAssetsByCode(404));
				res.fields.put("Content-Length", Integer.toString(res.getBody().length()));
				res.fields.put("Content-Type", ContentTypeDictionary.getContentTypeByExt("html"));

			} catch (Exception e) {
				Console.logErr(e.getMessage());
				e.printStackTrace();
				// Means there was an error generating response
				// So we build server error
				res.setStatus(getResponseHeaderByCode(500));
				res.setBody(getHTMLErrorAssetsByCode(500));
				res.fields.put("Content-Length", Integer.toString(res.getBody().length()));
				res.fields.put("Content-Type", ContentTypeDictionary.getContentTypeByExt("html"));
			}

		}

		return res.toString();

	}

	private static String getHTMLErrorAssetsByCode(int code) {
		String content = null;
		try {
			content = new String(Files.readAllBytes(Paths.get(ASSETS + code + ".html")));
		} catch (Exception e) {
			Console.log("Couldn't load asset for " + code + ".html");
			Console.logErr("Reason : " + e.getMessage());
			content = "";
		}
		return content;
	}
}
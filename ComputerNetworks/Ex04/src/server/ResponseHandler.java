package server;

/**
 * 
 */

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import client.Client;
import console.Console;
import httpObjects.HTTPRequest;
import httpObjects.HTTPResponse;
import interfaces.RouteController;
import routes.Routes;
import utils.ContentTypeDictionary;
import utils.PathUtils;

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
	private static final String CONTENT_LENGTH = "Content-Length";
	private static final String CONTENT_TYPE = "Content-Type";

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

	public static String getResponseHeaderByCode(int code) {

		String header = HTTPcodesHash.get(code);

		// If the status code is null return server error
		return header.length() == 0 ? HTTPcodesHash.get(501) : header;
	}

	public static HTTPResponse buildResponse(HTTPRequest req, Client client) {
		HTTPResponse res = new HTTPResponse();
		res.fields.put("Date", new Date().toString());
		res.fields.put("Server", "Computer Networks/1.0 - (Ubuntu)");

		// If somehow the request object is still null, server error
		if (req == null) {
			return buildResponseByCode(500);
		}
		try {

			// Access frequently accessed variables once
			String reqType = req.getRequestType();
			URI requestedResource = PathUtils.toFullPath(req.getRequestedResource());

			// Verify security
			if (!Routes.testRouteAccessibility(requestedResource.getPath(), client.permissionLevel)) {
				res.setStatus(getResponseHeaderByCode(403));
				res.setBody(getHTMLErrorAssetsByCode(403));
				res.fields.put(CONTENT_LENGTH, Integer.toString(res.getBodySize()));
				res.fields.put(CONTENT_TYPE, ContentTypeDictionary.getContentTypeByExt("html"));
			} else if (reqType.equals("GET")) {
				RouteController controller = Routes.getController(requestedResource.getPath());
				byte[] content;
				String contentType;

				if (controller != null) {
					content = controller.GET(req, res);
					contentType = controller.contentTypeByMethod("GET");
				} else {
					content = Files.readAllBytes(Paths.get(Routes.getRoot() + requestedResource.getPath()));
					contentType = requestedResource.getPath().replaceAll("^.*\\.(.*)$", "$1");
					res.setStatus(getResponseHeaderByCode(200));
				}
				res.setBody(content);
				res.fields.put(CONTENT_LENGTH, Integer.toString(res.getBodySize()));
				res.fields.put(CONTENT_TYPE, ContentTypeDictionary.getContentTypeByExt(contentType));

			} else if (reqType.equals("POST")) {
				RouteController controller = Routes.getController(requestedResource.getPath());
				String contentType = "";
				byte[] content = null;
				if (controller != null) {
					content = controller.POST(req, res);
					contentType = controller.contentTypeByMethod("POST");
				} else {
					content = Files.readAllBytes(Paths.get(requestedResource.getPath()));
					contentType = requestedResource.getPath().replaceAll("^.*\\.(.*)$", "$1");
					res.setStatus(getResponseHeaderByCode(200));
				}
				res.setBody(content);

				res.fields.put(CONTENT_LENGTH, Integer.toString(res.getBodySize()));
				res.fields.put(CONTENT_TYPE, ContentTypeDictionary.getContentTypeByExt(contentType));

			} else if (reqType.equals("HEAD")) {
				byte[] content = Files.readAllBytes(Paths.get(requestedResource));
				String ext = requestedResource.getPath().replaceAll("^.*\\.(.*)$", "$1");
				res.setStatus(getResponseHeaderByCode(200));
				res.setBody(content);
				res.fields.put(CONTENT_LENGTH, Integer.toString(res.getBodySize()));
				res.fields.put(CONTENT_TYPE, ContentTypeDictionary.getContentTypeByExt(ext));
				res.setBody(new byte[0]);

			} else if (reqType.equals("TRACE")) {
				res.setStatus(getResponseHeaderByCode(200));
				res.setBody(req.toString().getBytes());
				res.fields.put(CONTENT_LENGTH, Integer.toString(res.getBodySize()));
				res.fields.put(CONTENT_TYPE, ContentTypeDictionary.getContentTypeByExt(null));

			} else {
				return buildResponseByCode(501);
			}
		} catch (URISyntaxException e) {
			Console.logErr(e.getMessage());
			return buildResponseByCode(400);
		} catch (IllegalArgumentException e) {
			Console.logErr(e.getMessage());
			return buildResponseByCode(400);
		} catch (NoSuchFileException e) {
			return buildResponseByCode(404);
		} catch (Exception e) {
			Console.logErr(e.getMessage());
			e.printStackTrace();
			// Means there was an error generating response
			// So we build server error
			return buildResponseByCode(500);
		}

		return res;
	}

	private static byte[] getHTMLErrorAssetsByCode(int code) {
		byte[] content = null;
		try {
			content = Files.readAllBytes(Paths.get(ASSETS + code + ".html"));
		} catch (Exception e) {
			Console.log("Couldn't load asset for " + code + ".html");
			Console.logErr("Reason : " + e.getMessage());
		}
		return content;
	}

	public static HTTPResponse buildResponseByCode(int code) {
		HTTPResponse res = new HTTPResponse();
		res.fields.put("Date", new Date().toString());
		res.fields.put("Server", "Badly implemented/1.0 (Ubuntu)");

		res.setStatus(getResponseHeaderByCode(code));
		res.setBody(getHTMLErrorAssetsByCode(code));
		res.fields.put(CONTENT_LENGTH, Integer.toString(res.getBodySize()));
		res.fields.put(CONTENT_TYPE, ContentTypeDictionary.getContentTypeByExt("html"));

		return res;
	}
}
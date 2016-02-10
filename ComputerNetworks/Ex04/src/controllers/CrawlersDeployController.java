package controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import crawler.CrawlManager;
import htmlGenerator.HTMLGenerator;
import httpObjects.HTTPRequest;
import httpObjects.HTTPResponse;
import interfaces.RouteController;
import server.ResponseHandler;
import utils.PathUtils;

public class CrawlersDeployController implements RouteController {

	@Override
	public byte[] GET(HTTPRequest req, HTTPResponse res) {
		// Check we got referred from main page
		String ref = req.getGenericHeaders("Referer".toUpperCase());

		if (ref == null || (ref.indexOf("http://localhost:8080/crawlers") < 0
				&& !ref.equals("http://localhost:8080/index.html") && !ref.equals("http://localhost:8080/"))) {
			// Means we got here not from out main route
			res.setStatus(ResponseHandler.getResponseHeaderByCode(403));
			return new byte[0];
		}

		String fullUri = "";
		boolean scanPorts = false;
		boolean disrespectRobots = false;
		String query = null;
		URI requestedResource;
		try {
			requestedResource = PathUtils.toFullPath(req.getRequestedResource());
			query = requestedResource.getQuery();
		} catch (Exception e) {
			return HTMLGenerator.generateCrawlerErrorPage("You fucked up the domain, try again").getBytes();
		}

		// Make sure query isn't null
		if (query != null) {
			scanPorts = query.indexOf("portScan=on") > -1;
			disrespectRobots = query.indexOf("robots=on") > -1;

			// Extract host name
			Pattern p = Pattern.compile("domain=([A-Za-z0-9.:\\-/]*)");
			Matcher m = p.matcher(query);

			// if our pattern matches the string, we can try to extract our
			// groups
			if (m.find()) {
				fullUri = m.group(1);
			}
		}
		res.setStatus(ResponseHandler.getResponseHeaderByCode(200));
		return (CrawlManager.tryCrawl(fullUri, scanPorts, disrespectRobots)).getBytes();
	}

	@Override
	public byte[] POST(HTTPRequest req, HTTPResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] UPDATE(HTTPRequest req, HTTPResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String contentTypeByMethod(String str) {
		String res;
		switch (str) {
		case ("GET"):
			res = "html";
			break;
		case ("POST"):
			res = "html";
			break;
		case ("UPDATE"):
			res = "html";
			break;
		default:
			res = "html";
		}
		return res;
	}

}

package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import crawler.CrawlManager;
import interfaces.RouteController;

public class CrawllersDeployController implements RouteController {

	@Override
	// RETURNS THE RESPPONSE BODY!
	public byte[] GET(String query) {
		String domain = "";
		boolean scanPorts = false;
		boolean disrespectRobots = false;
		
		// Make sure query isn't null
		if (query != null) {
			scanPorts = query.indexOf("portScan=on") > -1;
			disrespectRobots = query.indexOf("robots=on") > -1;

			Pattern p = Pattern.compile("(https?:\\/\\/){1}(www)?(.*\\.[A-Za-z]*){1}");
			Matcher m = p.matcher(query);
			if (m.find()) {
				domain = m.group(0);
			}
		}
		return (CrawlManager.tryCrawl(domain, scanPorts, disrespectRobots)).getBytes();
	}

	@Override
	public byte[] POST(String body) {
		return null;
	}

	@Override
	public byte[] UPDATE(String body) {
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

package controllers;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import crawler.CrawlManager;
import httpObjects.HTTPRequest;
import interfaces.RouteController;
import utils.PathUtils;

public class CrawllersDeployController implements RouteController {

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

	@Override
	public byte[] GET(HTTPRequest req) {
		String domain = "";
		boolean scanPorts = false;
		boolean disrespectRobots = false;
		String query =null;
		try{
		URI requestedResource = PathUtils.toFullPath(req.getRequestedResource());
		query= requestedResource.getQuery();
		}catch(Exception e){
			// We don't really care
		}
		
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
	public byte[] POST(HTTPRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] UPDATE(HTTPRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}

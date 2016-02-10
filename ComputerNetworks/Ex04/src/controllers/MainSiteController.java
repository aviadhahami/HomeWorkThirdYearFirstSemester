package controllers;

import crawler.CrawlResultObject;
import htmlGenerator.HTMLGenerator;
import httpObjects.HTTPRequest;
import interfaces.RouteController;

public class MainSiteController implements RouteController {

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
		CrawlResultObject.getInstance();
		return (CrawlResultObject.isCrawling() ? HTMLGenerator.generateCrawlersBusyPage()
				: HTMLGenerator.generateMainPage()).getBytes();
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

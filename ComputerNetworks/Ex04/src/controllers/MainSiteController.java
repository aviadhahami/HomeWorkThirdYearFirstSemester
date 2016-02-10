package controllers;

import crawler.CrawlResultObject;
import htmlGenerator.HTMLGenerator;
import httpObjects.HTTPRequest;
import httpObjects.HTTPResponse;
import interfaces.RouteController;
import server.ResponseHandler;

public class MainSiteController implements RouteController {

	@Override
	public byte[] GET(HTTPRequest req, HTTPResponse res) {
		CrawlResultObject.getInstance();
		res.setStatus(ResponseHandler.getResponseHeaderByCode(200));
		return (CrawlResultObject.isCrawling() ? HTMLGenerator.generateCrawlersBusyPage()
				: HTMLGenerator.generateMainPage()).getBytes();
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

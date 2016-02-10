package controllers;

import crawler.CrawlResultObject;
import htmlGenerator.HTMLGenerator;
import interfaces.RouteController;

public class MainSiteController implements RouteController {

	@Override
	public byte[] GET(String query) {
		CrawlResultObject.getInstance();
		// TODO Auto-generated method stub
		return (CrawlResultObject.isCrawling ? HTMLGenerator.generateMainPage() : HTMLGenerator.generateCrawlersBusyPage()).getBytes();
	}

	@Override
	public byte[] POST(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] UPDATE(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String contentTypeByMethod(String str) {
		String res;
		switch(str){
		case("GET"):
			res="html";
			break;
		case("POST"):
			res="html";
			break;
		case("UPDATE"):
			res="html";
			break;
		default:
			res="html";
		}
		return res;
	}

}

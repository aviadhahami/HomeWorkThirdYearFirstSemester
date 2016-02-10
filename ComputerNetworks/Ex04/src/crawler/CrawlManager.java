package crawler;

import htmlGenerator.HTMLGenerator;
import utils.URIutils;

public class CrawlManager {

	private CrawlManager() {
		// Empty private constructor since we don't want instances dancing
		// around here
	}

	public static String tryCrawl(String domain, boolean scanPorts, boolean disrespectRobots) {
		if (!URIutils.isProperURI(domain)) {
			return HTMLGenerator.generateCrawlerErrorPage("You fucked up the domain, try again");
		}
		if(CrawlResultObject.isCrawling()){
			return HTMLGenerator.generateCrawlersBusyPage();
		}
		CrawlManager.crawl(domain, scanPorts, disrespectRobots);
		return HTMLGenerator.generateCrawlResultsPage();
	}

	private static boolean crawl(String domain, boolean scanPorts, boolean disrespectRobots) {
		CrawlResultObject.getInstance();
		CrawlResultObject.setDomain(domain);
		CrawlResultObject.setScanPorts(scanPorts);
		CrawlResultObject.setDisrespectRobots(disrespectRobots);
		
		// IMPORTANT : need to change to false once done
		CrawlResultObject.setCrawling(true);
		
		// TODO: PERFORM CRAWL
		
	   return false;
	    
		
	}

}

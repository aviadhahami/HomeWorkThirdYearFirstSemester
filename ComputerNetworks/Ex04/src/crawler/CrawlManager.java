package crawler;

import htmlGenerator.HTMLGenerator;

public class CrawlManager {

	private CrawlManager() {
		// Empty private constructor since we don't want instances dancing
		// around here
	}

	public static String tryCrawl(String domain, boolean scanPorts, boolean disrespectRobots) {
		if (domain.length() == 0 || domain == null) {
			return HTMLGenerator.generateCrawlerErrorPage("You fucked up the domain, try again");
		}
		if(CrawlResultObject.isCrawling){
			return HTMLGenerator.generateCrawlersBusyPage();
		}
		CrawlManager.crawl(domain, scanPorts, disrespectRobots);
		return HTMLGenerator.generateCrawlResultsPage();
	}

	private static boolean crawl(String domain, boolean scanPorts, boolean disrespectRobots) {
		CrawlResultObject.getInstance();
		CrawlResultObject.domain = domain;
		CrawlResultObject.scanPorts = scanPorts;
		CrawlResultObject.disrespectRobots = disrespectRobots;
		CrawlResultObject.isCrawling = true;
		// TODO: PERFORM CRAWL
		return false;
	}

}

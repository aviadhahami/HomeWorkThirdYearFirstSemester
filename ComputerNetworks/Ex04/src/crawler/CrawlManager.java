package crawler;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.corba.se.spi.activation.Server;
import com.sun.org.apache.xpath.internal.axes.WalkingIterator;

import config.ServerConfigObj;
import downloaders.Downloader;
import htmlGenerator.HTMLGenerator;
import portscanner.PortScanner;
import threadPool.ThreadPoolManager;

public class CrawlManager {

	private CrawlManager() {
		// Empty private constructor since we don't want instances dancing
		// around here
	}

	public static String tryCrawl(String query, boolean scanPorts, boolean disrespectRobots) {
		URI uri = null;
		try {
			uri = new URI(query);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return HTMLGenerator.generateCrawlerErrorPage("Bad URI");
		}
		if (CrawlResultObject.isCrawling()) {
			return HTMLGenerator.generateCrawlersBusyPage();
		}
		CrawlManager.crawl(uri, scanPorts, disrespectRobots);
		return HTMLGenerator.generateCrawlResultsPage();
	}

	private static boolean crawl(URI uri, boolean scanPorts, boolean disrespectRobots) {
		CrawlResultObject.getInstance();
		CrawlResultObject.setHost(uri.getHost());
		CrawlResultObject.setResouce(uri.getQuery());
		CrawlResultObject.setScanPorts(scanPorts);
		CrawlResultObject.setDisrespectRobots(disrespectRobots);

		// IMPORTANT : need to change to false once done
		CrawlResultObject.setCrawling(true);

		if (CrawlResultObject.isScanPorts()) {
			PortScanner.deploy();
		}

		// TODO: PERFORM CRAWL

		// Init analyzers pool
		ThreadPoolManager analyzerPoolManager = new ThreadPoolManager(ServerConfigObj.getMaxAnalyzer());
		// Init downloaders pool
		ThreadPoolManager downloadersPoolManager = new ThreadPoolManager(ServerConfigObj.getMaxDownloaders());
		// insert into queue the domain so they can use it

		// Run downloaders & analyzers
		downloadersPoolManager.submitTask(new Downloader(analyzerPoolManager, downloadersPoolManager, uri, "html"));

		// Save page once done
		while (!analyzerPoolManager.isFinished() || !downloadersPoolManager.isFinished()) {
			continue;
		}
		CrawlResultObject.getInstance();
		CrawlResultObject.setCrawling(false);
		return false;

	}

}

package crawler;

import java.util.ArrayList;
import java.util.Hashtable;

public class CrawlResultObject {

	private static CrawlResultObject instance = null;
	private static boolean isCrawling = false;
	private static boolean disrespectRobots;
	private static boolean scanPorts;
	private static String host;
	private static String resouce;
	private static ArrayList<Integer> openPorts;
	private static int totalImageSize = 0;
	private static int amountOfImg = 0;
	private static int amountofDoc = 0;
	private static int totalDocSize = 0;
	private static int totalVidSize = 0;
	private static int amountOfVid = 0;
	private static int amountOfRequest = 0;
	private static long totalTime = 0;
	private static int externalDomainsAmount = 0;

	private static Hashtable<String, Boolean> externalDomains = new Hashtable<>();
	private static int amountOfPage = 0;
	private static int totalPagesSize = 0;

	private CrawlResultObject() {
		// Exists only to defeat instantiation.
		openPorts = new ArrayList<>();
	}

	public static CrawlResultObject getInstance() {
		if (instance == null) {
			instance = new CrawlResultObject();
		}
		return instance;
	}

	public String toHTML() {
		String _div = "<div>";
		String div_ = "</div>";
		String _p = "<p>";
		String p_ = "</p>";
		String br = "<br>";
		StringBuilder sb = new StringBuilder();
		sb.append(_div);
		sb.append(_p);
		sb.append("Crawled at: " + getHost());
		sb.append(p_);
		sb.append(_p);
		sb.append("disrespected robot: " + isDisrespectRobots() + "; Performed port scan: " + isScanPorts());
		sb.append(p_);
		sb.append(br);
		sb.append(_p);
		sb.append("data goes here");
		sb.append(p_);
		// Port scanner data
		if (scanPorts) {
			sb.append(br);
			sb.append(_p);
			sb.append("Open ports:");
			for (Integer port : openPorts) {
				sb.append(port + ", ");
			}
			sb.append(p_);
		}
		// End port scanner data
		// RTT
		sb.append(br);
		sb.append(_p);
		sb.append("Avg. RTT: " + totalTime / amountOfRequest);
		sb.append(p_);
		// END RTT

		sb.append(div_);
		return sb.toString();
	}

	public static boolean isCrawling() {
		return isCrawling;
	}

	public static void setCrawling(boolean isCrawling) {
		CrawlResultObject.isCrawling = isCrawling;
	}

	public static boolean isDisrespectRobots() {
		return disrespectRobots;
	}

	public static void setDisrespectRobots(boolean disrespectRobots) {
		CrawlResultObject.disrespectRobots = disrespectRobots;
	}

	public static boolean isScanPorts() {
		return scanPorts;
	}

	public static void setScanPorts(boolean scanPorts) {
		CrawlResultObject.scanPorts = scanPorts;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		CrawlResultObject.host = host;
	}

	public static void addOpenPort(int port) {
		openPorts.add(port);
	}

	public static String getResouce() {
		return resouce;
	}

	public static void setResouce(String resouce) {
		CrawlResultObject.resouce = resouce;
	}

	public static void addDoc(int fileSize) {
		CrawlResultObject.getInstance();
		totalDocSize += fileSize;
		amountofDoc += 1;
	}

	public static void addVid(int fileSize) {
		CrawlResultObject.getInstance();
		totalVidSize += fileSize;
		amountOfVid += 1;
	}

	public static void addImg(int fileSize) {
		CrawlResultObject.getInstance();
		totalImageSize += fileSize;
		amountOfImg += 1;

	}

	public static void updateRTT(long time) {
		CrawlResultObject.getInstance();
		amountOfRequest += 1;
		totalTime += time;
	}

	public static void increaseExternalDomainAmount(int size) {
		externalDomainsAmount += size;
	}

	public static int getExternalDomainAmount() {
		return externalDomainsAmount;
	}

	public static void addExternalUriDomainName(String host) {
		CrawlResultObject.getInstance();
		externalDomains.put(host.toLowerCase(), true);
	}

	public static void addPage(int amount) {
		CrawlResultObject.getInstance();
		amountOfPage += 1;
		totalPagesSize += amount;

	}
}
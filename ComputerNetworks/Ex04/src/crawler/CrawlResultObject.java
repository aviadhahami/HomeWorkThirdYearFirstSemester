package crawler;

import java.util.ArrayList;

public class CrawlResultObject {

	private static CrawlResultObject instance = null;
	private static boolean isCrawling = false;
	private static boolean disrespectRobots;
	private static boolean scanPorts;
	private static String domain;
	private static ArrayList<Integer> openPorts;

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
		sb.append("Crawled at: " + getDomain());
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

	public static String getDomain() {
		return domain;
	}

	public static void setDomain(String domain) {
		CrawlResultObject.domain = domain;
	}

	public static void addOpenPort(int port) {
		openPorts.add(port);
	}
}
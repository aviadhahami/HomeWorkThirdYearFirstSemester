package crawler;

public class CrawlResultObject {

	private static CrawlResultObject instance = null;
	public static boolean disrespectRobots;
	public static boolean scanPorts;
	public static String domain;

	private CrawlResultObject() {
		// Exists only to defeat instantiation.
	}

	public static CrawlResultObject getInstance() {
		if (instance == null) {
			instance = new CrawlResultObject();
		}
		return instance;
	}

	public String toHTML() {
		String _div = "<div>";
		String div_ ="</div>";
		String _p = "<p>";
		String p_ = "</p>";
		String br = "<br>";
		StringBuilder sb= new StringBuilder();
		sb.append(_div);
		sb.append(_p);
		sb.append("Crawled at: " + domain);
		sb.append(p_);
		sb.append(_p);
		sb.append("disrespected robot: " + disrespectRobots + "; Performed port scan: " + scanPorts);
		sb.append(p_);
		sb.append(br);
		sb.append(_p);
		sb.append("data goes here");
		sb.append(p_);
		sb.append(div_);
		return sb.toString();
	}
}
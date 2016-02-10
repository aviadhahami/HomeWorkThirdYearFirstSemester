package config;

public class ServerConfigObj {

	// Fallbacks
	private final static int defaultPort = 8080;
	private final static int defaultMaxThreads = 10;
	private final static String defaultRoot = "";
	private final static String defaultDefaultPage = "";
	private final static int defaultMaxDownloaders = 10;
	private final static int defaultMaxAnalyzers = 2;
	private final static String[] defaultImageTypes = new String[] { "bmp", "jpg", "png", "gif", "ico" };
	private final static String[] defaultVideoTypes = new String[] { "avi", "mpg", "mp4", "wmv", "mov", "flv", "swf", "mkv" };
	private final static String[] defaultDocumentTypes = new String[] { "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx" };
	private static int socketTimeout;

	private static int port = 0;
	private static int maxThreads = 0;
	private static String root = null;
	private static String defaultPage = null;
	private static int defaultSocketTimeout = 20000; // In ms
	private static int maxDownloaders = -1;
	private static int maxAnalyzers = -1;
	private static String[] imageTypes = null;
	private static String[] videoTypes = null;
	private static String[] documentTypes = null;

	// Getters and setters for all members
	public static void setPort(int port) {
		ServerConfigObj.port = port;
	}

	public static int getPort() {
		if (ServerConfigObj.port == 0) {
			ServerConfigObj.port = defaultPort;
			System.out.println("Used default port " + defaultPort);
		}
		return ServerConfigObj.port;
	}

	public static void setMaxThreads(int maxThreads) {
		ServerConfigObj.maxThreads = maxThreads;
	}

	public static int getMaxThreads() {
		if (ServerConfigObj.maxThreads == 0) {
			ServerConfigObj.maxThreads = defaultMaxThreads;
			System.out.println("Used default max threads " + defaultMaxThreads);
		}
		return ServerConfigObj.maxThreads;
	}

	public static void setDefaultRoot(String path) {
		ServerConfigObj.root = path;
	}

	public static String getRoot() {
		if (ServerConfigObj.root == null) {
			ServerConfigObj.root = defaultRoot;
			System.out.println("Used default root path : " + ServerConfigObj.root);
		}
		return ServerConfigObj.root;
	}

	public static void setDefaultPage(String path) {
		ServerConfigObj.defaultPage = path;
	}

	public static String getDefaultPage() {
		if (ServerConfigObj.defaultPage == null) {
			ServerConfigObj.defaultPage = defaultDefaultPage;
			System.out.println("Used default main page : " + ServerConfigObj.defaultPage);
		}
		return ServerConfigObj.defaultPage;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("port : " + ServerConfigObj.port + "\n");
		sb.append("max threads : " + ServerConfigObj.maxThreads + "\n");
		sb.append("root : " + ServerConfigObj.root + "\n");
		sb.append("default page : " + ServerConfigObj.defaultPage + "\n");
		return sb.toString();
	}

	public static int getSocketTimeout() {
		if (socketTimeout == 0) {
			socketTimeout = ServerConfigObj.defaultSocketTimeout;
			System.out.println("Used default socket timeout : " + ServerConfigObj.socketTimeout);
		}
		return socketTimeout;
	}

	public static void setSocketTimeout(int val) {
		socketTimeout = val;
	}

	public static void setMaxDownloaders(int maxDownloaders) {
		ServerConfigObj.maxDownloaders = maxDownloaders;
	}

	public static int getMaxDownloaders() {
		return ServerConfigObj.maxDownloaders == -1 ? defaultMaxDownloaders : ServerConfigObj.maxDownloaders;
	}

	public static void setMaxAnalyzer(int maxAnalyzers) {
		ServerConfigObj.maxAnalyzers = maxAnalyzers;
	}

	public  static int getMaxAnalyzer() {
		return ServerConfigObj.maxAnalyzers == -1 ? defaultMaxAnalyzers : ServerConfigObj.maxAnalyzers;
	}

	public static void setImageTypes(String[] arr) {
		ServerConfigObj.imageTypes = arr;
	}

	public  static String[] getImageTypes() {
		return ServerConfigObj.imageTypes == null ? defaultImageTypes : ServerConfigObj.imageTypes;
	}

	public static void setVideoTypes(String[] arr) {
		ServerConfigObj.videoTypes = arr;

	}

	public  static String[] getVideoTypes() {
		return ServerConfigObj.videoTypes == null ? defaultVideoTypes : ServerConfigObj.videoTypes;
	}

	public static void setDocumentTypes(String[] arr) {
		ServerConfigObj.documentTypes = arr;
	}

	public static String[] getDocumentTypes() {
		return ServerConfigObj.documentTypes == null ? defaultDocumentTypes : ServerConfigObj.documentTypes;
	}
}

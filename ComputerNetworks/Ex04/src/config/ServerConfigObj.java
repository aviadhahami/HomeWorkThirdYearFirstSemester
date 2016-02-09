package config;

public class ServerConfigObj {

	// Fallbacks
	private final int defaultPort = 8080;
	private final int defaultMaxThreads = 10;
	private final String defaultRoot = "";
	private final String defaultDefaultPage = "";
	private final int defaultMaxDownloaders = 10;
	private final int defaultMaxAnalyzers = 2;
	private final String[] defaultImageTypes = new String[] { "bmp", "jpg", "png", "gif", "ico" };
	private final String[] defaultVideoTypes = new String[] { "avi", "mpg", "mp4", "wmv", "mov", "flv", "swf", "mkv" };
	private final String[] defaultDocumentTypes = new String[] { "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx" };
	private int socketTimeout;

	private int port = 0;
	private int maxThreads = 0;
	private String root = null;
	private String defaultPage = null;
	private int defaultSocketTimeout = 20000; // In ms
	private int maxDownloaders = -1;
	private int maxAnalyzers = -1;
	private String[] imageTypes = null;
	private String[] videoTypes = null;
	private String[] documentTypes = null;

	// Getters and setters for all members
	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		if (this.port == 0) {
			this.port = this.defaultPort;
			System.out.println("Used default port " + defaultPort);
		}
		return this.port;
	}

	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}

	public int getMaxThreads() {
		if (this.maxThreads == 0) {
			this.maxThreads = this.defaultMaxThreads;
			System.out.println("Used default max threads " + defaultMaxThreads);
		}
		return this.maxThreads;
	}

	public void setDefaultRoot(String path) {
		this.root = path;
	}

	public String getRoot() {
		if (this.root == null) {
			this.root = this.defaultRoot;
			System.out.println("Used default root path : " + this.root);
		}
		return this.root;
	}

	public void setDefaultPage(String path) {
		this.defaultPage = path;
	}

	public String getDefaultPage() {
		if (this.defaultPage == null) {
			this.defaultPage = this.defaultDefaultPage;
			System.out.println("Used default main page : " + this.defaultPage);
		}
		return this.defaultPage;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("port : " + this.port + "\n");
		sb.append("max threads : " + this.maxThreads + "\n");
		sb.append("root : " + this.root + "\n");
		sb.append("default page : " + this.defaultPage + "\n");
		return sb.toString();
	}

	public int getSocketTimeout() {
		if (this.socketTimeout == 0) {
			this.socketTimeout = this.defaultSocketTimeout;
			System.out.println("Used default socket timeout : " + this.socketTimeout);
		}
		return this.socketTimeout;
	}

	public void setSocketTimeout(int val) {
		this.socketTimeout = val;
	}

	public void setMaxDownloaders(int maxDownloaders) {
		this.maxDownloaders = maxDownloaders;
	}

	public int getMaxDownloaders() {
		return this.maxDownloaders == -1 ? this.defaultMaxDownloaders : this.maxDownloaders;
	}

	public void setMaxAnalyzer(int maxAnalyzers) {
		this.maxAnalyzers = maxAnalyzers;
	}

	public int getMaxAnalyzer() {
		return this.maxAnalyzers == -1 ? this.defaultMaxAnalyzers : this.maxAnalyzers;
	}

	public void setImageTypes(String[] arr) {
		this.imageTypes = arr;
	}

	public String[] getImageTypes() {
		return this.imageTypes == null ? this.defaultImageTypes : this.imageTypes;
	}

	public void setVideoTypes(String[] arr) {
		this.videoTypes = arr;

	}

	public String[] getVideoTypes() {
		return this.videoTypes == null ? this.defaultVideoTypes : this.videoTypes;
	}

	public void setDocumentTypes(String[] arr) {
		this.documentTypes = arr;
	}

	public String[] getDocumentTypes() {
		return this.documentTypes == null ? this.defaultDocumentTypes : this.documentTypes;
	}
}

package server;

public class ServerConfigObj {

	// Fallbacks
	private final int defaultPort = 8080;
	private final int defaultMaxThreads = 10;
	private final String defaultRoot = "";
	private final String defaultDefaultPage = "";

	private int port = 0;
	private int maxThreads = 0;
	private String root = null;
	private String defaultPage = null;

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
		if (this.root.equals(null)) {
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
}

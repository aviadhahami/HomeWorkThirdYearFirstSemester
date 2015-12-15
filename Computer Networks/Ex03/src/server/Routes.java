package server;

public class Routes {

	private static String root = "";
	private static String defaultPage = "";

	public static void setRoot(String givenRoot) {
		root = givenRoot;
	}

	public static String getRoot() {
		return root;
	}

	public static void setDefaultPage(String giveDefaultPage) {
		defaultPage = giveDefaultPage;
	}

	public static String getDefaultPage() {
		return defaultPage;
	}

}

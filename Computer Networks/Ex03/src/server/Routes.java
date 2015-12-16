package server;

import java.util.HashMap;

public class Routes {

	private static String root = "";
	private static String defaultPage = "";
	private static HashMap<String, Integer> routesAccessMap = new HashMap<>();

	public static void initRoutes(String _root, String _defaultPage) {
		root = _root;
		defaultPage = _defaultPage;
		routesAccessMap.put("/", 0);
	}

	/*
	 * Here we test the accessibility of the path, assuming path was purified
	 */
	public static boolean testRouteAccessibility(String path, int userLevel) {

		String unifiedPath = unifyPath(path);
		if (!isValidPath(unifiedPath)) {
			return false;
		}

		StringBuilder sb = new StringBuilder();
		char curr;
		int previousLevel = 0;
		String builtPath = "";
		int extractedLevel;

		for (int i = 0; i < unifiedPath.length(); i++) {
			curr = unifiedPath.charAt(i);
			if (curr == '/') {
				// Test for allowance
				extractedLevel = 0;
				builtPath = sb.toString();
				try {
					extractedLevel = routesAccessMap.get(builtPath);
					if (extractedLevel <= userLevel) {

						// Means user level is higher than this path, so we can
						// allow him

						// Record the previous level
						previousLevel = extractedLevel;
					} else {

						// Means the path level is higher than the user's
						return false;
					}
				} catch (Exception e) {

					// Means the returned value was null
					Console.logErr("Unmonitored path - adding to cache and using inherited level");
					Console.logErr("Path added - " + builtPath + " : " + previousLevel);
					routesAccessMap.put(builtPath, previousLevel);
				}

				// Clear and append slash
				sb.setLength(0);
				sb.append('/');
			} else if (curr == '.') {

				// This means that we've passed all the parent folders of the
				// file
				// and if we haven't got kicked out then the file is OK
				return true;

			} else {

				// Else keep building folders' names
				sb.append(curr);
			}
		}
		return true;
	}

	// Unify the paths we test
	private static String unifyPath(String path) {
		StringBuilder sb = new StringBuilder();
		if (!path.startsWith("/")) {
			sb.append("/");
			sb.append(path);
		} else {
			sb.append(path);
		}
		return sb.toString();
	}

	// Test if there's a try to inject faulty path i.e. "a.html/abc"
	public static boolean isValidPath(String path) {

		if (path.equals("/")) {
			return true;
		}
		String firstDot = path.substring(path.indexOf("."));

		// If there is a slash after the dot, than the path is not valid so we
		// return the opposite of the condition
		return !(firstDot.indexOf('/') > -1);

	}

	public static String getDefaultPage() {
		return defaultPage;
	}

	public static String getRoot() {
		return root;
	}

}

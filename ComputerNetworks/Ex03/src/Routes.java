
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

		if (!PathUtils.isValidPath(path)) {
			return false;
		}

		Console.log("THIS IS PATH " + path);
		StringBuilder sb = new StringBuilder();
		char curr;
		int previousLevel = 0;
		String builtPath = "";
		int extractedLevel;

		sb.append("/");
		for (int i = 0; i < path.length(); i++) {
			curr = path.charAt(i);
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

	public static String getDefaultPage() {
		return defaultPage;
	}

	public static String getRoot() {
		return root;
	}

}

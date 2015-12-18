import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class PathUtils {

	public static URI toFullPath(String path) throws URISyntaxException {
		StringBuilder sb = new StringBuilder();

		if (path.equals("/") || path.length() == 0 || path == null) {
			sb.append(Routes.getDefaultPage());
		} else if (path.startsWith("/")) {
			sb.append(path.substring(1, path.length()));
		}
		return new URI(sb.toString());

	}

	public static boolean isValidPath(String path) {
		if (path == null) {
			return false;
		}
		String dummyPrefix = "http://localhost/";
		try {
			URL u = new URL(dummyPrefix + path);
			u.toURI();
		} catch (Exception e) {
			Console.logErr(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

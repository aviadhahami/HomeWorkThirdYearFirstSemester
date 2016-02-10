package utils;

import java.net.URL;

public class URIutils {

	public static boolean isProperURI(String uri) {

		final URL url;
		try {
			url = new URL(uri);
		} catch (Exception e1) {
			return false;
		}
		return "http".equals(url.getProtocol());
	}

}

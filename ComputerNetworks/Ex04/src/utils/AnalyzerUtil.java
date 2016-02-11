package utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import config.ServerConfigObj;
import crawler.CrawlResultObject;

public class AnalyzerUtil {
	private static String[] docTypes = ServerConfigObj.getDocumentTypes();
	private static String[] vidTypes = ServerConfigObj.getVideoTypes();
	private static String[] imgTypes = ServerConfigObj.getImageTypes();
	private static String[][] allTypes = new String[][] { docTypes, vidTypes, imgTypes };

	private AnalyzerUtil() {
	}

	public static String extractMediaType(String contetTypeField) {
		return contetTypeField != null ? contetTypeField.substring(contetTypeField.indexOf("/") + 1) : "";
	}

	public static boolean isVaibleMediaType(String mediaType) {
		for (String[] type : allTypes) {
			for (String t : type) {
				if(t.equals(mediaType));
			}
		}
		return false;
	}

	public static String getMediaType(String mediaName) {
		if (Arrays.asList(docTypes).contains(mediaName)) {
			return "doc";
		} else if (Arrays.asList(vidTypes).contains(mediaName)) {
			return "vid";
		} else if (Arrays.asList(imgTypes).contains(mediaName)) {
			return "img";
		}
		return "";
	}

	public static String[] extractLinksFromHTML(byte[] body) {
		String bodyAsString = new String(body);
		Pattern p = Pattern.compile("href=\"(.*?)\"", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		Matcher m = p.matcher(bodyAsString);
		ArrayList<String> res = new ArrayList<>();
		while (m.find()) {
			for (int i = 0; i < m.groupCount(); i++) {
				System.out.println(m.group(i).substring(m.group(i).indexOf("\"") + 1, m.group(i).lastIndexOf("\"")));
				res.add(m.group(i).substring(m.group(i).indexOf("\"") + 1, m.group(i).lastIndexOf("\"")));
			}
		}
		return res.toArray(new String[res.size()]);
	}


}

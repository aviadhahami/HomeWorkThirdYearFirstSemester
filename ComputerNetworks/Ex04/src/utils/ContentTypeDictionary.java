package utils;


import java.util.HashMap;
import java.util.Map;

public class ContentTypeDictionary {
	private static final Map<String, String> contentTypesDictionary = new HashMap<String, String>();
	private static final String DEFAULT_TYPE = "application/octet-stream";

	static {
		contentTypesDictionary.put("html", "text/html");
		contentTypesDictionary.put("ico", "icon");
		contentTypesDictionary.put("png", "image");
		contentTypesDictionary.put("gif", "image");
		contentTypesDictionary.put("bmp", "image");
		contentTypesDictionary.put("jpg", "image");
	}

	public static String getContentTypeByExt(String type) {
		String dictVal = contentTypesDictionary.get(type);
		return (dictVal == null) ? DEFAULT_TYPE : dictVal;
	}
}

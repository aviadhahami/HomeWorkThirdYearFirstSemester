package utils;

import java.util.Arrays;

import config.ServerConfigObj;

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
		return Arrays.asList(docTypes).contains(mediaType) || Arrays.asList(vidTypes).contains(mediaType)
				|| Arrays.asList(imgTypes).contains(mediaType);
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
		String bodyAsString = body.toString();
		System.out.println(bodyAsString);
		return new String[0];
	}

}

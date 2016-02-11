package downloaders;

import java.util.Hashtable;

public class DownloadersBlackListSingleton {

	private static DownloadersBlackListSingleton isntance = new DownloadersBlackListSingleton();
	private static Hashtable<String, Boolean> alreadyDownload = new Hashtable<>();

	private DownloadersBlackListSingleton() {
		// TODO Auto-generated constructor stub
	}

	public static DownloadersBlackListSingleton getInstance() {
		return isntance;
	}

	public static boolean getFromTable(String k) {
		if (alreadyDownload.get(k) != null) {
			return true;
		} else {
			alreadyDownload.put(k, true);
			return false;
		}

	}

}

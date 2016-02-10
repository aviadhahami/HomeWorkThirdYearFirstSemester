package portscanner;

import java.net.InetSocketAddress;
import java.net.Socket;

import console.Console;
import crawler.CrawlResultObject;

public class PortScanner {

	public static void deploy() {
		CrawlResultObject.getInstance();
		String domain = CrawlResultObject.getDomain();
		for (int port = 1; port <= 65535; port++) {
			try {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(domain, port), 1000);
				socket.close();
				updateResult(port);
			} catch (Exception ex) {
			}
		}
	}

	private static void updateResult(int port) {
		Console.logErr("FOUND PORT! "  + port);
		CrawlResultObject.getInstance();
		CrawlResultObject.addOpenPort(port);
	}

}

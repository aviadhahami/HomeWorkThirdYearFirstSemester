package portscanner;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import console.Console;
import crawler.CrawlResultObject;

public class PortScanner {

	public static void deploy() {
		CrawlResultObject.getInstance();
		String domain = CrawlResultObject.getDomain();
		try {
			URI uri = new URI(domain);
			domain = uri.getHost();
		} catch (Exception e) {

		}
		
		// TODO : CHANGE PORTS LIMIT
		for (int port = 50; port <= 100; port++) {
			try {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(domain, port), 200);
				socket.close();
				updateResult(port);
			} catch (Exception ex) {
				continue;
			}
		}
	}

	private static void updateResult(int port) {
		CrawlResultObject.getInstance();
		CrawlResultObject.addOpenPort(port);
	}

}

package portscanner;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import crawler.CrawlResultObject;

public class PortScanner {

	public static void deploy() {
		CrawlResultObject.getInstance();
		String domain = CrawlResultObject.getHost();
//		try {
//			URI uri = new URI(domain);
//			domain = uri.
//		} catch (Exception e) {
//
//		}

		for (int port = 0; port <= 65536; port++) {
			try {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(domain, port), 200);
				updateResult(port);
				socket.close();
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

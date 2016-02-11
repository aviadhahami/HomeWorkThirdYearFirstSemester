package analyzers;

import java.net.URI;

import downloaders.Downloader;
import httpObjects.HTTPResponse;
import threadPool.ThreadPoolManager;

public class Analyzer implements Runnable {

	private ThreadPoolManager analyzersQue;
	private ThreadPoolManager downloadQue;
	private HTTPResponse res;

	public Analyzer(ThreadPoolManager analyzersQue, ThreadPoolManager downloaderQue, HTTPResponse res) {
		this.analyzersQue = analyzersQue;
		this.downloadQue = downloaderQue;
		this.res = res;
	}

	@Override
	public void run() {
		System.out.println("RUNNING!");
		if (res.getBodySize() > 0) {
			// We have body --> html page
			
			
		} else {
			// We have only headers

			// Check for "moved" headers
			if (res.getStatus().indexOf("30") > 0) {
				// Means we are moved
				URI uri;
				try {
					uri = new URI(res.fields.get("Location"));
					downloadQue.submitTask(new Downloader(analyzersQue, downloadQue, uri, "html"));
					System.err.println("WE REFER~ " + uri.toString());
				} catch (Exception e) {
					// Then this one is fucked
					return;
				}
			}
			
			// Now check for HEAD
		}

	}

}

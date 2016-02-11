package analyzers;

import java.net.URI;
import java.net.URISyntaxException;

import crawler.CrawlResultObject;
import downloaders.Downloader;
import httpObjects.HTTPResponse;
import threadPool.ThreadPoolManager;
import utils.AnalyzerUtil;

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
			String[] arrayOfLinks = AnalyzerUtil.extractLinksFromHTML(res.getBody());
						
			String type;
			for (String link : arrayOfLinks) {
				type = AnalyzerUtil.isVaibleMediaType(link) ? "head" : "html";
				URI uri;
				try {
					uri = new URI(link);
					downloadQue.submitTask(new Downloader(analyzersQue, downloadQue, uri, type));
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
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
			if (res.getStatus().indexOf("20") > 0) {
				String mediaType = AnalyzerUtil.extractMediaType(res.fields.get("Content-Type"));
				if (AnalyzerUtil.isVaibleMediaType(mediaType)) {
					updateResultsWithMedia(mediaType, res.fields.get("Content-Length"));
				}
			}
		}

	}

	private void updateResultsWithMedia(String mediaType, String size) {
		int fileSize = 0;

		fileSize = Integer.parseInt(size);

		CrawlResultObject.getInstance();
		switch (AnalyzerUtil.getMediaType(mediaType)) {
		case "doc":
			CrawlResultObject.addDoc(fileSize);
			break;
		case "vid":
			CrawlResultObject.addVid(fileSize);
			break;
		case "img":
			CrawlResultObject.addImg(fileSize);
			break;
		}

	}

}

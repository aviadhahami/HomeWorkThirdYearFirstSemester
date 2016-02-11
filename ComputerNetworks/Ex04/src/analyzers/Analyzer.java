package analyzers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

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
			// Update that we got another page
			CrawlResultObject.getInstance();
			CrawlResultObject.addPage(res.fields.get("Content-Length"));

			// We have body --> html page
			ArrayList<String> arrayOfLinks = new ArrayList<String>(
					Arrays.asList(AnalyzerUtil.extractLinksFromHTML(res.getBody())));
			ArrayList<URI> fullUriLinkList = new ArrayList<>();

			// Change relatives to full path and remove anchor tags
			try {
				for (String link : arrayOfLinks) {
					link = link.replace(" ", "%20");
					if (link.startsWith("/")) {
						CrawlResultObject.getInstance();
						fullUriLinkList.add(new URI("http://" + CrawlResultObject.getHost() + link));
					} else if (link.startsWith("//")) {
						// Relative method link
						CrawlResultObject.getInstance();
						fullUriLinkList.add(
								new URI("http://" + CrawlResultObject.getHost() + link.substring(1, link.length())));
					} else if (link.startsWith("http://") || link.startsWith("https://")) {
						// Then it's a fine link
						fullUriLinkList.add(new URI(link));
					} else if (link.startsWith("#")) {
						// Anchor tag, we don't care and we don't keep it
					} else {
						CrawlResultObject.getInstance();
						fullUriLinkList.add(new URI("http://" + CrawlResultObject.getHost() + "/" + link));
					}
				}
			} catch (URISyntaxException e) {

				e.printStackTrace();
				return;
			}

			// Filter only inner domain links
			ArrayList<URI> externalLinks = new ArrayList<>();
			ArrayList<URI> internalLinks = new ArrayList<>();
			for (URI uri : fullUriLinkList) {
				CrawlResultObject.getInstance();
				if (uri.getHost().equals(CrawlResultObject.getHost())) {
					internalLinks.add(uri);
				} else {
					externalLinks.add(uri);
				}
			}

			// Update amount of external domain links
			CrawlResultObject.getInstance();
			CrawlResultObject.increaseExternalDomainAmount(externalLinks.size());

			// Append name of domain to hash of external domains
			for (URI uri : externalLinks) {
				CrawlResultObject.getInstance();
				CrawlResultObject.addExternalUriDomainName(uri.getHost());
				CrawlResultObject.addExternalLinkCount(1);
			}

			ArrayList<URI> toDownload = new ArrayList<>();
			// Get all internal and purify against config

			// FIXME
			for (URI uri : internalLinks) {
				downloadQue.submitTask(new Downloader(analyzersQue, downloadQue, uri, "html"));
			}

			// Get all media from external and purify against config
			// ONLY HEAD THESE
			for (URI uri : externalLinks) {
				downloadQue.submitTask(new Downloader(analyzersQue, downloadQue, uri, "head"));
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
				if (mediaType.equals("html")) {
					CrawlResultObject.getInstance();
					CrawlResultObject.addPage(res.fields.get("Content-Length"));
				}
			}
		}
		return;

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

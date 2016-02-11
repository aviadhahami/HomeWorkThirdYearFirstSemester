package downloaders;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import analyzers.Analyzer;
import console.Console;
import crawler.CrawlResultObject;
import httpObjects.HTTPRequest;
import httpObjects.HTTPResponse;
import threadPool.ThreadPoolManager;

public class Downloader implements Runnable {

	private String downloadType;
	private ThreadPoolManager analyzersQue;
	private Socket socket;
	private OutputStream out;
	private URI uri;
	private BufferedReader reader;

	StringBuilder sb = new StringBuilder();
	private ThreadPoolManager downloaderQue;

	public Downloader(ThreadPoolManager analyzersQue, ThreadPoolManager downloadersQue, URI uri, String type) {
		this.analyzersQue = analyzersQue;
		this.downloaderQue = downloadersQue;
		this.uri = uri;
		this.downloadType = type;
	}

	@Override
	public void run() {
		try {
			// Verify against robots

			// Verify against links already downloaded
			DownloadersBlackListSingleton.getInstance();
			if (DownloadersBlackListSingleton.getFromTable(uri.getPath() == null ? "/" : uri.getPath())) {
				// Means we've seen this URI before
				return;
			}

			socket = new Socket(InetAddress.getByName(uri.getHost()), 80);
			out = socket.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

			if (downloadType.equals("html")) {
				// Download the page and shove it to the analyzer

				HTTPRequest req = new HTTPRequest();
				req.setRequestType("GET");
				req.setHTTPVersion("HTTP/1.1");
				req.setRequestedResource(uri.getPath() == null || uri.getPath().length() < 1 ? "/" : uri.getPath());
				req.setGenericHeaders("Host", uri.getHost());
				out.write(req.toString().getBytes());

				// Start RTT calc
				long startClock = System.currentTimeMillis();
				String line = reader.readLine();
				long endClock = System.currentTimeMillis();
				CrawlResultObject.getInstance();
				CrawlResultObject.updateRTT(endClock - startClock);

				// End RTT calc
				HTTPResponse res = new HTTPResponse();
				res.setStatus(line);
				while ((line = reader.readLine()) != null) {
					if (line.length() == 0) {
						break;
					}
					res.fields.put(line.split(" ")[0].replace(":", ""),
							(line.split(" ").length > 1 ? line.split(" ")[1] : "0"));
				}

				// We hit an "\r\n", get the body
				String contentLength = res.fields.get("Content-Length");
				String chunked = res.fields.get("Transfer-Encoding");
				if (contentLength != null) {
					int bodyLength = 0;
					try {
						bodyLength = Integer.parseInt(contentLength);
					} catch (NumberFormatException e) {
						// Don't care cause we set to zero
					}
					sb = new StringBuilder();
					while ((line = reader.readLine()) != null) {
						if (line.length() == 0) {
							break;
						}
						sb.append(line);
					}
					res.setBody(sb.toString().getBytes());
				} else if (chunked != null && chunked.toLowerCase().equals("chunked")) {
					// TODO: this
					return;
				} else {
					// Just eat it all
					sb = new StringBuilder();
					while ((line = reader.readLine()) != null) {
						if (line.length() == 0) {
							break;
						}
						sb.append(line);
					}
					res.setBody(sb.toString().getBytes());
				}

				// Send to analyzers
				analyzersQue.submitTask(new Analyzer(analyzersQue, downloaderQue, res));

			} else {
				// Perform HEAD

				HTTPRequest req = new HTTPRequest();
				req.setRequestType("HEAD");
				req.setHTTPVersion("HTTP/1.0");// use 1.0 to avoid chunked
				req.setRequestedResource(uri.getPath() == null ? "/" : uri.getPath());
				req.setGenericHeaders("Host", uri.getHost());
				out.write(req.toString().getBytes());

				HTTPResponse res = new HTTPResponse();
				String line = reader.readLine();
				res.setStatus(line);
				while ((line = reader.readLine()) != null) {
					if (line.length() == 0) {
						break;
					}
					res.fields.put(line.split(":")[0], (line.split(":").length > 1 ? line.split(":")[1] : ""));
				}
				// Send to analyzers
				analyzersQue.submitTask(new Analyzer(analyzersQue, downloaderQue, res));

				// Update link was downloaded
			}

		} catch (

		IOException e)

		{
			e.printStackTrace();
		}
		return;
	}

}

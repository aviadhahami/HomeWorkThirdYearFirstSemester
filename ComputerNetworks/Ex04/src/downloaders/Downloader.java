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

			socket = new Socket(InetAddress.getByName(uri.getHost()), 80);
			out = socket.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

			if (downloadType.equals("html")) {
				// Download the page and shove it to the analyzer

				HTTPRequest req = new HTTPRequest();
				req.setRequestType("GET");
				req.setHTTPVersion("HTTP/1.1");
				req.setRequestedResource(uri.getPath().length() == 0 ? "/" : uri.getPath());
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
				if (contentLength != null) {
					int bodyLength = 0;
					try {
						bodyLength = Integer.parseInt(contentLength);
					} catch (NumberFormatException e) {
						// Don't care cause we set to zero
					}
					sb = new StringBuilder();
					for (int i = 0; i < bodyLength; i++) {
						line = reader.readLine();
						sb.append(line);
					}
					res.setBody(sb.toString().getBytes());
				}

				// Send to analyzers
				analyzersQue.submitTask(new Analyzer(analyzersQue, downloaderQue, res));
				
				// Update link was downloaded
				
			} else {
				// Perform HEAD

				HTTPRequest req = new HTTPRequest();
				req.setRequestType("HEAD");
				req.setHTTPVersion("HTTP/1.1");
				req.setRequestedResource(uri.getPath().length() == 0 ? "/" : uri.getPath());
				req.setGenericHeaders("Host", uri.getHost());
				out.write(req.toString().getBytes());

				HTTPResponse res = new HTTPResponse();
				String line = reader.readLine();
				res.setStatus(line);
				while ((line = reader.readLine()) != null) {
					if (line.length() == 0) {
						break;
					}
					line = line.replace(":", "");
					res.fields.put(line.split(" ")[0], (line.split(" ").length > 1 ? line.split(" ")[1] : "0"));
				}
				// Send to analyzers
				analyzersQue.submitTask(new Analyzer(analyzersQue, downloaderQue, res));

				// Update link was downloaded
			}

		} catch (

		IOException e)

		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

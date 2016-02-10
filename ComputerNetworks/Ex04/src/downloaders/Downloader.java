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

import console.Console;
import httpObjects.HTTPRequest;
import threadPool.ThreadPoolManager;

public class Downloader implements Runnable {

	private String downloadType;
	private ThreadPoolManager analyzersQue;
	private Socket socket;
	private OutputStream out;
	private URI uri;
	private BufferedReader reader;

	StringBuilder sb = new StringBuilder();

	public Downloader(ThreadPoolManager analyzersQue, URI uri, String type) {
		this.analyzersQue = analyzersQue;
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

				// Open socket to dest.

				HTTPRequest req = new HTTPRequest();
				req.setRequestType("GET");
				req.setHTTPVersion("HTTP/1.1");
				req.setRequestedResource("/" + ((uri.getQuery() == null) ? "" : ("?" + uri.getQuery())));
				Console.logErr(req.toString());
				// out.println(req.toString());
				// BufferedWriter wr = new BufferedWriter(new
				// OutputStreamWriter(socket.getOutputStream(), "UTF8"));
				//
				// wr.write(req.toString());
				// wr.flush();
				
				out.write(req.toString().getBytes());
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
				// Send to analyzers

			} else {
				// Perfom HEAD
			}

		} catch (

		IOException e)

		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

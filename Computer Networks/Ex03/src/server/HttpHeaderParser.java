package server;

public class HttpHeaderParser {

	public String parseGET(String header) {
		return "<HTML>" + "<h1>Welcome</h1>" + "<p>Youve asked this <br />" + header.replace("\n", "<br />")
				+ "</HTML>";
	}

	public String parsePOST(String header) {
		return "<HTML>" + "<h1>Welcome</h1>" + "<p>Youve asked this <br />" + header.replace("\n", "<br />")
		+ "</HTML>";
	}

}

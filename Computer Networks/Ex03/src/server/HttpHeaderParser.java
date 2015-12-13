package server;

public class HttpHeaderParser {

	public String parseForResponse(String header) {
		return "<HTML>"
				+ "<h1>Welcome</h1>"
				+ "<p>Youve asked this <br />"
				+ header.replace("\n", "<br />")
				+ "</HTML>";
	}

}

package httpObjects;

import java.util.LinkedHashMap;

public class HTTPRequest {

	private String type = null;
	private String body = null;
	private LinkedHashMap<String, String> headers = new LinkedHashMap<>();
	private String setRequestedResource = null;
	private String RequestHTTPVersion = null;

	public void setRequestType(String type) {
		this.type = type;
	}

	public String getRequestType() {
		return this.type;
	}

	public void setRequestBody(String body) {
		this.body = body;
	}

	public String getRequestBody() {
		return this.body;
	}

	public void setGenericHeaders(String key, String value) {
		this.headers.put(key.toUpperCase(), value);
	}

	public String getGenericHeaders(String key) {
		return this.headers.get(key);
	}

	public void setRequestedResource(String resource) {
		this.setRequestedResource = resource;
	}

	public String getRequestedResource() {
		return this.setRequestedResource;
	}

	public void setHTTPVersion(String ver) {
		this.RequestHTTPVersion = ver;
	}

	public String getHTTPVersion() {
		return this.RequestHTTPVersion;

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getRequestType() + " " + this.getRequestedResource() + " " + this.getHTTPVersion() + "\r\n");
		for (String key : headers.keySet()) {
			sb.append(key + " : " + headers.get(key) + "\r\n");
		}
		if (this.getRequestBody() != null) {
			sb.append("\r\n" + this.getRequestBody());
		}
		
		return sb.append("\r\n").toString();
	}

}

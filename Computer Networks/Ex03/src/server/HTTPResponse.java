package server;

import java.util.LinkedHashMap;

public class HTTPResponse {

	// A bit of overkill I know but other DS destroy insertion order
	public LinkedHashMap<String, String> fields = new LinkedHashMap<>();
	private String responseStatus = null;
	private String responseBody = null;

	public HTTPResponse() {
	}

	public void setStatus(String status) {
		this.responseStatus = status;
	}

	public String getStatus() {
		return this.responseStatus;
	}

	public String getBody() {
		return this.responseBody;
	}

	public void setBody(String body) {
		this.responseBody = body;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(responseStatus);
		for (String k : fields.keySet()) {
			if (fields.get(k) == "") {
				res.append(k);
			} else {
				res.append(k + " : " + fields.get(k));
			}

			res.append("\n");
		}
		res.append('\n');
		res.append(responseBody + '\n');
		return res.toString();
	}
}

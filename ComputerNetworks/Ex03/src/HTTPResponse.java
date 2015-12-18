
import java.util.LinkedHashMap;

public class HTTPResponse {

	// A bit of overkill I know but other DS destroy insertion order
	public LinkedHashMap<String, String> fields = new LinkedHashMap<>();
	private String responseStatus = null;
	private byte[] responseBody = null;

	public HTTPResponse() {
	}

	public void setStatus(String status) {
		this.responseStatus = status;
	}

	public String getStatus() {
		return this.responseStatus;
	}

	public byte[] getBody() {
		return this.responseBody;
	}

	public void setBody(byte[] body) {
		this.responseBody = body;
	}

	public String headerToString() {
		StringBuilder res = new StringBuilder();
		res.append(this.responseStatus + "\n");
		for (String k : fields.keySet()) {
			if (fields.get(k) == "") {
				res.append(k);
			} else {
				res.append(k + " : " + fields.get(k));
			}
			res.append('\n');
		}
		
		// CRITICAL TO ADD THE GOD DAMN SPACE!
		res.append('\n');
		return res.toString();
	}

	public byte[] generateBytes() {
		return composeByteBasedResponse(this.headerToString().getBytes(), this.responseBody);
	}

	private byte[] composeByteBasedResponse(byte[] head, byte[] body) {
		byte[] merged = new byte[head.length + (body.length > 0 ? body.length : 0)];
		System.arraycopy(head, 0, merged, 0, head.length);
		System.arraycopy(body, 0, merged, head.length, body.length);
		return merged;
	}

	/*
	 * Return the size Octet based
	 */
	public int getBodySize() {
		return this.getBody().length;
	}
}

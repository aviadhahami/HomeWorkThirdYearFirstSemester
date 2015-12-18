
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

	@Override
	public String toString() {
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
		return res.toString();
	}

	public byte[] generateBytes() {
		// TODO Auto-generated method stub
		return null;
	}
}

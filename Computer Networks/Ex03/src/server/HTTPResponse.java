package server;

import java.util.LinkedHashMap;

public class HTTPResponse {

	// A bit of overkill I know but other DS destroy insertion order
	public LinkedHashMap<String, String> fields = new LinkedHashMap<>();

	public HTTPResponse() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (String k : fields.keySet()) {
			if (fields.get(k) == "") {
				res.append(k);
			} else {
				res.append(k + " : " + fields.get(k));
			}

			res.append("\n");
		}
		return res.toString();
	}
}

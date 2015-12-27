import java.util.Iterator;

public class databaseGetterController implements RouteController {

	@Override
	public byte[] GET(String query) {
		StringBuilder json = new StringBuilder();

		if (query == null) {

			// Means get all
			json.append("[");
			String k;
			for (Iterator<String> collectionItr = Database.getAll().iterator(); collectionItr.hasNext();) {
				k = collectionItr.next();
				json.append("{");
				json.append("\"name\" : \"" + k + "\",");
				json.append("\"age\" : \"" + Database.get(k));
				if (collectionItr.hasNext()) {
					json.append("\"},");
				} else {
					json.append("\"}");
				}

			}
			json.append("]");
		} else if (!query.startsWith("name=")) {

			// If bad query
			return new String("").getBytes();
		} else {

			String name = query.replaceAll("^name=([a-zA-Z]+)(&*).*", "$1");
			json.append("[");
			String k;
			for (Iterator<String> dbIter = Database.getAll().iterator(); dbIter.hasNext();) {
				k = dbIter.next();

				if (k.indexOf(name) > -1) {
					json.append("{");
					json.append("\"name\" : \"" + k + "\",");
					json.append("\"age\" : \"" + Database.get(k) + "\"");
					json.append("},");
				}
			}
			String testJSON = json.toString();
			if (testJSON.endsWith(",")) {
				json.delete(testJSON.length() - 1, testJSON.length());
			}
			json.append("]");

		}
		return json.toString().getBytes();
	}

	@Override
	public byte[] POST(String body) {
		return new byte[0];
	}

	@Override
	public byte[] UPDATE(String body) {
		return new byte[0];
	}

}

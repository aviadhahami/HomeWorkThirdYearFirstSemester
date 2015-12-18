
public class databaseController implements RouteController {

	@Override
	public byte[] invoke() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] GET(String query) {
		Console.log(query);
		StringBuilder json = new StringBuilder();

		if (query == null) {

		} else {
			String name = query.replaceAll("^name=([a-zA-Z]+)&.*", "$1");
			if (name == query) {
				json.append("{}");
			} else {
				json.append("{");
				json.append("name : " + name + ",");
				json.append("age : " + Database.get(name));
				json.append("}");
			}
		}
		return json.toString().getBytes();
	}

	@Override
	public byte[] POST(String body) {
		// TODO Auto-generated method stub
		return new byte[0];
	}

	@Override
	public byte[] UPDATE(String body) {
		return new byte[0];
	}

}

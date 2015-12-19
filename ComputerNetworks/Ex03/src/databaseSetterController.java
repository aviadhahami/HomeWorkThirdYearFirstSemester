
public class databaseSetterController implements RouteController {

	@Override
	public byte[] GET(String query) {
		// TODO Auto-generated method stub
		return new byte[0];
	}

	@Override
	public byte[] POST(String body) {
		if (body == null || !body.matches("^name=(.+)&age=([0-9]+)$")) {
			Console.log("body is " + body);
			throw new IllegalArgumentException("Bad request body");
		}
		String[] vals = body.split("&");
		String name = vals[0].replaceAll("^name=(.+)", "$1");
		String age = vals[1].replaceAll("^age=([0-9]+)", "$1");
		Database.insert(name, age);
		// return ok
		StringBuilder sb = new StringBuilder();

		sb.append("{");
		sb.append("\"name\" : \"" + name + "\",");
		sb.append("\"age\" : \"" + age + "\"");
		sb.append("}");

		return new byte[0];//sb.toString().getBytes();
	}

	@Override
	public byte[] UPDATE(String body) {
		// TODO Auto-generated method stub
		return new byte[0];
	}

}

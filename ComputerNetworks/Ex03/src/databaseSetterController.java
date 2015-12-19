
public class databaseSetterController implements RouteController {

	@Override
	public byte[] GET(String query) {
		// TODO Auto-generated method stub
		return new byte[0];
	}

	@Override
	public byte[] POST(String body) {
		if(body == null || !body.matches("^name=(.+)&age=([0-9]+)$")){
			throw new IllegalArgumentException("Bad request body");
		}
		String[] vals = body.split("&");
		String name = vals[0].replaceAll("^name=(.+)", "$1");
		String age = vals[1].replaceAll("^age=([0-9]+)", "$1");
		Console.log("name is " + name);
		Console.log("Age is " + age);
		Database.insert(name, age);
		
		// return ok
		return new byte[0];
	}

	@Override
	public byte[] UPDATE(String body) {
		// TODO Auto-generated method stub
		return new byte[0];
	}

}

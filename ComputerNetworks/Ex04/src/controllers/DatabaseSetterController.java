package controllers;
import console.*;
import interfaces.*;
import database.*;
import httpObjects.HTTPRequest;

public class DatabaseSetterController implements RouteController {

	@Override
	public String contentTypeByMethod(String str) {
		String res;
		switch(str){
		case("GET"):
			res="html";
			break;
		case("POST"):
			res="html";
			break;
		case("UPDATE"):
			res="html";
			break;
		default:
			res="html";
		}
		return res;
	}

	@Override
	public byte[] GET(HTTPRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] POST(HTTPRequest req) {
		String body = req.getRequestBody();
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

		return sb.toString().getBytes();
	}

	@Override
	public byte[] UPDATE(HTTPRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}

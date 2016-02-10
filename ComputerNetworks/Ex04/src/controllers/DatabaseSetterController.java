package controllers;

import console.*;
import interfaces.*;
import server.ResponseHandler;
import database.*;
import httpObjects.HTTPRequest;
import httpObjects.HTTPResponse;

public class DatabaseSetterController implements RouteController {

	@Override
	public byte[] GET(HTTPRequest req, HTTPResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] POST(HTTPRequest req, HTTPResponse res) {
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
		res.setStatus(ResponseHandler.getResponseHeaderByCode(200));
		return sb.toString().getBytes();
	}

	@Override
	public byte[] UPDATE(HTTPRequest req, HTTPResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String contentTypeByMethod(String str) {
		String res;
		switch (str) {
		case ("GET"):
			res = "html";
			break;
		case ("POST"):
			res = "html";
			break;
		case ("UPDATE"):
			res = "html";
			break;
		default:
			res = "html";
		}
		return res;
	}

}

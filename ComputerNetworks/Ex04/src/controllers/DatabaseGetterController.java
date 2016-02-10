package controllers;

import java.net.URI;
import java.util.Iterator;

import database.Database;
import httpObjects.HTTPRequest;
import interfaces.RouteController;
import utils.PathUtils;

public class DatabaseGetterController implements RouteController {

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

	@Override
	public byte[] GET(HTTPRequest req) {
		StringBuilder json = new StringBuilder();
		String query = null;
		try {
			URI requestedResource = PathUtils.toFullPath(req.getRequestedResource());
			query = requestedResource.getQuery();
		} catch (Exception e) {
			// We don't really care
		}

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
	public byte[] POST(HTTPRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] UPDATE(HTTPRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}

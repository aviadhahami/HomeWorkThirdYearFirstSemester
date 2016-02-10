package interfaces;

import httpObjects.HTTPRequest;
import httpObjects.HTTPResponse;

public interface RouteController {

	public byte[] GET(HTTPRequest req,HTTPResponse res);

	public byte[] POST(HTTPRequest req, HTTPResponse res);

	public byte[] UPDATE(HTTPRequest req,HTTPResponse res);

	public String contentTypeByMethod(String string);

}

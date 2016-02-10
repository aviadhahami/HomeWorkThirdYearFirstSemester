package interfaces;

import httpObjects.HTTPRequest;

public interface RouteController {

	public byte[] GET(HTTPRequest req);

	public byte[] POST(HTTPRequest req);

	public byte[] UPDATE(HTTPRequest req);

	public String contentTypeByMethod(String string);

}

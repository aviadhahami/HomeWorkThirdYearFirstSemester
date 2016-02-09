package interfaces;

public interface RouteController {

	public byte[] GET(String query);

	public byte[] POST(String body);

	public byte[] UPDATE(String body);

	public String contentTypeByMethod(String string);

}

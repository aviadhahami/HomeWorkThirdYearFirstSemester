
public interface RouteController {

	public byte[] invoke();

	public byte[] GET(String query);

	public byte[] POST(String body);

	public byte[] UPDATE(String body);

}



public class Client {

	public int permissionLevel = 0;
	public String UA = "";

	@Override
	public String toString() {
		return "User level : " + this.permissionLevel + "\n" + this.UA;
	}
}

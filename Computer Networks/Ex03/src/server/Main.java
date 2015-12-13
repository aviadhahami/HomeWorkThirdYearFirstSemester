package server;

/**
 *
 * @author aviadh
 */
public class Main {

	public static void main(String[] args) {

		// TODO: load config here and pass it to server

		int port = 9000;

		int poolSize = 2;
		Server s = new Server(port, poolSize);
		s.listen();
	}
}

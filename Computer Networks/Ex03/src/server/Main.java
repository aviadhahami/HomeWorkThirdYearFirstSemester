package server;

/**
 *
 * @author aviadh
 */
public class Main {

    public static void main(String[] args) {
    	
    	// TODO: load config here and pass it to server
    	
        int port = 9000;

        Server s = new Server(port);
        s.listen();
    }
}

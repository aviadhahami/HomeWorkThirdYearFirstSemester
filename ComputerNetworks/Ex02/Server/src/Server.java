
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author aviadh
 */
public class Server {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void listen() {
        NotifyConsole("Listening on " + port);
        try (ServerSocket server = new ServerSocket(port)) {
            Socket connection;
            boolean listenFlag = true;
            
            // Listen to incoming connections
            while (listenFlag) {
                connection = server.accept();
                System.out.println("Client connected, generating thread");
                ConnectionHandler connectionHandler = new ConnectionHandler(connection);
                connectionHandler.start();
            }
        } catch (Exception e) {
            System.out.println("Server.listen() exception" + e);
            System.exit(1);
        }
    }

    // Display to console
    private void NotifyConsole(String msg) {
        System.out.println(msg);
    }

}

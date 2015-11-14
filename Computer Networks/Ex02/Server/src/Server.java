
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        NotifyConsole(port);
        try {
            ServerSocket server = new ServerSocket(port);
            Socket connection;
            while (true) {
                connection = server.accept();
                System.out.println("connected!");
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Server.listen() exception" + e);
            System.exit(1);
        }
    }

    // Notifies what port the server is on
    private void NotifyConsole(int port) {
        System.out.println("Started server on port: " + port);
    }

}

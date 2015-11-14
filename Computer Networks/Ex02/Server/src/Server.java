
import java.io.IOException;
import java.net.ServerSocket;

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

    private int port;

    public Server(int port) {
        this.port = port;
    }

       public void run() {
        try {
            initServer(port);
        } catch (Exception e) {
            System.out.println("Server.run() exception" + e);
            System.exit(1);
        }
    }

    private void initServer(int port) throws IOException {
        System.out.println("Running");

    }

}

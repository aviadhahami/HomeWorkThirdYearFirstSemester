
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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

    public void run() {
        int defaultPort = 9000;
        initServer(defaultPort);
    }

    public void run(int port) {
        initServer(port);
    }

    private void initServer(int port) {
       System.out.println("Running");
    }
}

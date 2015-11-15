
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aviadh
 */
public class ConnectionHandler extends Thread {

    private final Socket socket;
    private final String WELCOME_MESSAGE = "Computer Networks: November 2015:"
            + "Aviya Sela & Aviad Hahami\n";
    private final List<String> inputToken = Arrays.asList("q", "quit", "exit");

    public ConnectionHandler(Socket connection) {
        this.socket = connection;
    }

    @Override
    public void run() {
        try {

            OutputStream out = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(out, true);
            pw.print(WELCOME_MESSAGE);
            pw.println("=================================================");
            pw.println("| You can type exit,q or quit in order to leave |");
            pw.println("=================================================");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (checkExitToken(line.toLowerCase())) {
                    break;
                }
                pw.println(line);
            }
            pw.println("~~And remember, respect is everything (GTA2)~~");
            closeConnection();
        } catch (IOException e) {
            System.err.println(e);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void closeConnection() {
        try {
            this.socket.close();
            NotifyConsole("Client disconnected, killing thread id " + this.getId());
        } catch (Exception e) {
            System.err.println("Error closing socket" + e);
        }
    }
    // Display to console

    private void NotifyConsole(String msg) {
        System.out.println(msg);
    }

    private boolean checkExitToken(String str) {
        return inputToken.indexOf(str) > -1;
    }

}

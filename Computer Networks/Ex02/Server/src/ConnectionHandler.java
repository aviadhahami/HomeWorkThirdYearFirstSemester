
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class ConnectionHandler extends Thread {

    private final Socket socket;
    private final String WELCOME_MESSAGE = "Computer Networks: November 2015:"
            + "Aviya Sela & Aviad Hahami\n";

    public ConnectionHandler(Socket connection) {
        this.socket = connection;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out
                    = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(WELCOME_MESSAGE);
            DataInputStream in
                    = new DataInputStream(socket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                out.writeUTF(line);
            }

        } catch (IOException e) {
            System.err.println(e);
        } catch (Exception e) {
            System.err.println(e);
        }

        closeConnection();

    }

    private void closeConnection() {
        try {
            this.socket.close();
        } catch (Exception e) {
            System.err.println("Error closing socket" + e);
        }
    }

}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aviadh
 */
public class ConnectionHandler extends Thread {

    private final Socket socket;
    private final String WELCOME_MESSAGE = "Computer Networks: November 2015:"
            + "Aviya Sela & Aviad Hahami\n";
    private final List<String> inputToken = Arrays.asList("q", "quit", "exit");
    private final String TOKENS_MESSAGE
            = "=================================================\n"
            + "| You can type exit,q or quit in order to leave |\n"
            + "=================================================\n";
    private String EXIT_MESSAGE
            = "=================================================\n"
            + "|             Thank you and goodbye!              |\n"
            + "=================================================\n";

    public ConnectionHandler(Socket connection) {
        this.socket = connection;
    }

    @Override
    public void run() {
        try {
            // Init readers and writers
            OutputStream out = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(out, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line;

            // Print welcome message
            pw.print(WELCOME_MESSAGE);
            pw.println(TOKENS_MESSAGE);
            while ((line = reader.readLine()) != null) {

                NotifyConsole(line);
                if (checkExitToken(line.toLowerCase())) {
                    break;
                } else if (line.equals("") || line.equals(" ")) {
                    NotifyConsole("Empty message, Posting");
                    pw.println(sb.toString());
                    sb.delete(0, sb.length());
                } else {
                    sb.append(line).append("\n");
                }

            }
            pw.println(EXIT_MESSAGE);
            closeConnection();
        } catch (IOException e) {
            System.err.println(e);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // Wrapper to close the connection
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

    // Check for exit token
    private boolean checkExitToken(String str) {
        return inputToken.indexOf(str) > -1;
    }

}


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
    private final String WELCOME_MESSAGE
            = "Computer Networks: November 2015 :"
            + "Aviya Sela <302221403> & Aviad Hahami <302188347>\n";
    private final String TOKENS_MESSAGE
            = "=================================================\n"
            + "| You can type exit,q or quit in order to leave |\n"
            + "=================================================\n";
    private final String EXIT_MESSAGE
            = "=================================================\n"
            + "|             Thank you and goodbye!              |\n"
            + "=================================================\n";
    private final List<String> inputToken = Arrays.asList("q", "quit", "exit");
    private boolean firstMessageFlag = true;
    private final String SERVER_RESPONSE_SECTION
            = "~~~~~~ YOUR MESSAGE ~~~~~~";

    public ConnectionHandler(Socket connection) {
        this.socket = connection;
    }

    @Override
    public void run() {
        try {
            // Init readers and writers
            OutputStream out = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(out, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line;

            // Print welcome message
            pw.print(WELCOME_MESSAGE);
            pw.println(TOKENS_MESSAGE);

            // Text feed
            while ((line = reader.readLine()) != null) {
                // Output into console for server side
                NotifyConsole(line);
                if (firstMessageFlag) {

                    // We need to escsape the telnet protocol commands on first
                    // input in the stream, the commands end with '#'
                    NotifyConsole("first read, escaping telnet protocol cmds");

                    // Toggle flag
                    firstMessageFlag = !firstMessageFlag;

                    // Find the hash and trim it
                    line = line.substring(line.indexOf("#") + 1, line.length());

                    // Still check for exit token
                    if (checkExitToken(line.toLowerCase())) {
                        break;
                    } else {
                        sb.append(line).append("\n");
                    }

                } else if (checkExitToken(line.toLowerCase())) {
                    break;
                } else if (line.equals("") || line.equals(" ")) {
                    NotifyConsole("Empty message, Posting");
                    pw.println(SERVER_RESPONSE_SECTION);
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

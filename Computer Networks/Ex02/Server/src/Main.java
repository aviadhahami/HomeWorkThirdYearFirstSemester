
/**
 *
 * @author aviadh
 */
public class Main {

    public static void main(String[] args) {
        int port = 9000;

        Server s = new Server(port);
        s.listen();
    }
}

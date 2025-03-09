
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) throws UnknownHostException, IOException {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Calculator calculator = new Calculator(out);
            calculator.start();
        }
    }
}

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Create the Calculator (GUI) with the PrintWriter
            Calculator calculator = new Calculator(out);

            // Register a shutdown hook to close the socket when the GUI exits
            calculator.setOnWindowClosed(() -> {
                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                        System.out.println("Socket closed gracefully.");
                    }
                } catch (IOException e) {
                }
            });

            calculator.start(); // Start the GUI

        } catch (IOException e) {
        }
    }
}
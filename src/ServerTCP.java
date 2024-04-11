import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        HotelManager hotelManager = new HotelManager();

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server listening on port " + PORT + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, hotelManager);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        GestioneHotel gestioneHotel = new GestioneHotel();

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server listening on port " + PORT + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                GestioneClient gestioneClient = new GestioneClient(clientSocket, gestioneHotel);
                gestioneClient.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

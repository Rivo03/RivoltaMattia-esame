import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final HotelManager hotelManager;

    public ClientHandler(Socket clientSocket, HotelManager hotelManager) {
        this.clientSocket = clientSocket;
        this.hotelManager = hotelManager;
    }

    
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Inserire uno dei seguenti comandi: all, sorted_by_name, with_spa");

            while (true) {
                String command = in.readLine();
                if (command == null || command.equalsIgnoreCase("exit")) {
                    break;
                }
                command = command.toLowerCase(); // Converti il comando in minuscolo
                switch (command) {
                    case "all":
                        hotelManager.sendAll(out);
                        break;
                    case "sorted_by_name":
                        hotelManager.sendSortedByName(out);
                        break;
                    case "with_spa":
                        hotelManager.sendWithSpa(out);
                        break;
                    default:
                        out.println("Comando non valido. Comandi possibili: all, sorted_by_name, with_spa");
                        break;
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


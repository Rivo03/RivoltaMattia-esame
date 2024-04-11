import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GestioneClient extends Thread {
    private final Socket clientSocket;
    private final GestioneHotel gestioneHotel;

    public GestioneClient(Socket clientSocket, GestioneHotel gestioneHotel) {
        this.clientSocket = clientSocket;
        this.gestioneHotel = gestioneHotel;
    }


    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Inserire uno dei seguenti comandi: all, sorted_by_name, with_spa");
            out.println();
            while (true) {
                String command = in.readLine();
                if (command == null || command.equalsIgnoreCase("exit")) {
                    break;
                }
                command = command.toLowerCase();
                switch (command) {
                    case "all":
                        gestioneHotel.sendAll(out);
                        break;
                    case "sorted_by_name":
                        gestioneHotel.sendSortedByName(out);
                        break;
                    case "with_spa":
                        gestioneHotel.sendWithSpa(out);
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


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HotelManager {
    private final List<Hotel> hotels;

    public HotelManager() {
        hotels = new ArrayList<>();
        hotels.add(new Hotel("Francoforte A", true));
        hotels.add(new Hotel("Miramare", false));
        hotels.add(new Hotel("Argento", true));
        hotels.add(new Hotel("Sole", false));
        hotels.add(new Hotel("Mariano", true));
    }

    public void sendAll(PrintWriter out) {
        for (Hotel hotel : hotels) {
            out.println(hotel.getNome() + " - Spa: " + (hotel.hasSpa() ? "Yes" : "No"));
        }
        out.println();  // Invio di una riga vuota per segnalare la fine della lista
    }

    public void sendSortedByName(PrintWriter out) {
        List<Hotel> sortedHotels = new ArrayList<>(hotels);
        sortedHotels.sort(Comparator.comparing(Hotel::getNome));
        sendAll(out, sortedHotels);
    }

    public void sendWithSpa(PrintWriter out) {
        List<Hotel> spaHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.hasSpa()) {
                spaHotels.add(hotel);
            }
        }
        sendAll(out, spaHotels);
    }

    private void sendAll(PrintWriter out, List<Hotel> hotels) {
        for (Hotel hotel : hotels) {
            out.println(hotel.getNome() + " - Spa: " + (hotel.hasSpa() ? "Yes" : "No"));
        }
        out.println();  // Invio di una riga vuota per segnalare la fine della lista
    }
}

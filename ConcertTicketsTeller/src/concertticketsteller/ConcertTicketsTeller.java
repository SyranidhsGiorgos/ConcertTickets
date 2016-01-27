 /**
 * Αλίκη Δαγλή 44841
 * Ανδρέας Μπελόκας 40443
 * Γιώργος ΣΥΡΑΝΙΔΗΣ 39280
 **/
package concertticketsteller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcertTicketsTeller {

    private Socket socket;

    private static final int PORT = 4444;
    private static final String IP = "localhost";
    ObjectInputStream in;
    ObjectOutputStream out;

    boolean connect() {
        try {
            socket = new Socket(IP, PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ConcertTicketsTeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            write("TELLER");
            if (read().equals("OK")) {
                return true;
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConcertTicketsTeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Object read() throws IOException, ClassNotFoundException {
        return in.readObject();

    }

    public void write(Object obj) throws IOException {
        out.writeObject(obj);
        out.flush();

    }

    private void close() throws IOException {
        in.close();
        out.close();
        socket.close();

    }

    void exit() throws IOException {
        write("EXIT");
        close();
    }

    String[] getTiers(String concert) {
        try {
            write("GET_TIERS");
            write(concert);
            List<String> concerts = new ArrayList<>();
            String read;
            while (!(read = (String) read()).equals("END")) {
                concerts.add(read);
            }
            String[] a = new String[concerts.size()];
            return concerts.toArray(a);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConcertTicketsTeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String[]{};
    }

    String[] getConcerts() {
        try {
            write("GET_CONCERTS");
            List<String> concerts = new ArrayList<>();
            String read;
            while (!(read = (String) read()).equals("END")) {
                concerts.add(read);
            }
            String[] a = new String[concerts.size()];
            return concerts.toArray(a);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConcertTicketsTeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String[]{};
    }

    String[] getDates(String concert) {
        try {
            write("GET_DATES");
            write(concert);
            List<String> concerts = new ArrayList<>();
            String read;
            while (!(read = (String) read()).equals("END")) {
                concerts.add(read);
            }
            String[] a = new String[concerts.size()];
            return concerts.toArray(a);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConcertTicketsTeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String[]{};
    }

    String[] getSeats(String concert, String tier, String date) {
        try {
            write("GET_SEATS");
            write(concert + ":" + tier);
            write(date);
            List<String> concerts = new ArrayList<>();
            String read;
            while (!(read = (String) read()).equals("END")) {
                concerts.add(read);
            }
            String[] a = new String[concerts.size()];
            return concerts.toArray(a);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConcertTicketsTeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String[]{};
    }

    boolean addTicket(String concert, String tier, String date, String seat, String name, String discount) {
        try {
            write("ADD_TICKET");
            write(name);
            write(tier);
            write(seat);
            write(discount);
            write(concert);
            write(date);
            if (((String) read()).equals("OK")) {
                return true;
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConcertTicketsTeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    String getTicket(String name,String concert,String date) {

        try {
            write("GET_TICKET");
            write(name);
            write(concert);
            write(date);
            return (String) read();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConcertTicketsTeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "NOT FOUND";
    }

}

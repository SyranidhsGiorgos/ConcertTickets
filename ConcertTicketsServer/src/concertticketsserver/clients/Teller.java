 /**
 * Αλίκη Δαγλή 44841
 * Ανδρέας Μπελόκας 40443
 * Γιώργος ΣΥΡΑΝΙΔΗΣ 39280
 **/
package concertticketsserver.clients;

import concertticketsserver.tools.SQL;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Teller extends Client {

    SQL sql = new SQL();
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Teller() {
    }

    public Teller(ObjectInputStream in, ObjectOutputStream out) {
        this.in = in;
        this.out = out;
        write("OK");
    }

    @Override
    public Object command(Object read) {
        switch ((String) read) {
            case "GET_CONCERTS":
                return getConcerts();
            case "GET_DATES":
                return getDates();
            case "GET_SEATS":
                return getSeats();
            case "ADD_TICKET":
                return addTicket();
            case "GET_TICKET":
                return getTicket();
            case "GET_TIERS":
                return getTiers();
            case "EXIT":
                return "EXIT";
            default:
                return "UNKNOWN";
        }
    }

    private Object getConcerts() {
        List<String> concerts = sql.getConcertNames();
        for (String concert : concerts) {
            write(concert);
        }
        return "END";
    }

    private Object getSeats() {
        String[] read = ((String) read()).split(":");
        String[] date = ((String) read()).split(":");
        List<String> seats = sql.getSeats(read[0], read[1], date);
        for (String seat : seats) {
            write(seat);
        }
        return "END";
    }

    private Object addTicket() {
        String name = (String) read();
        String tier_name = (String) read();
        int seat = Integer.parseInt((String) read());
        float discount = Float.parseFloat(((String) read())) / 100;
        String concert_name = (String) read();
        String[] date = ((String) read()).split(":");

        return sql.addTicket(name, tier_name, seat, discount, concert_name, date) ? "OK" : "ERROR";
    }

    private Object getTicket() {
        String customer = (String) read();
        String concert = (String) read();
        String[] date = ((String) read()).split(":");
        return sql.getTicket(concert, date, customer);
    }

    private Object getTiers() {
        List<String> tiers = sql.getTiersNames((String) read());
        for (String name : tiers) {
            write(name);
        }
        return "END";
    }

    private Object getDates() {
        List<String> dates = sql.getConcertDates((String) read());
        for (String date : dates) {
            write(date);
        }
        return "END";
    }

    public Object read() {
        try {
            return in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Teller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void write(Object obj) {
        try {
            out.writeObject(obj);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Teller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

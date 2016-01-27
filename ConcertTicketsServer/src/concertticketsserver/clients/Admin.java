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

public class Admin extends Client {

    SQL sql = new SQL();

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Admin() {
    }

    public Admin(ObjectInputStream in, ObjectOutputStream out) {
        this.in = in;
        this.out = out;
        write("OK");
    }

    @Override
    public Object command(Object read) {
        switch ((String) read) {
            case "GET_HALLS":
                return getHalls();
            case "ADD_HALL":
                return addHall();
            case "DELETE_HALL":
                return deleteHall();
            case "GET_TIERS":
                return getTiers();
            case "ADD_TIER":
                return addTier();
            case "EDIT_TIER":
                return editTier();
            case "GET_CONCERTS":
                return getConcerts();
            case "ADD_CONCERT":
                return addConcert();
            case "DELETE_CONCERT":
                return deleteConcert();
            case "GET_CONCERTS_DATES":
                return getConcertsDates();
            case "ADD_CONCERT_DATE":
                return addConcertDates();
            case "EDIT_CONCERT_DATES":
                return editConcertDate();
            case "GET_STATISTICS":
                return getStatistics();
            case "EXIT":
                return "EXIT";
            default:
                return "UNKNOWN";
        }
    }

    private Object getHalls() {
        List<String> halls = sql.getHalls();
        System.out.println(halls);
        for (String hall : halls) {
            write(hall);
        }
        return "END";
    }

    private Object getConcerts() {
        List<String> concerts = sql.getConcerts();
        for (String concert : concerts) {
            write(concert);
        }
        return "END";

    }

    private Object addConcert() {
        String read = (String) read();
        String[] concert = read.split(":");
        return sql.addConcert(concert[0], concert[1], concert[2]) ? "OK" : "ERROR";
    }

    private Object deleteConcert() {
        String concert = (String) read();
        return sql.removeConcert(concert)?"OK":"ERROR";
    }

    private Object getConcertsDates() {
        List<String> dates = sql.getConcertDates((String) read());
        for (String date : dates) {
            write(date);
        }
        return "END";
    }

    private Object addConcertDates() {
        String read = (String) read();
        System.out.println(read);
        String[] date = read.split(":");
        return sql.addConcertDate(date[0],date[3]+"-"+date[2]+"-"+date[1],date[4]+":"+date[5])? "OK" : "ERROR";
    }

    private Object editConcertDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object addHall() {
        String name = (String) read();
        return sql.addHall(name) ? "OK" : "ERROR";
    }

    private Object deleteHall() {
        String hall = (String) read();
        return sql.removeHall(hall)?"OK":"ERROR";
    }

    private Object getTiers() {

        List<String> tiers = sql.getTiers((String) read());
        for (String tier : tiers) {
            write(tier);
        }
        return "END";
    }

    private Object addTier() {
        String read = (String) read();
        System.out.println(read);
        String[] tier = read.split(":");
        return sql.addTier(tier[0], tier[1], Integer.parseInt(tier[2]), tier[3].equals("true"), Integer.parseInt(tier[4])) ? "OK" : "ERROR";
    }

    private Object editTier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object read() {
        try {
            return in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void write(Object obj) {
        try {
            out.writeObject(obj);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Object getStatistics() {
        List<String> statistics = sql.getStatistics();
        for (String stat : statistics) {
            write(stat);
        }
        return "END";
    }

}

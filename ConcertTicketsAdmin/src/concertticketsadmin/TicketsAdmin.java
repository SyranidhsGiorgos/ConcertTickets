 /**
 * Αλίκη Δαγλή 44841
 * Ανδρέας Μπελόκας 40443
 * Γιώργος ΣΥΡΑΝΙΔΗΣ 39280
 **/
package concertticketsadmin;

import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketsAdmin {

    private static final int PORT = 4444;
    private static final String IP = "localhost";
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket socket;

    boolean connect() {
        try {
            socket = new Socket(IP, PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            write("ADMIN");
            if (read().equals("OK")) {

                System.out.println(getHalls()[0]);
                return true;
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String[] getHalls() {
        try {
            write("GET_HALLS");
            List<String> halls = new ArrayList<>();
            String read = "";
            while (!(read = (String) read()).equals("END")) {
                halls.add(read);
            }
            String[] a = new String[halls.size()];
            return halls.toArray(a);
        } catch (IOException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String[]{};
    }

    public Object[][] getTiers(Object hall) {
        try {
            write("GET_TIERS");
            write(hall);
            List<String> tiers = new ArrayList<>();
            String read = "";
            while (!(read = (String) read()).equals("END")) {
                System.out.println(read);
                tiers.add(read);
            }
            String[][] tierArray = new String[tiers.size()][4];
            for (int i = 0; i < tiers.size(); i++) {
                tierArray[i] = tiers.get(i).split(":");
            }
            if (tiers.size() > 0) {
                System.out.println(tierArray[0][0]);
            }
            return tierArray;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Object[][]{new Object[]{null, null, null, null}};
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

    Object[][] gatDates(Object concert) {
        try {
            write("GET_CONCERTS_DATES");
            System.out.println((String) concert);
            write(concert);
            List<String> dates = new ArrayList<>();
            String read = "";
            while (!(read = (String) read()).equals("END")) {
                if (!read.equals("END")) {
                    dates.add(read);
                }
            }
            String[][] dateArray = new String[dates.size()][4];
            for (int i = 0; i < dates.size(); i++) {
                System.out.println(dates.get(i).split(":")[0]);
                dateArray[i] = new String[]{
                    dates.get(i).split(":")[0],
                    dates.get(i).split(":")[1],
                    dates.get(i).split(":")[2],
                    dates.get(i).split(":")[3] + ":"
                    + dates.get(i).split(":")[4]
                };
            }

            if (dates.size() > 0) {
                System.out.println(dateArray[0][0]);
            }
            return dateArray;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Object[][]{new Object[]{null, null, null, null}};
    }

    List<String> getConcerts() {
        List<String> concerts = new ArrayList<>();
        try {
            write("GET_CONCERTS");
            String read;
            while (!(read = (String) read()).equals("END")) {
                concerts.add(read);
            }
            return concerts;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return concerts;
    }

    boolean addTier(String hall, String name, String seats, String price, String numbered) {
        
        String tier = hall+":"+name+":"+seats+":"+numbered+":"+price;
        try {
            write("ADD_TIER");
            write(tier);
            return ((String)read()).equals("OK");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    boolean addConcert(String concert) {
        try {
            write("ADD_CONCERT");
            write(concert);
            return ((String)read()).equals("OK");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    boolean addHall(String hall) {
        try {
            write("ADD_HALL");
            write(hall);
            return ((String)read()).equals("OK");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    boolean adDDate(String concert, String day, String month, String year, String time) {
        String date = concert+":"+day+":"+month+":"+year+":"+time;
        try {
            write("ADD_CONCERT_DATE");
            write(date);
            return ((String)read()).equals("OK");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    boolean removeConcert(String concert) {
        try {
            write("DELETE_CONCERT");
            write(concert);
            return((String)read()).equals("OK");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    boolean removeHall(String hall) {
        try {
            write("DELETE_HALL");
            write(hall);
            return((String)read()).equals("OK");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    Object[][] getStatistics() {
        try {
            write("GET_STATISTICS");
            List<String> statistics = new ArrayList<>();
            String read;
            while (!(read = (String) read()).equals("END")) {
                System.out.println(read);
                statistics.add(read);
            }
            String[][] statArray = new String[statistics.size()][4];
            for (int i = 0; i < statistics.size(); i++) {
                statArray[i] = statistics.get(i).split(":");
            }
            if (statistics.size() > 0) {
                System.out.println(statArray[0][0]);
            }
            return statArray;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TicketsAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Object[][]{new Object[]{null, null, null}};
    }
}

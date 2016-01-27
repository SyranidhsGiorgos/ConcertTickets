 /**
 * Αλίκη Δαγλή 44841
 * Ανδρέας Μπελόκας 40443
 * Γιώργος ΣΥΡΑΝΙΔΗΣ 39280
 **/
package concertticketsserver.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQL {

    String connectionUrl = "jdbc:mysql://192.168.0.175:3306/ConcertTickets";
    String connectionUser = "Tickets";
    String connectionPassword = "Tickets";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public List<String> getHalls() {
        List<String> halls = new ArrayList<>();
        List<String> hall_names = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT NAME FROM HALLS");
            while (rs.next())
                hall_names.add(rs.getString("NAME"));
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return hall_names;
    }

    public List<String> getTiers(String hall) {
        List<String> tiers = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT TIERS.NAME,SEATS,NUMBERED,TIERS.BASE_PRICE FROM TIERS,HALLS WHERE HALLS.ID = HALL_ID AND HALLS.NAME = '" + hall + "'");
            while (rs.next())
                tiers.add(rs.getString("NAME") + ":" + rs.getInt("SEATS") + ":" + rs.getInt("BASE_PRICE") + ":" + (rs.getInt("NUMBERED") == 1 ? "True" : "False"));
            System.out.println(tiers);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return tiers;
    }

    public List<String> getConcerts() {
        List<String> concerts = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT CONCERTS.TITLE,CONCERTS.PERFORMER,HALLS.NAME AS HALL_NAME FROM CONCERTS,HALLS WHERE CONCERTS.HALL_ID=HALLS.ID");
            while (rs.next())
                concerts.add(rs.getString("CONCERTS.TITLE") + ":" + rs.getString("HALL_NAME") + ":" + rs.getString("CONCERTS.PERFORMER"));
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return concerts;
    }

    public List<String> getConcertNames() {
        List<String> concerts = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT DISTINCT TITLE FROM CONCERTS,CONCERTDATES WHERE CONCERTS.ID=CONCERTDATES.CONCERT_ID AND CONCERT_DATE>=CURDATE();");
            while (rs.next())
                concerts.add(rs.getString("CONCERTS.TITLE"));
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return concerts;
    }

    public List<String> getConcertDates(String concert) {

        List<String> dates = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT DATE_FORMAT(CONCERT_DATE,'%d:%m:%Y') AS CONCERT_DATE ,\n"
                    + "CONCERTDATES.TIME \n"
                    + "FROM CONCERTDATES,CONCERTS\n"
                    + "WHERE CONCERTDATES.CONCERT_ID=CONCERTS.ID \n"
                    + "AND CONCERT_DATE>=CURDATE() \n"
                    + "AND CONCERTS.TITLE='" + concert + "';");
            while (rs.next())
                dates.add(rs.getString("CONCERT_DATE") + ":" + rs.getString("CONCERTDATES.TIME"));
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return dates;

    }

    public List<String> getTickets(String concert) {
        List<String> tickets = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT TICKETS.NAME AS CUSTOMER_NAME,\n"
                    + "TIERS.NAME AS TIER_NAME,\n"
                    + "TICKETS.SEAT,\n"
                    + "TICKETS.DISCOUNT,\n"
                    + "CONCERTS.TITLE AS CONCERT_TITLE,\n"
                    + "TIERS.BASE_PRICE,\n"
                    + "HALLS.NAME AS HALL_NAME,  \n"
                    + "CONCERTDATES.CONCERT_DATE AS DATE,  \n"
                    + "CONCERTDATES.TIME AS TIME  \n"
                    + "FROM TICKETS,CONCERTS,TIERS,HALLS,CONCERTDATES\n"
                    + "WHERE TICKETS.TIER_ID=TIERS.ID \n"
                    + "AND TICKETS.CONCERT_ID=CONCERTS.ID \n"
                    + "AND TICKETS.DATE_ID=CONCERTDATES.ID \n"
                    + "AND CONCERTDATES.CONCERT_ID=CONCERTS.ID \n"
                    + "AND TIERS.HALL_ID=HALLS.ID \n"
                    + "AND CONCERTS.HALL_ID=HALLS.ID \n"
                    + "AND CONCERTS.TITLE='" + concert + "';");
            while (rs.next())
                tickets.add(rs.getString("CUSTOMER_NAME") + ":" + rs.getString("CONCERT_TITLE") + ":" + rs.getString("HALL_NAME") + ":" + rs.getString("TIER_NAME") + ":" + rs.getString("TICKETS.SEAT") + ":" + rs.getString("TIERS.BASE_PRICE") + ":" + rs.getString("TICKETS.DISCOUNT") + ":" + rs.getString("DATE") + ":" + rs.getString("TIME"));

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return tickets;
    }

    public String getTicket(String concert, String[] date, String customer) {
        String tickets = "NOT FOUND";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT TICKETS.NAME AS CUSTOMER_NAME,\n"
                    + "TIERS.NAME AS TIER_NAME,\n"
                    + "TICKETS.SEAT,\n"
                    + "TICKETS.DISCOUNT,\n"
                    + "CONCERTS.TITLE AS CONCERT_TITLE,\n"
                    + "TIERS.BASE_PRICE,\n"
                    + "HALLS.NAME AS HALL_NAME, \n"
                    + "CONCERTDATES.CONCERT_DATE AS DATE,  \n"
                    + "CONCERTDATES.TIME AS TIME  \n"
                    + "FROM TICKETS,CONCERTS,TIERS,HALLS,CONCERTDATES \n"
                    + "WHERE TICKETS.TIER_ID=TIERS.ID \n"
                    + "AND TICKETS.CONCERT_ID=CONCERTS.ID \n"
                    + "AND TICKETS.DATE_ID=CONCERTDATES.ID \n"
                    + "AND CONCERTDATES.CONCERT_ID=CONCERTS.ID \n"
                    + "AND TIERS.HALL_ID=HALLS.ID \n"
                    + "AND CONCERTS.HALL_ID=HALLS.ID \n"
                    + "AND CONCERTDATES.CONCERT_DATE='" + date[2] + "-" + date[1] + "-" + date[0] + "' \n"
                    + "AND CONCERTDATES.TIME='" + date[3] + ":" + date[4] + "' \n"
                    + "AND TICKETS.NAME='" + customer + "' \n"
                    + "AND CONCERTS.TITLE='" + concert + "';");
            rs.next();
            tickets = (rs.getString("CUSTOMER_NAME") + ":"
                    + rs.getString("CONCERT_TITLE") + ":"
                    + rs.getString("HALL_NAME") + ":"
                    + rs.getString("TIER_NAME") + ":"
                    + rs.getString("TICKETS.SEAT") + ":"
                    + rs.getString("TIERS.BASE_PRICE") + ":"
                    + rs.getString("TICKETS.DISCOUNT") + ":"
                    + rs.getString("DATE") + ":"
                    + rs.getString("TIME"));

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return tickets;
    }

    public boolean addHall(String name) {
        int id;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT NAME FROM HALLS WHERE NAME= '" + name + "';");
            if (rs.next() && rs.getString("NAME").equals(name)) {
                close();
                return false;
            }
            rs = stmt.executeQuery("SELECT MAX(ID) AS LAST FROM HALLS");
            rs.next();
            id = rs.getString("LAST") == null ? 0 : Integer.parseInt(rs.getString("LAST")) + 1;

            stmt.executeUpdate("INSERT INTO HALLS VALUES ('" + String.format("%05d", id) + "','" + name + "')");
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            close();
        }
        return true;
    }

    public boolean addTier(String hall, String name, int seats, boolean numbered, int price) {
        int id;
        String hall_id;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT NAME FROM TIERS WHERE NAME= '" + name + "';");
            if (rs.next() && rs.getString("NAME").equals(name)) {
                close();
                return false;
            }
            rs = stmt.executeQuery("SELECT MAX(ID) AS LAST FROM TIERS");
            rs.next();
            id = rs.getString("LAST") == null ? 0 : Integer.parseInt(rs.getString("LAST")) + 1;
            rs = stmt.executeQuery("SELECT ID FROM HALLS WHERE NAME= '" + hall + "';");
            rs.next();
            if ((hall_id = rs.getString("ID")) == null) {
                close();
                return false;
            }
            stmt.executeUpdate("INSERT INTO TIERS "
                    + "VALUES ('" + String.format("%05d", id) + "','" + hall_id + "'," + price + "," + seats + "," + (numbered ? 1 : 0) + ",'" + name + "')");
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            close();
        }
        return true;
    }

    public boolean addConcertDate(String concert, String date, String time) {
        int id;
        String hall_id;
        String concert_id;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT MAX(ID) AS LAST FROM CONCERTDATES");
            rs.next();
            id = rs.getString("LAST") == null ? 0 : Integer.parseInt(rs.getString("LAST")) + 1;
            rs = stmt.executeQuery("SELECT CONCERTS.ID FROM CONCERTS WHERE CONCERTS.TITLE='" + concert + "';");
            rs.next();
            if ((concert_id = rs.getString("CONCERTS.ID")) == null) {
                close();
                return false;
            }
            stmt.executeUpdate("INSERT INTO CONCERTDATES VALUES ('" + String.format("%05d", id) + "','" + date + "','" + concert_id + "','" + time + "')");
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            close();
        }
        return true;
    }

    public boolean addConcert(String performer, String hall_name, String title) {
        int id;
        String hall_id;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT TITLE FROM CONCERTS WHERE TITLE= '" + title + "';");
            if (rs.next() && rs.getString("TITLE").equals(title)) {
                close();
                return false;
            }
            rs = stmt.executeQuery("SELECT MAX(ID) AS LAST FROM CONCERTS");
            rs.next();
            id = rs.getString("LAST") == null ? 0 : Integer.parseInt(rs.getString("LAST")) + 1;
            rs = stmt.executeQuery("SELECT ID FROM HALLS WHERE NAME= '" + hall_name + "';");
            rs.next();
            if ((hall_id = rs.getString("ID")) == null) {
                close();
                return false;
            }
            stmt.executeUpdate("INSERT INTO CONCERTS VALUES ('" + performer + "','" + hall_id + "','" + title + "','" + String.format("%05d", id) + "')");
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            close();
        }
        return true;
    }

    public boolean addTicket(String name, String tier_name, int seat, float discount, String concert_name, String[] date) {
        int id;
        String tier_id;
        String concert_id;
        String date_id;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX(ID) AS LAST FROM TICKETS");
            rs.next();
            id = rs.getString("LAST") == null ? 0 : Integer.parseInt(rs.getString("LAST")) + 1;

            rs = stmt.executeQuery("SELECT ID FROM CONCERTS WHERE TITLE= '" + concert_name + "';");
            rs.next();
            if ((concert_id = rs.getString("ID")) == null) {
                close();
                return false;
            }

            rs = stmt.executeQuery("SELECT TIERS.ID FROM TIERS,CONCERTS "
                    + "WHERE TIERS.HALL_ID=CONCERTS.HALL_ID AND TIERS.NAME='" + tier_name + "';");
            rs.next();
            if ((tier_id = rs.getString("TIERS.ID")) == null) {
                close();
                return false;
            }

            rs = stmt.executeQuery("SELECT CONCERTDATES.ID FROM CONCERTDATES WHERE CONCERT_ID='" + concert_id + "';");
            rs.next();
            if ((date_id = rs.getString("CONCERTDATES.ID")) == null) {
                close();
                return false;
            }
            stmt.executeUpdate("INSERT INTO TICKETS "
                    + "VALUES ('" + String.format("%05d", id) + "','" + name + "','" + tier_id + "'," + seat + "," + discount + ",'" + concert_id + "','" + date_id + "')");
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            close();
        }
        return true;
    }

    void close() {
        try {
            if (rs != null)
                rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getSeats(String concert, String tier, String[] date) {
        List<String> seats = new ArrayList<>();
        try {
            String tierID;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT TIERS.NUMBERED,TIERS.ID FROM TIERS "
                    + "WHERE TIERS.NAME='" + tier + "' "
                    + "AND TIERS.HALL_ID = (SELECT CONCERTS.HALL_ID FROM CONCERTS WHERE CONCERTS.TITLE='" + concert + "');");
            rs.next();
            tierID = rs.getString("TIERS.ID");
            if ((rs.getString("TIERS.NUMBERED")).equals("1")) {
                rs = stmt.executeQuery("SELECT SEATS FROM TIERS "
                        + "WHERE TIERS.NAME='" + tier + "' "
                        + "AND TIERS.HALL_ID = (SELECT CONCERTS.HALL_ID FROM CONCERTS WHERE CONCERTS.TITLE='" + concert + "'); ");
                rs.next();
                int seatCount = rs.getInt("SEATS");
                for (int i = 1; i <= seatCount; i++)
                    seats.add(Integer.toString(i));
                rs = stmt.executeQuery("SELECT SEAT FROM TICKETS,CONCERTS,CONCERTDATES "
                        + "WHERE TICKETS.TIER_ID='" + tierID + "' "
                        + "AND TICKETS.CONCERT_ID=CONCERTS.ID "
                        + "AND CONCERTDATES.ID=TICKETS.DATE_ID "
                        + "AND CONCERTDATES.CONCERT_ID=CONCERTS.ID "
                        + "AND CONCERTDATES.CONCERT_DATE='" + date[2] + "-" + date[1] + "-" + date[0] + "' "
                        + "AND CONCERTDATES.TIME='" + date[3] + ":" + date[4] + "' "
                        + "AND CONCERTS.TITLE='" + concert + "';");
                while (rs.next()) {
                    seats.remove(rs.getString("SEAT"));
                    if (seats.isEmpty()) {
                        seats.add("FULL");
                        break;
                    }
                }
            } else {
                rs = stmt.executeQuery("SELECT SEATS FROM TIERS "
                        + "WHERE TIERS.NAME='" + tier + "' "
                        + "AND TIERS.HALL_ID = (SELECT CONCERTS.HALL_ID FROM CONCERTS WHERE CONCERTS.TITLE='" + concert + "') ");
                rs.next();
                int seatCount = rs.getInt("SEATS");
                rs = stmt.executeQuery("SELECT COUNT(SEAT) FROM TICKETS,CONCERTS,CONCERTDATES "
                        + "WHERE TICKETS.TIER_ID='" + tierID + "' "
                        + "AND TICKETS.CONCERT_ID=CONCERTS.ID "
                        + "AND CONCERTDATES.ID=TICKETS.DATE_ID "
                        + "AND CONCERTDATES.CONCERT_ID=CONCERTS.ID "
                        + "AND CONCERTDATES.CONCERT_DATE='" + date[2] + "-" + date[1] + "-" + date[0] + "' "
                        + "AND CONCERTDATES.TIME='" + date[3] + ":" + date[4] + "' "
                        + "AND CONCERTS.TITLE='" + concert + "'");
                rs.next();
                int reservedSeats = rs.getInt(1);
                if (seatCount == reservedSeats)
                    seats.add("FULL");
                else
                    seats.add("Seats Left :" + (seatCount - reservedSeats));
            }
            return seats;

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        seats.add("FULL");
        return seats;
    }

    public List<String> getTiersNames(String concert) {
        List<String> concerts = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT TIERS.NAME FROM TIERS,CONCERTS,HALLS WHERE CONCERTS.HALL_ID=HALLS.ID AND HALLS.ID=TIERS.HALL_ID AND CONCERTS.TITLE='" + concert + "';");
            while (rs.next())
                concerts.add(rs.getString("TIERs.NAME"));
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return concerts;
    }

    public boolean removeConcert(String concert) {

        String concert_id;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT ID FROM CONCERTS WHERE TITLE= '" + concert + "';");
            rs.next();
            if ((concert_id = rs.getString("ID")) == null) {
                close();
                return false;
            }

            stmt.executeUpdate("DELETE FROM CONCERTDATES WHERE CONCERT_ID='" + concert_id + "'");
            stmt.executeUpdate("DELETE FROM TICKETS WHERE CONCERT_ID='" + concert_id + "'");
            if (stmt.executeUpdate("DELETE FROM CONCERTS WHERE ID='" + concert_id + "'") != 0)
                return true;
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean removeHall(String hall) {
        String hall_id;
        String concert;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT ID FROM HALLS WHERE NAME= '" + hall + "';");
            rs.next();
            if ((hall_id = rs.getString("ID")) == null) {
                close();
                return false;
            }

            stmt.executeUpdate("DELETE FROM TIERS WHERE HALL_ID='" + hall_id + "'");
            rs = stmt.executeQuery("SELECT TITLE FROM CONCERTS WHERE HALL_ID= '" + hall_id + "';");
            if (rs.next())
                if ((concert = rs.getString("TITLE")) != null)
                    removeConcert(concert);
            if (stmt.executeUpdate("DELETE FROM HALLS WHERE ID='" + hall_id + "'") != 0)
                return true;
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<String> getStatistics() {
        
            List<String> stats = new ArrayList<>();
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT CONCERTS.ID,CONCERTS.TITLE, DATES.COUNT AS PERFOMANCES ,COMPLETIONS.PERCENTAGE\n"
                    + "FROM CONCERTS,\n"
                    + "(SELECT COUNT(CONCERTDATES.ID) AS COUNT,CONCERT_ID \n"
                    + "FROM CONCERTDATES GROUP BY CONCERT_ID)DATES,\n"
                    + "(SELECT SUM(T.COUNT/C.SUM*100) AS PERCENTAGE,CONCERT_ID \n"
                    + "FROM (SELECT COUNT(TICKETS.ID) AS COUNT,CONCERT_ID \n"
                    + "FROM TICKETS GROUP BY CONCERT_ID) T,\n"
                    + "(SELECT SUM(TIERS.SEATS) AS SUM,HALL_ID \n"
                    + "FROM TIERS GROUP BY HALL_ID) C, CONCERTS \n"
                    + "WHERE C.HALL_ID=CONCERTS.HALL_ID \n"
                    + "GROUP BY CONCERT_ID) COMPLETIONS\n"
                    + "WHERE CONCERTS.ID = DATES.CONCERT_ID \n"
                    + "AND DATES.CONCERT_ID=COMPLETIONS.CONCERT_ID \n"
                    + "AND COMPLETIONS.CONCERT_ID=CONCERTS.ID;");
            while(rs.next()){
                stats.add(rs.getString("CONCERTS.TITLE")+":"+rs.getString("PERFOMANCES")+":"+rs.getString("COMPLETIONS.PERCENTAGE"));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stats;
    }

}

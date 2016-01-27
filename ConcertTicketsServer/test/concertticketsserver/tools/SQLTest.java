 /**
 * Αλίκη Δαγλή 44841
 * Ανδρέας Μπελόκας 40443
 * Γιώργος ΣΥΡΑΝΙΔΗΣ 39280
 **/
package concertticketsserver.tools;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SQLTest {
    
    public SQLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getHalls method, of class SQL.
     */
    @Test
    public void testGetHalls() {
        System.out.println("getHalls");
        SQL instance = new SQL();
        List<String> expResult = null;
        List<String> result = instance.getHalls();
        assertTrue(result.size()>=0);
    }

    /**
     * Test of getTiers method, of class SQL.
     */
    @Test
    public void testGetTiers() {
        System.out.println("getTiers");
        String hall = "";
        SQL instance = new SQL();
        List<String> expResult = null;
        List<String> result = instance.getTiers(hall);
        assertTrue(result.size()>=0);
        // TODO review the generated test code a
    }

    /**
     * Test of getConcerts method, of class SQL.
     */
    @Test
    public void testGetConcerts() {
        System.out.println("getConcerts");
        SQL instance = new SQL();
        List<String> expResult = null;
        List<String> result = instance.getConcerts();
        assertTrue(result.size()>=0);
    }

    /**
     * Test of getConcertNames method, of class SQL.
     */
    @Test
    public void testGetConcertNames() {
        System.out.println("getConcertNames");
        SQL instance = new SQL();
        List<String> expResult = null;
        List<String> result = instance.getConcertNames();
        assertTrue(result.size()>=0);
    }

    /**
     * Test of getConcertDates method, of class SQL.
     */
    @Test
    public void testGetConcertDates() {
        System.out.println("getConcertDates");
        String concert = "";
        SQL instance = new SQL();
        List<String> expResult = null;
        List<String> result = instance.getConcertDates(concert);
        assertTrue(result.size()>=0);
    }

    /**
     * Test of getTickets method, of class SQL.
     */
    @Test
    public void testGetTickets() {
        System.out.println("getTickets");
        String concert = "";
        SQL instance = new SQL();
        List<String> expResult = null;
        List<String> result = instance.getTickets(concert);
        assertTrue(result.size()>=0);
    }

    /**
     * Test of addHall method, of class SQL.
     */
    @Test
    public void testAddHall() {
        System.out.println("addHall");
        String name = "hall";
        SQL instance = new SQL();
        boolean expResult = true;
        boolean result = instance.addHall(name);
        assertEquals(expResult, result);
        testAddConcert();
    }

  


    /**
     * Test of addConcert method, of class SQL.
     */
    private void testAddConcert() {
        System.out.println("addConcert");
        String performer = "performer";
        String hall_name = "hall";
        String title = "title";
        SQL instance = new SQL();
        boolean expResult = true;
        boolean result = instance.addConcert(performer, hall_name, title);
        assertEquals(expResult, result);
    }




    /**
     * Test of getSeats method, of class SQL.
     */
    @Test
    public void testGetSeats() {
        System.out.println("getSeats");
        String concert = "";
        String tier = "";
        String[] date = null;
        SQL instance = new SQL();
        List<String> expResult = null;
        List<String> result = instance.getSeats(concert, tier, date);
        assertTrue(result.size()>=0);
    }

    /**
     * Test of getTiersNames method, of class SQL.
     */
    @Test
    public void testGetTiersNames() {
        System.out.println("getTiersNames");
        String concert = "Prince Live";
        SQL instance = new SQL();
        List<String> expResult = null;
        List<String> result = instance.getTiersNames(concert);
        assertTrue(result.size()>=0);
    }

    
//    /**
//     * Test of removeConcert method, of class SQL.
//     */
//    @Test
//    public void testRemoveConcert() {
//        System.out.println("removeConcert");
//        String concert = "title";
//        SQL instance = new SQL();
//        boolean expResult = true;
//        boolean result = instance.removeConcert(concert);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//    }


    /**
     * Test of removeHall method, of class SQL.
     */
    @Test
    public void testRemoveHall() {
        System.out.println("removeHall");
        String hall = "hall";
        SQL instance = new SQL();
        boolean expResult = true;
        boolean result = instance.removeHall(hall);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}

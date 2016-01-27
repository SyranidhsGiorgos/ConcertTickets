 /**
 * Αλίκη Δαγλή 44841
 * Ανδρέας Μπελόκας 40443
 * Γιώργος ΣΥΡΑΝΙΔΗΣ 39280
 **/
package concertticketsteller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConcertTicketsTellerTest {
    
    public ConcertTicketsTellerTest() {
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
     * Test of connect method, of class ConcertTicketsTeller.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        ConcertTicketsTeller instance = new ConcertTicketsTeller();
        boolean expResult = true;
        boolean result = instance.connect();
        assertEquals(expResult, result);
    }
}

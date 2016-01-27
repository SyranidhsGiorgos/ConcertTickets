 /**
 * Αλίκη Δαγλή 44841
 * Ανδρέας Μπελόκας 40443
 * Γιώργος ΣΥΡΑΝΙΔΗΣ 39280
 **/
package concertticketsserver;

import java.io.IOException;
import java.net.ServerSocket;

class TicketServer {
    
    private static final int PORT = 4444;
    
    public TicketServer() {
        
        int portNumber = PORT;
        boolean listening = true;
        
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
	            new ServerThread(serverSocket.accept()).run();
	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
    
}

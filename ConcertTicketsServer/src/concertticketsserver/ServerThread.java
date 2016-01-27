 /**
 * Αλίκη Δαγλή 44841
 * Ανδρέας Μπελόκας 40443
 * Γιώργος ΣΥΡΑΝΙΔΗΣ 39280
 **/
package concertticketsserver;

import concertticketsserver.clients.Teller;
import concertticketsserver.clients.Client;
import concertticketsserver.clients.Admin;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class ServerThread implements Runnable {

    Socket accept;
    ObjectInputStream in;
    ObjectOutputStream out;
    Client cli;

    public ServerThread(Socket accept) {
        this.accept = accept;
        try {
            in = new ObjectInputStream(accept.getInputStream());
            out = new ObjectOutputStream(accept.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String input = (String) read();
        cli = input.equals("ADMIN") ? new Admin(in, out) : input.equals("TELLER") ? new Teller(in, out) : null;
        do {
            write(input = (String) cli.command(read()));
        } while (!input.equals("EXIT"));
        close();
    }

    public Object read() {
        try {
            return in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "EXIT";
    }

    public void write(Object obj) {
        try {
            out.writeObject(obj);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void close() {
        try {
            in.close();
            out.close();
            accept.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

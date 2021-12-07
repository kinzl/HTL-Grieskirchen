package net.htlgrieskirchen.pos2.lottotipps.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kimme
 */
public class Model {
    
    private final static int PORT = 4711;
    private final static String MESSAGE_SEPARATOR = ":";
    private final static File FILE = new File("tips.csv");
    
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private List<Tip> tips;
    
    public Model() {
        tips = new ArrayList<>();
    }
    
    private void connectToServer(String address) {
        try {
            Socket socket = new Socket(address, PORT);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            throw new RuntimeException("Verbindung zum Server nicht möglich.");
        }
    }
    
    private void disconnectFromServer() {
        try {
            in.close();
            out.close();
            if (socket != null) socket.close();
        } catch (IOException ex) {           
            throw new RuntimeException("Verbindung zum Server konnte nicht getrennt werden.");
        }
    }
    
    public void requestTipsFromServer(String address, int numberOfTips) {
        connectToServer(address);
        tips.clear();
        out.println("tips" + MESSAGE_SEPARATOR + numberOfTips);
        String response = in.nextLine();
        if ("<tips>".equals(response)) {
            response = in.nextLine();
            while (!"</tips>".equals(response)) {
                Tip tip = Tip.fromString(response);
                tips.add(tip);
                response = in.nextLine();
            }
        }
        disconnectFromServer();
    }
    
    public List getTips() {
        return Collections.unmodifiableList(tips);
    }
    
    public void saveTips() {
        if (tips == null || tips.isEmpty()) throw new RuntimeException("Keine Tips vorhanden.");
        try (PrintWriter pw = new PrintWriter(FILE)) {
            for(Tip tip : tips) {
                pw.println(tip);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Datei konnte nicht gespeichert werden.");
        }
    }
    
    public void loadTips() {
        tips.clear();
        try (Scanner s = new Scanner(FILE)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                tips.add(Tip.fromString(line));
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Datei wurde nicht gefunden.");
        }
    }
}

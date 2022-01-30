/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_mattle_augustin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bmattle19
 */
public class Model {

    private final static int PORT = 4711;

    private Socket socket;

    private PrintWriter out;
    private ObjectInputStream in;

    public Model() {

    }

    public void connectToServer(String address) {
        boolean running = false;
        Map<String, String> clubs = new TreeMap<>();
        try {
            socket = new Socket(address, PORT);
            in = new ObjectInputStream(socket.getInputStream());
            running = true;

        } catch (IOException ex) {
            throw new RuntimeException("Verbindung zum Server nicht möglich.");
        }
    }

    public Map<String, String> getMap() {
        Map<String, String> clubs = new TreeMap<>();
        try {

            clubs = (Map<String, String>) in.readObject();

        } catch (IOException ex) {
            throw new RuntimeException("Verbindung zum Server nicht möglich.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clubs;
    }

    private void disconnectFromServer() {
        try {
            in.close();
            out.close();
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Verbindung zum Server konnte nicht getrennt werden.");
        }
    }

//    
}

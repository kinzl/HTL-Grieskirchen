/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgkr.jbaumgartner19;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jbaumgartner19
 */
public class MyRunnable implements Runnable {

    private Socket socket;
    private Turnier turnier = new Turnier();
    private String fileName;
    
    
    public MyRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {

            while (true) {
                String s = br.readLine();
                while (s == null) {
                    s = br.readLine();
                }
                if (s.equals("fileName")) {
                    this.fileName = s;
                } else if (s.equals("requestApiKey")) {
                    out.writeObject(requestApiKey());
                    out.flush();
                } else if (s.equals("newTournament")) {
                    try {
                        HashSet hashSet = (HashSet) in.readObject();
                        String id = br.readLine();
                        newTournament(id, hashSet);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (s.equals("open")) {
                    openTournament(fileName);
                } else if(s.equals("removePlayer")){
                    
                    removePlayer(s);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] requestApiKey() {
        String[] array = null;
        try {
            Scanner scanner = new Scanner(new File("Apikeys.txt"));
            String s = "";
            while (scanner.hasNext()) {
                s = scanner.nextLine();
            }

            array = s.split(";");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public void newTournament(String id, HashSet hashSet) {

        turnier.addPlayer(id, hashSet, fileName);

    }
    
    public void openTournament(String fileName){
        turnier.open(fileName);
    }
    
    public void removePlayer(String id){
        turnier.removePlayer(id);
    }

}

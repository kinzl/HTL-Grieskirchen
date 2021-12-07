/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golfclubprojekt;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author kinzl
 */
public class MyRunable implements Runnable {

    
    private Socket socket;
    private Scanner scanner;
    private PrintWriter pw;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    private String fileName;
    private Turnier turnier;
    
    public MyRunable(Socket socket) {
        try {
            this.socket = socket;
            this.scanner = new Scanner(socket.getInputStream());
            this.pw = new PrintWriter(socket.getOutputStream(), true);
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out  = new ObjectOutputStream(socket.getOutputStream());
            
            
        } catch (IOException e) {
            throw new RuntimeException("ERROR: unable to instantiate reader and writer", e);
        }
    }

    @Override
    public void run() {
        String line = scanner.nextLine();
        while (line != null) {
            try {
                if (line.equals("fileName")) {
                    this.fileName = line;
                } else if (line.equals("requestApiKey")) {
                    out.writeObject(requestApiKey());
                    
                } else if (line.equals("newTournament")) {
                    
                    try {
                        String id = scanner.nextLine();
                        HashSet temp = (HashSet) in.readObject();
                        newTournament(id, temp);
                        out.writeObject(turnier.getMap());
                        out.flush();
                        //turnier.save();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MyRunable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        String s = scanner.nextLine();
                        HashSet temp = (HashSet) in.readObject();
                        newTournament(s, temp);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MyRunable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (line.equals("open")) {
                    String fileName = scanner.nextLine();
                    
                } else if (line.equals("removePlayer")) {
                    String s = scanner.nextLine();
                    removePlayer(s);
                    
                } else if (line.equals("closeServer")) {
                    try {
                        scanner.close();
                        pw.close();
                        out.close();
                        in.close();
                        socket.close();
                    } catch (IOException e) {
                        throw new RuntimeException("ERROR: unable to close reader, writer and socket", e);
                    }
                }

                System.out.println("done");
            } catch (NumberFormatException e) {
                throw new RuntimeException("ERROR: unable parse line " + line + " to int", e);
            } catch (IOException ex) {
                Logger.getLogger(MyRunable.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            line = scanner.nextLine();
        }

        System.out.println("client disconnected");

        try {
            scanner.close();
            pw.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("ERROR: unable to close reader, writer and socket", e);
        }
    }

    public String[] requestApiKey() {
        String[] array = null;
        try {
            Scanner sc = new Scanner(new File("Apikeys.txt"));
            String s = "";
            while (sc.hasNext()) {
                s = sc.nextLine();
                System.out.println(s);
            }

            array = s.split(";");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos2.lottotipps.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Torsten Welsch
 */
public class Worker implements Runnable {

    private Socket socket;
    private Scanner in;
    private PrintWriter out;
   
    private Map<String, String[]> hmap = new TreeMap<>();
    public Worker(Socket socket) {
        Map<String, String> time = generateApi();
        for (Map.Entry<String, String> entry : time.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + "; " + val + "\n");
        }
        try {
            this.socket = socket;

            this.in = new Scanner(socket.getInputStream());

        } catch (IOException e) {
            throw new RuntimeException("ERROR: unable to instantiate reader and writer", e);
        }
    }

    @Override
    public void run() {

        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {

            Map<String, String> time = generateApi();

            if (time == null) {
                System.out.println("Api == null");
            }
            try {
                out.writeObject(time);
                out.flush();
//                hmap = (Map<String, String[]>) in.readObject();
                
            } catch (IOException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            } 
            

            System.out.println("done");
        } catch (NumberFormatException e) {
            throw new RuntimeException("ERROR: unable parse line ");
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
       
        
    }
    
    static Map generateApi() {
        Map<String, String> Clubs = new TreeMap<>();
        Clubs.put("Wels", "wels0690");
        Clubs.put("Sonnberg", "sonnberg5060");
        Clubs.put("Linz Feldkirchen", "linz3080");
        Clubs.put("Golf Resort Kremstal", "kremstal7580");
        Clubs.put("Golpark Böhmerwald", "sonnberg5060");

        return Clubs;
    }

}

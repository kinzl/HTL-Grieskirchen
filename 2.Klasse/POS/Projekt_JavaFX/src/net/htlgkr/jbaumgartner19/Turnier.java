/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgkr.jbaumgartner19;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jbaumgartner19
 */
public class Turnier {
    //Im Key der Turnier - name und im Value der Spieler mit den erreichten Punkten
    private Map<String, HashSet<String>> map;
    private Spieler spieler;
    public Turnier() {
    }
    
    public void addPlayer(String id, HashSet hashSet, String fileName){
        map.put(id, hashSet);
        save(fileName);
    }
    
    public void removePlayer(String id){
        map.remove(id);
    }
    
    
    public void open(String filename){
        try {
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename+".xml")));
            
            map = (Map<String, HashSet<String>>) d.readObject();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Turnier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void save(String filename){
        try {
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename+".xml")));
            
            e.writeObject(map);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Turnier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

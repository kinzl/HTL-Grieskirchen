/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golfclubprojekt;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kinzl
 */
public class Turnier {
    private Map<String, HashSet<String>> map;
    public Turnier() {
    }
    
    public void addPlayer(String id, HashSet hashSet, String fileName){
        map.put(id, hashSet);
        save(fileName);
    }
    
    public void removePlayer(String id){
        map.remove(id);
    }
    
    
    public  Map<String, HashSet<String>> open(String filename){
        try {
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename+".xml")));
            
            map = (Map<String, HashSet<String>>) d.readObject();
            if(map != null){
                return map;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Turnier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private boolean save(String filename){
        try {
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename+".xml")));
            
            e.writeObject(map);
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Turnier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public Map<String, HashSet<String>> getMap() {
        return map;
    }
}

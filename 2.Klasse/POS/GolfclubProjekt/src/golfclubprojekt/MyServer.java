/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golfclubprojekt;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author kinzl
 */
public class MyServer {
    private static final int PORT = 4722;

    public void connect() {
        try (ServerSocket s = new ServerSocket(PORT)) {
            System.out.println("server started");
            while (true) {
                try {
                    new Thread(new MyRunable(s.accept())).start();
                    
                    
                    
                    
                } catch (IOException e) {
                    throw new RuntimeException("ERROR: unable to instantiate reader and writer", e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("ERROR: unable to instantiate the server socket", e);
        }
    }
}

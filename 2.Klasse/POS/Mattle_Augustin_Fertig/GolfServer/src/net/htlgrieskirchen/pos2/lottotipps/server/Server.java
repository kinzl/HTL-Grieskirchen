/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos2.lottotipps.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

/**
 *
 * @author bmattle19
 */
public class Server {
    private static final int PORT = 4711;

    public static void main(String[] args) {
        Map<String,String> time = Worker.generateApi();
            for (Map.Entry<String, String> entry : time.entrySet()) {
                Object key = entry.getKey();
                Object val = entry.getValue();
                System.out.println(key +"; "+val +"\n");
            }
        try (ServerSocket s = new ServerSocket(PORT)) {
            System.out.println("server started");
            while (true) {
                try {
                    new Thread(new Worker(s.accept())).start();
                    System.out.println("client connected");
                } catch (IOException e) {
                    throw new RuntimeException("ERROR: unable to instantiate reader and writer", e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("ERROR: unable to instantiate the server socket", e);
        }
    }

}

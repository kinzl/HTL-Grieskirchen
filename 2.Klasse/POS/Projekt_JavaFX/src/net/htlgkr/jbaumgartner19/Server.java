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
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;

/**
 * @author jbaumgartner19
 */
public class Server {

    public void connect() {
        try {
            ServerSocket serverSocket = new ServerSocket(4641);
            while(true){
                Socket clientSocket = serverSocket.accept();
                MyRunnable cr = new MyRunnable(clientSocket);
                Thread client = new Thread(cr);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("Server stopped");
    }

}

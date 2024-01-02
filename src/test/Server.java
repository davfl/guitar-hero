package test;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    public static void main(String[] args) {
        try {
            // Start the server
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started");

            // Wait for a client to connect
            Socket socket = serverSocket.accept();
            Socket socket2= serverSocket.accept();
            System.out.println("Client connected");
            
            new MultiClient(socket, socket2).start();
        
            serverSocket.close();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
  
    }
}
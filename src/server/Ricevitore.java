/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAVIDE
 */
public class Ricevitore extends Thread {
    private Socket socket;
    private int punteggio;
    
    public Ricevitore(Socket socket) {
        this.socket = socket;
    }
    
    public void run(){
        try {
            Scanner sc= new Scanner(socket.getInputStream());
            System.out.println("entro nel run");
            String msg=sc.nextLine();
            punteggio=Integer.parseInt(msg);
            System.out.println("entro nel run");
            System.out.println(punteggio);
               } catch (IOException ex) {
            Logger.getLogger(Ricevitore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public int getPunteggio() {
        return punteggio;
    }
    
}

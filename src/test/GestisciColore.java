package test;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author d.florini
 */
/*public class GestisciColore extends Thread{
    
    private JPanel quad;
    private Color color;
    
    public GestisciColore(JPanel quad,Color color) {
        this.quad = quad;
        this.color=color;
  
    }
    public void run(){
        Random r= new Random();
        while(true){   
            int numeroRandomizzato=1000*r.nextInt(5);

            try {
                Thread.sleep(2000);
                quad.setBackground(Color.white);
                Thread.sleep(numeroRandomizzato);
                quad.setBackground(color);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestisciColore.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
*/
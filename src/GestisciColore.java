
import java.awt.Color;
import java.awt.Label;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author d.florini
 */
public class GestisciColore extends Thread{
    
    private Label label;
    
    public GestisciColore(Label label){
        this.label=label;     
    }
 
    public void run(){
        Random r= new Random();
        while(true){   
            int numeroRandomizzato=1000*r.nextInt(5);

            try {
                Thread.sleep(2000);
                label.setForeground(Color.red);
                Thread.sleep(numeroRandomizzato);
                label.setForeground(Color.black);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestisciColore.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}

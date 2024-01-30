/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gioco;

import gioco.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author DAVIDE
 */
public class Tastiera implements KeyListener {

    private char  tastoPremuto;
    private JFrame frame;
    
    public Tastiera (JFrame frame){
        this.frame=frame;
        this.frame.addKeyListener((KeyListener) this);
     }
    
    
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("sto premendo");
        tastoPremuto = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        tastoPremuto='\0';
    }

    public char getTastoPremuto() {
        return tastoPremuto;
    }
    
}

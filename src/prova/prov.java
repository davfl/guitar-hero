/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prova;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author DAVIDE
 */
public class prov extends Thread implements KeyListener{
    private int nota;
    private int y;
    private int punteggio;
    private GridBagConstraints gbc;
    private JPanel panel;
    private ImageIcon imgPalla= new ImageIcon("src/palla/ball.png");
    private JLabel lblPalla= new JLabel(imgPalla);
    private  boolean isPremuto;
    private JFrame frame;
    private char [] comandi={'a','s','d','f','g'};
    private int punteggioThread=0;
    private ArrayList <Integer> punteggiThread;
    
    public prov(int nota, GridBagConstraints gbc, JPanel panel, JFrame frame, ArrayList<Integer> punteggio){
        this.nota=nota;
        this.gbc=gbc;
        this.panel=panel;
        this.frame=frame;
        frame.addKeyListener(this);
        isPremuto=false;
        punteggiThread=punteggio;
        //this.punteggio=punteggio;
    }
    public void run(){
        lblPalla.setPreferredSize(new Dimension(50, 50));
        //posizione
        this.gbc.gridx = nota-1;  
        this.gbc.anchor = GridBagConstraints.PAGE_START;
        this.gbc.insets = new Insets(0, 10, 0, 10);
        this.gbc.gridy=0;
        this.panel.add(lblPalla, gbc);
        SwingUtilities.updateComponentTreeUI(panel);
        for(y=0; y<500;y+=3){
            
            
            lblPalla.setLocation(lblPalla.getX(), y);
            try {
                sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(prov.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(isPremuto==true){
                break;
            }
        }
        panel.remove(lblPalla);
        SwingUtilities.updateComponentTreeUI(panel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if((y>=400 && y<=430)&& e.getKeyChar()==comandi[nota-1]){
            isPremuto=true;
            punteggiThread.add(1);
            //this.punteggioThread = this.punteggio + 1;
           // System.out.println("il punteggio del thread della nota "+this.nota +" e' " +this.punteggioThread);
          //  System.out.println(punteggio);
        
        }
        /*else
            punteggio--;*/
        //System.out.println(this.punteggioThread);
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getPunteggioThread() {
        return punteggioThread;
    }
    
    
    
}

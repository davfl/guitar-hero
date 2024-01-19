/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prova;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author DAVIDE
 */
/*public class prov extends Thread{
    private int nota;
        private GridBagConstraints gbc;
    private JPanel panel;
    
    public prov(int nota, GridBagConstraints gbc, JPanel panel){
        this.nota=nota;
        this.gbc=gbc;
        this.panel=panel;
    }
    public void run(){
        
    ImageIcon imgPalla= new ImageIcon("src/palla/ball.png");
    JLabel lblPalla= new JLabel(imgPalla);
            lblPalla.setPreferredSize(new Dimension(50, 50));
            //posizione
            this.gbc.gridx = nota-1;  
            this.gbc.anchor = GridBagConstraints.PAGE_START;
            this.gbc.insets = new Insets(0, 10, 0, 10);
            this.gbc.gridy=0;
            this.panel.add(lblPalla, gbc);
            SwingUtilities.updateComponentTreeUI(panel);
         for(int y=0; y<370;y+=5){
            y+=5;
            lblPalla.setLocation(lblPalla.getX(), y);
        try {
            sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(prov.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
         panel.remove(lblPalla);
         SwingUtilities.updateComponentTreeUI(panel);
    }
    
    
    
}*/

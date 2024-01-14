package prova;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author DAVIDE
 */
public class GestisciPallini extends Thread {
    private JPanel quad1;
    private JPanel quad2;
    private JPanel quad3;
    private JPanel quad4;
    private JPanel quad5;
    private ArrayList<JSONObject> oggetti;
    private GridBagConstraints gbc;
    private JPanel panel;

    public GestisciPallini(JPanel quad1, JPanel quad2, JPanel quad3, JPanel quad4, JPanel quad5,GridBagConstraints gbc, JPanel panel) throws LineUnavailableException {
        this.quad1 = quad1;
        this.quad2 = quad2;
        this.quad3 = quad3;
        this.quad4 = quad4;
        this.quad5 = quad5;
        this.oggetti= new ArrayList<>();
        this.gbc=gbc;
        this.panel=panel;
        
    }
    @Override
    public void run(){
        leggiJSON();
        ImageIcon imgPalla= new ImageIcon("src/palla/ball.png");
        
        
        for(int i=0; i<oggetti.size();i++){
            long sec= oggetti.get(i).getLong("tempo"); //il tempo in cui deve apparire la nota
            int nota=oggetti.get(i).getInt("posizione");   //prende la posizione, cioè dove deve apparire la nota              
            try {
                //if(timerTask.getCount()-2<=sec && timerTask.getCount()+2>=sec){
                sleep(sec);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                apparePallino(nota);
                /* try {
                sleep(1000);
                } catch (InterruptedException ex) {
                System.out.println("errore");
                } */
                // this.cambiaColore();
                //  }
            } catch (InterruptedException ex) {
                Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
            }

        }   
        //timer.cancel();
    }
   
    private void apparePallino(int nota) throws InterruptedException{
            ImageIcon imgPalla= new ImageIcon("src/palla/ball.png");
           // JLabel lblPalla = new JLabel(imgPalla);
            //lblPalla.setPreferredSize(new Dimension(50, 50));
            JLabel lblPalla= new JLabel(imgPalla);
            lblPalla.setPreferredSize(new Dimension(50, 50));
            //posizione
            this.gbc.gridx = nota-1;  
            this.gbc.anchor = GridBagConstraints.PAGE_START;
            this.gbc.insets = new Insets(0, 10, 0, 10);
            this.gbc.gridy=0;
            this.panel.add(lblPalla, gbc);
            SwingUtilities.updateComponentTreeUI(panel);
           /* for(int i=0; i<370;i++){
                y+=i;
               // System.out.println("entro nel ciclo");
                //lblPalla.setAlignmentY(i);
                //this.gbc.gridy += i;
                lblPalla.setBounds(0, y, 50, 50);
                sleep(50);
                //this.panel.add(lblPalla, gbc);
                SwingUtilities.updateComponentTreeUI(panel);
            /*}*/
           // System.out.println(lblPalla.getAlignmentX());

        Timer timer = new Timer(50, new ActionListener() {
            int y = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                y += 5; 
                lblPalla.setLocation(lblPalla.getX(),y);
                panel.repaint();
                //SwingUtilities.updateComponentTreeUI(panel);
                if (y >= 370) { 
                    ((Timer) e.getSource()).stop(); 
                }
            }
        });

        timer.start();
    


     /*   gbc.gridx = nota - 1;
        gbc.gridy = 0;

        panel.add(lblPalla, gbc);
        panel.revalidate();
        panel.repaint();

        int y = 0;
        Timer timer = new Timer(50, new ActionListener() {
            int y=0;
            @Override
            public void actionPerformed(ActionEvent e) {
                y += 5;
                gbc.gridy = y / 50; // Aggiorna la coordinata y basata sulla dimensione della JLabel
                panel.revalidate();
                panel.repaint();

                if (y >= panel.getHeight()) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.start();
    
    
    
    
    
    
    */
    
            //palle.add(lblPalla);
            
           // ImageIcon imgPalla= new ImageIcon("src/palla/ball.png");
        /*for (int i = 0; i < 5; i++) {
            
            JLabel lblPalla = new JLabel(imgPalla);
            lblPalla.setPreferredSize(new Dimension(50, 50));
            //lblPalla.setPreferredSize(new Dimension(50, 50));
            //posizione
            gbc.gridx = i;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(0, 10, 0, 10);

            panel.add(lblPalla, gbc);
           // panel.repaint();
            SwingUtilities.updateComponentTreeUI(panel);
           // palle.add(lblPalla);
        }
        */
        
        /*switch (nota){
            case 1:
                this.quad1.setBackground(Color.white);
                break;
            case 2:
                this.quad2.setBackground(Color.white);
                break;
            case 3:
                this.quad3.setBackground(Color.white);
                break;
            case 4:
                this.quad4.setBackground(Color.white);
                break;
            case 5:
                this.quad5.setBackground(Color.white);
                break;  
        }*/
    }
    
    /*private void cambiaColore(){
        this.quad1.setBackground(Color.red);
        this.quad2.setBackground(Color.yellow);
        this.quad3.setBackground(Color.green);
        this.quad4.setBackground(Color.pink);
        this.quad5.setBackground(Color.blue);
    }*/
    private void leggiJSON(){
        String text;
        try {
            text = new String(Files.readAllBytes(Paths.get("file_json/prova.json")), StandardCharsets.UTF_8); 
            JSONObject obj = new JSONObject(text);   
            JSONArray data= obj.getJSONArray("data");
            
            for(int i=0; i<data.length();i++){
                oggetti.add(data.getJSONObject(i));
            }
        } catch (IOException ex) {
            Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }    
}
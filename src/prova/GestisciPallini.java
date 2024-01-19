package prova;

import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class GestisciPallini extends Thread implements KeyListener{
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
        //ImageIcon imgPalla= new ImageIcon("src/palla/ball.png");
        
        
        for(int i=0; i<oggetti.size();i++){
            long sec= oggetti.get(i).getLong("tempo"); //il tempo in cui deve apparire la nota
            int nota=oggetti.get(i).getInt("posizione");   //prende la posizione, cioè dove deve apparire la nota              
            try {
                sleep(sec);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                apparePallino(nota);
                //new prov(nota, gbc, panel).start();
            } catch (InterruptedException ex) {
                Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
            }

        }   
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

        Timer timer = new Timer(20, new ActionListener() {
            int y = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                y += 5; 
                lblPalla.setLocation(lblPalla.getX(),y);
                //panel.repaint();
                if (y >= 500) { 
                    panel.remove(lblPalla);
                    ((Timer) e.getSource()).stop(); 
                }
            }
        });

        timer.start();

    }
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

    @Override
    public void keyTyped(KeyEvent e) {
        int punteggio=0;
        // Codice da eseguire quando viene digitato un tasto (ad esempio, caratteri)
        //score.setText("Score: "+punteggio);
        if(quad1.getBackground().equals(white) && e.getKeyChar()=='a'){
            punteggio++;
        }
        else if (quad2.getBackground().equals(white) && e.getKeyChar()=='s'){
            punteggio++; 
        }
        else if(quad3.getBackground().equals(white) && e.getKeyChar()=='d'){
            punteggio++; 
        }
        else if(quad4.getBackground().equals(white) && e.getKeyChar()=='f'){
            punteggio++; 
        }
        else if(quad5.getBackground().equals(white) && e.getKeyChar()=='g'){
            punteggio++; 
        }
        else{
            punteggio--; 
        }
        //score.setText("Score: "+punteggio);    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Tasto premuto: " + KeyEvent.getKeyText(keyCode));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
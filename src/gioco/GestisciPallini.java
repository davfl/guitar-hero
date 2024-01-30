package gioco;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author DAVIDE
 */
public class GestisciPallini extends Thread{

    private ArrayList<JSONObject> oggetti;
    private GridBagConstraints gbc;
    private JPanel panel;
    private JFrame frame;
    private int punteggio;
    private Tastiera tastiera;
    private char [] comandi={'a','s','d','f','g'};
    private JLabel score;
    private Connessione connessione;
    private String percorso;
    
    public GestisciPallini(GridBagConstraints gbc, JPanel panel, JFrame frame, JLabel score, Connessione connessione, String percorso) throws LineUnavailableException {
        this.oggetti= new ArrayList<>();
        this.gbc=gbc;
        this.panel=panel;
        this.frame=frame;
        this.punteggio=0;
        tastiera= new Tastiera(frame);
        this.score=score;
        this.connessione=connessione;
        this.percorso=percorso;
    }
    
    public void run(){
        leggiJSON();
        for(int i=0; i<oggetti.size();i++){
            long sec= oggetti.get(i).getLong("tempo"); //il tempo in cui deve apparire la nota
            int nota=oggetti.get(i).getInt("posizione");   //prende la posizione, cioè dove deve apparire la nota              
            try {
                Thread.sleep(sec);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                apparePallino(nota);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(connessione != null){
            connessione.inviaPunteggio(punteggio);
            try {
                connessione.riceviVincitore();
            } catch (IOException ex) {
                Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(panel, connessione.getMsg(), "VINCITORE", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void apparePallino(int nota) throws InterruptedException{
            ImageIcon imgPalla= new ImageIcon("src/palla/ball.png");
            JLabel lblPalla = new JLabel(imgPalla);
            lblPalla.setPreferredSize(new Dimension(50, 50));
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
                lblPalla.setLocation(lblPalla.getX(), y);
                panel.repaint();
                if ((y>=400 && y<=430) && tastiera.getTastoPremuto()==comandi[nota-1]) {
                  //  System.out.println("Punteggio aumentato!");
                    punteggio++;
                   // System.out.println(punteggio);
                    ((Timer) e.getSource()).stop();
                    panel.remove(lblPalla);
                }
                if (y >= 500) {
                    ((Timer) e.getSource()).stop();
                    panel.remove(lblPalla);
                    punteggio--;
                }
                score.setText("Score: "+punteggio);
            }
        });
        timer.start();
    }
    private void leggiJSON(){
        String text;
        try {
            text = new String(Files.readAllBytes(Paths.get(percorso)), StandardCharsets.UTF_8); 
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
package test;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JPanel;
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
    //private JSONArray oggetti;
    private ArrayList<JSONObject> oggetti;
    //private final String fileName ;
    //private final Clip clip;
    
    
    private long tempoTotale;

    public GestisciPallini(JPanel quad1, JPanel quad2, JPanel quad3, JPanel quad4, JPanel quad5) throws LineUnavailableException {
        this.quad1 = quad1;
        this.quad2 = quad2;
        this.quad3 = quad3;
        this.quad4 = quad4;
        this.quad5 = quad5;
        this.oggetti= new ArrayList<>();
       // fileName= "canzoni_ricevute/received_audio.wav";
       // clip= (Clip)AudioSystem.getLine(new Line.Info(Clip.class));  
    }
    
    @Override
    public void run(){
        leggiJSON();
        
        GestioneTimer timerTask= new GestioneTimer();
        Timer timer= new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0,1);
       // playSong();
        int i=0;
       
        System.out.println(tempoTotale);
        while(timerTask.getCount()<tempoTotale){

            if(i<oggetti.size()){
                long sec= oggetti.get(i).getLong("tempo");
                int nota=oggetti.get(i).getInt("posizione");                
                this.cambiaColore();
                if(timerTask.getCount()-2<=sec && timerTask.getCount()+2>=sec){
                    apparePallino(nota);
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("errore");
                    }
                    i++;
                    this.cambiaColore();
                }
            } 
        }   
        timer.cancel();
    }
    private void apparePallino(int nota){
        switch (nota){
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
        }
    }
   /* public void playSong()
    {
        try
        {
            clip.addLineListener(new LineListener()
            {
                @Override
                public void update(LineEvent event)
                {
                    if (event.getType() == LineEvent.Type.STOP)
                        clip.close();
                }
            });

            clip.open(AudioSystem.getAudioInputStream(new File(fileName)));
            clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }*/
    
    private void cambiaColore(){
        this.quad1.setBackground(Color.red);
        this.quad2.setBackground(Color.yellow);
        this.quad3.setBackground(Color.green);
        this.quad4.setBackground(Color.pink);
        this.quad5.setBackground(Color.blue);
    }
    private void leggiJSON(){
        String text;
        try {
            text = new String(Files.readAllBytes(Paths.get("file_json/prova.json")), StandardCharsets.UTF_8); 
            JSONObject obj = new JSONObject(text);   
            JSONArray data= obj.getJSONArray("data");
            this.tempoTotale= obj.getLong("tempoTotale");
            
            //oggetti=data
            
            for(int i=0; i<data.length();i++){
                oggetti.add(data.getJSONObject(i));
            }
        } catch (IOException ex) {
            Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }    
}
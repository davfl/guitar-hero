package test;

import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private long tempoTotale;

    public GestisciPallini(JPanel quad1, JPanel quad2, JPanel quad3, JPanel quad4, JPanel quad5) {
        this.quad1 = quad1;
        this.quad2 = quad2;
        this.quad3 = quad3;
        this.quad4 = quad4;
        this.quad5 = quad5;
        this.tempoTotale = 0;
        this.oggetti= new ArrayList<>();
    }
    
    @Override
    public void run(){
        leggiJSON();
       // TimerTask timerTask= new GestioneTimer();
        GestioneTimer timerTask= new GestioneTimer();
        Timer timer= new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0,1);
        int i=0;
        while(timerTask.getCount() < tempoTotale){
           // System.out.println("timer " +timerTask.getCount());
           // for (int i=0; i<oggetti.size(); i++){
            if(i<oggetti.size()){
                long sec= oggetti.get(i).getLong("tempo");
                int nota=oggetti.get(i).getInt("posizione");
               // System.out.println("secondi "+ sec);
                //System.out.println("sec-10:" +sec-10);
                //this.cambiaColore();
                if(timerTask.getCount()-2<=sec && timerTask.getCount()+2>=sec){
                    
                    System.out.println("entro in if");
                   /* try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    
                   /* switch (nota){
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
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                    i++;
                    System.out.println("esco dall'if");
                }
                
            }
            
        }   
        //this.cambiaColore();
        timer.cancel();
    }
    
   /* private void cambiaColore(){
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
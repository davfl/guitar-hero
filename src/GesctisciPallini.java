
import java.awt.Label;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DAVIDE
 */
public class GesctisciPallini extends Thread {
    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;
    private long count;

    public GesctisciPallini(Label label1, Label label2, Label label3, Label label4, Label label5) {
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.label5 = label5;
        count=0;
    }
  
    @Override
    public void run(){
        ArrayList<JSONObject> oggetti= new ArrayList<>();
        String text;
        //JSONObject obj; 
        try {
            text = new String(Files.readAllBytes(Paths.get("file_json/prova.json")), StandardCharsets.UTF_8); 
            JSONObject obj = new JSONObject(text);   
            JSONArray data= obj.getJSONArray("data");
            for(int i=0; i<data.length();i++){
                //JSONObject dato1= data.getJSONObject(i);
                oggetti.add(data.getJSONObject(i));
               // System.out.println(dato1);
            }
            

        } catch (IOException ex) {
            Logger.getLogger(GesctisciPallini.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        

        long tempoInizio = System.currentTimeMillis();

        // Inizia il contatore
       

        // Esegui un loop per contare
        //while (true) {
        /*for (int i=0; i<oggetti.size(); i++){
            // Ottieni il tempo corrente in millisecondi
            long tempoCorrente = System.currentTimeMillis(); 
            // Calcola la differenza di tempo
            long differenzaTempo = tempoCorrente - tempoInizio; 

            // Incrementa il contatore ogni 100 millisecondi (decimillesimi di secondo)
            if (differenzaTempo >= 1) {
                count++;
                tempoInizio = tempoCorrente; // Aggiorna il tempo di inizio
                System.out.println("Contatore: " + count);
            }
            
            if(oggetti.get(i).)
        }*/
    }
}

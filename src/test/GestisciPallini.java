package test;


import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
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
public class GestisciPallini extends Thread {
    private JPanel quad1;
    private JPanel quad2;
    private JPanel quad3;
    private JPanel quad4;
    private JPanel quad5;
    
    
    private long count;

    public GestisciPallini(JPanel quad1, JPanel quad2, JPanel quad3, JPanel quad4, JPanel quad5) {
        this.quad1 = quad1;
        this.quad2 = quad2;
        this.quad3 = quad3;
        this.quad4 = quad4;
        this.quad5 = quad5;
        this.count = 0;
    }
    
    @Override
    public void run(){
        ArrayList<JSONObject> oggetti= new ArrayList<>();
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
        // Esegui un loop per contare
        for (int i=0; i<oggetti.size(); i++){
            int nota=oggetti.get(i).getInt("posizione");
                    this.quad1.setBackground(Color.red);
                    this.quad2.setBackground(Color.yellow);
                    this.quad3.setBackground(Color.green);
                    this.quad4.setBackground(Color.pink);
                    this.quad5.setBackground(Color.blue);
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
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestisciPallini.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
}

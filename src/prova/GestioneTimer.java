/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prova;

import static java.lang.Thread.sleep;
import java.util.TimerTask;
import java.util.logging.Level;


/**
 *
 * @author DAVIDE
 */
public class GestioneTimer extends TimerTask{

    private long count;
    
    public GestioneTimer(){
        this.count=0;
    }
    
    @Override
    public void run() {
        this.count++;
    }

    public long getCount() {
        return this.count;
    }
    
}

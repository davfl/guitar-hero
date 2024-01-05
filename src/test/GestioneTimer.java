/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.util.TimerTask;

/**
 *
 * @author DAVIDE
 */
public class GestioneTimer extends TimerTask{

    private long count;
    
    public GestioneTimer(){
        count=0;
    }
    
    @Override
    public void run() {
        count++;
        //System.out.println(count);
    }

    public long getCount() {
        return count;
    }
    
}

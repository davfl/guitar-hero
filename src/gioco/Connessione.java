/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gioco;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author DAVIDE
 */
public class Connessione {
    private Socket socket;
    private PrintWriter out;
    private Scanner sc;
    private String msg;
    public Connessione() throws IOException{
        this.socket=new Socket("localhost", 1234);
        out= new PrintWriter(socket.getOutputStream(), true);
        //sc= new Scanner(socket.getInputStream());
        msg=null;
    }

    public void connessione() throws IOException{
        InputStream inputStream = socket.getInputStream();

        FileOutputStream fileOutputStream = new FileOutputStream("canzoni_ricevute/received_audio.wav");
        FileOutputStream fileOutputStream2 = new FileOutputStream("file_json/prova.json");

        byte [] lunghezza=new byte[8];
        byte [] lunghezza2= new byte[8];
 
        int lunghezzaFile1 = inputStream.read(lunghezza);
        int lunghezzaFile2= inputStream.read(lunghezza2);
        
        int myInt = Integer.parseInt(new String(lunghezza, 0, lunghezzaFile1)); 
        int myInt2= Integer.parseInt(new String(lunghezza2, 0, lunghezzaFile2));
        
        //System.out.println(myInt);
        byte[] buffer = new byte[1024];
        byte[] buffer2= new byte[1024];
        int bytesRead1;
        int bytesRead2;
        long cont=0;
        long cont2=0;
        
        while (cont<myInt &&(bytesRead1 = inputStream.read(buffer))!=-1) {
            fileOutputStream.write(buffer, 0, bytesRead1);
            cont+=bytesRead1;
        }
        fileOutputStream.close();
        while (cont2<myInt2 && (bytesRead2 = inputStream.read(buffer2)) != -1) {
            fileOutputStream2.write(buffer2, 0, bytesRead2);
            cont2+=bytesRead2;
        }
        fileOutputStream2.close();
        
        //inputStream.close();
       // socket.close();

       System.out.println("File received successfully");
    }
    public void inviaPunteggio(int punteggio){
        out.println(punteggio);
        System.out.println("sto inviando il punteggio");
    }
    public void riceviVincitore() throws IOException{
        sc=new Scanner(socket.getInputStream());
        msg=sc.nextLine();
        System.out.println(msg);
    }
}
        

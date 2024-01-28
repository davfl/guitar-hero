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

/**
 *
 * @author DAVIDE
 */
public class Connessione {
    private Socket socket;
    private PrintWriter out;
    public Connessione() throws IOException{
        this.socket=new Socket("localhost", 1234);;
        out= new PrintWriter(socket.getOutputStream(), true);
    }

    public void connessione() throws IOException{
        InputStream inputStream = socket.getInputStream();

        FileOutputStream fileOutputStream = new FileOutputStream("canzoni_ricevute/received_audio.wav");
        FileOutputStream fileOutputStream2 = new FileOutputStream("file_json/prova.json");

        byte [] lunghezza=new byte[8];
        int bytesRead = inputStream.read(lunghezza);
        int myInt = Integer.parseInt(new String(lunghezza, 0, bytesRead));  
        System.out.println(myInt);
        byte[] buffer = new byte[1024];
        byte[] buffer2= new byte[1024];
        int bytesRead1;
        int bytesRead2;
        long cont=0;
        while (cont<myInt &&(bytesRead1 = inputStream.read(buffer))!=-1) {
            fileOutputStream.write(buffer, 0, bytesRead1);
            cont+=1024;
        }
        while ((bytesRead2 = inputStream.read(buffer2)) != -1) {
            fileOutputStream2.write(buffer2, 0, bytesRead2);
        }

        fileOutputStream.close();
        inputStream.close();
       // socket.close();

       System.out.println("File received successfully");
    }
    public void inviaPunteggio(int punteggio){
        out.print(punteggio);
    }
}
        

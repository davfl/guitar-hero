package gioco;


import gioco.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author d.florini
 */
public class GUI {

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) throws InterruptedException, IOException {
        new MainScreen();
            // Connect to the server
            String fileName = "canzoni_ricevute/received_audio.wav";
           // connectToserver(fileName);
            /* run song */
            //playSong(fileName);
            
            
    }
   /* public static void connectToserver(String fileName) throws IOException, InterruptedException{
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server");
            
            
            InputStream inputStream = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
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
                System.out.println(bytesRead1);
                cont+=bytesRead1;
                System.out.println(cont);
            }
            while ((bytesRead2 = inputStream.read(buffer2)) != -1) {
                fileOutputStream2.write(buffer2, 0, bytesRead2);
            }
            
            fileOutputStream.close();
            inputStream.close();
           // socket.close();
            System.out.println("File received successfully");
    }*/
    

}

package test;


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
            connectToserver(fileName);
            /* run song */
            //playSong(fileName);
            
            
    }
     public static void connectToserver(String fileName) throws IOException{
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server");
            
            // Receive the audio file from the server
            InputStream inputStream = socket.getInputStream();
            
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            fileOutputStream.close();
            inputStream.close();
            socket.close();
            System.out.println("File received successfully");
    }
    

}

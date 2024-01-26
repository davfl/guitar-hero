package gioco;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DAVIDE
 */
public class MultiClient extends Thread {
    private Socket socket1;
    private Socket socket2; 

    public MultiClient(Socket socket1, Socket socket2) {
        this.socket1 = socket1;
        this.socket2 = socket2;
    }
    
    public void run(){
        try{
            
            ArrayList<File>listsongs=new ArrayList<>();
            listsongs.add(new File("src/canzoni/file1.wav"));
            listsongs.add(new File("src/canzoni/file2.wav"));
            listsongs.add(new File("src/canzoni/file3.wav"));
            Random r= new Random();
            int numeroRandomizzato= r.nextInt(3);
            File file=listsongs.get(numeroRandomizzato);
            OutputStream outputStream = socket1.getOutputStream();
            OutputStream outputStream2= socket2.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                outputStream2.write(buffer, 0, bytesRead);
            }
            fileInputStream.close();
            outputStream.close();
            outputStream2.close();
            System.out.println("File"+file+" sent successfully");
            } catch (IOException e) {
            // Log the error
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    
    
}

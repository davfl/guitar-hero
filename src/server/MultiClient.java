package server;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author DAVIDE
 */
public class MultiClient extends Thread {
    private Socket socket1;
    private Socket socket2; 
    private ArrayList<File>listsongs;
    private ArrayList <File> fileJson;  
    public MultiClient(Socket socket1, Socket socket2) {
        this.socket1 = socket1;
        this.socket2 = socket2;
        listsongs=new ArrayList<>();
        fileJson= new ArrayList<>();
    }
    
    public void run(){
        try{
            
            listsongs.add(new File("src/canzoni/file1.wav"));
            listsongs.add(new File("src/canzoni/file2.wav"));
            listsongs.add(new File("src/canzoni/file3.wav"));
            fileJson.add(new File("src/fileJson/prova.json"));
         //   fileJson.add(new File("src/fileJson/prova2.json"));
          //  fileJson.add(new File("src/fileJson/prova3.json"));
            
            Random r= new Random();
            int numeroRandomizzato= r.nextInt(3);
            File file=listsongs.get(numeroRandomizzato);
            File file2= fileJson.get(0);

            OutputStream outputStream = socket1.getOutputStream();
            OutputStream outputStream2= socket2.getOutputStream();

            FileInputStream fileInputStream = new FileInputStream(file);
            FileInputStream fileInputStream2 =  new FileInputStream(file2);

            byte[] buffer = new byte[1024];
            byte[] buffer2 = new byte[1024];
            int bytesRead;
            int bytesRead2;
            
            byte[] intBytes = Integer.toString((int)file.length()).getBytes();
            byte [] intBytes2= Integer.toString((int)file2.length()).getBytes();
            //byte[] lunghezza(file.length());
            //System.out.println((int)file.length());
            outputStream.write(intBytes);
            outputStream2.write(intBytes);
            
            
            outputStream.write(intBytes2);
            outputStream2.write(intBytes2);
            
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {//finchè il 
                outputStream.write(buffer, 0, bytesRead);
                outputStream2.write(buffer, 0, bytesRead);
            }
            while ((bytesRead2 = fileInputStream2.read(buffer2)) != -1) {//finchè il 
                //System.out.println("entro nel ciclo");
               // System.out.println(bytesRead2);
                outputStream.write(buffer2, 0, bytesRead2);
                outputStream2.write(buffer2, 0, bytesRead2);
            }
            
            Ricevitore r1=  new Ricevitore(socket1);
            Ricevitore r2=  new Ricevitore(socket2);
            
            r1.start();
            r2.start();
           
            r1.join();
            r2.join();
           
            if(r1.getPunteggio()>r2.getPunteggio())
                System.out.println("vince r1");
            else if(r1.getPunteggio()<r2.getPunteggio())
                System.out.println("vince r2");
            else
                System.out.println("parita");
            
            
            fileInputStream.close();
            fileInputStream2.close();
            outputStream.close();
            outputStream2.close();
            System.out.println("File"+file+" sent successfully");
            } catch (IOException e) {
            // Log the error
            System.out.println("Error: " + e.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}

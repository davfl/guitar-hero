package server;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
            fileJson.add(new File("src/fileJson/prova2.json"));
            fileJson.add(new File("src/fileJson/prova3.json"));
            
            Random r= new Random();
            int numeroRandomizzato= r.nextInt(3);
            File file=listsongs.get(numeroRandomizzato);
            File file2= fileJson.get(0);

            DataOutputStream outputStream = new DataOutputStream(socket1.getOutputStream());
            DataOutputStream outputStream2= new DataOutputStream(socket2.getOutputStream());

            DataInputStream inputStream = new DataInputStream(socket1.getInputStream());
            DataInputStream inputStream2= new DataInputStream(socket2.getInputStream());
            
            String user1=inputStream.readUTF();
            String user2=inputStream2.readUTF();
            
            FileInputStream fileInputStream = new FileInputStream(file);
            FileInputStream fileInputStream2 =  new FileInputStream(file2);

            byte[] buffer = new byte[1024];
            byte[] buffer2 = new byte[1024];
            int bytesRead;
            int bytesRead2;
           
            outputStream.writeLong(file.length());
            outputStream2.writeLong(file.length());
            
            outputStream.writeLong(file2.length());
            outputStream2.writeLong(file2.length());
                        
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {//finchè il 
                outputStream.write(buffer, 0, bytesRead);
                outputStream2.write(buffer, 0, bytesRead);
            }
            //bytesRead=0;
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
           
            PrintWriter out1= new PrintWriter(socket1.getOutputStream(), true);
            PrintWriter out2= new PrintWriter(socket2.getOutputStream(), true);
            
            String msg=null;
            
            if(r1.getPunteggio()>r2.getPunteggio())
                msg=user1;
            else if(r1.getPunteggio()<r2.getPunteggio())
                msg=user2;
            else
                msg="parita";
            
            out1.println(msg);
            out2.println(msg);
            
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

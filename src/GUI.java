
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
        // TODO code application logic here
        new MyFrame();
       //new NewFrame();
        //try {
            // Connect to the server
            String fileName = "canzoni_ricevute/received_audio.wav";
            connectToserver(fileName);
            
            leggiJSON();
            /* run song */
            //playSong(fileName);
            
            

            
      /*  } catch (IOException e) {
            // Log the error
            System.out.println("Error: " + e.getMessage());
        }*/
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
    
    public static void playSong(String filename)
    {
        try
        {
            final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));

            clip.addLineListener(new LineListener()
            {
                @Override
                public void update(LineEvent event)
                {
                    if (event.getType() == LineEvent.Type.STOP)
                        clip.close();
                }
            });

            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }
    public static void leggiJSON() throws IOException{
        String text = new String(Files.readAllBytes(Paths.get("file_json/prova.json")), StandardCharsets.UTF_8);
        JSONObject obj = new JSONObject(text);
        JSONArray data= obj.getJSONArray("data");
        
        for(int i=0; i<data.length();i++){
            JSONObject dato1= data.getJSONObject(i);
            System.out.println(dato1);
        }

        
    }
}

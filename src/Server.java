import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

public class Server {
    public static void main(String[] args) {
        try {
            // Start the server
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started");

            // Wait for a client to connect
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Send the audio file to the client
            ArrayList<File>listsongs=new ArrayList<>();
            listsongs.add(new File("src/canzoni/file1.wav"));
            //listsongs.add(new File("src/canzoni/file2.wav"));
            //listsongs.add(new File("src/canzoni/file3.wav"));
            Random r= new Random();
            //int numeroRandoizzato= r.nextInt(3);
            File file=listsongs.get(0);
            OutputStream outputStream = socket.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            fileInputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
            System.out.println("File"+file+" sent successfully");
            
        } catch (IOException e) {
            // Log the error
            System.out.println("Error: " + e.getMessage());
        }
    }
}
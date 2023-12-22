
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;



public class MyFrame extends JFrame implements KeyListener{
    
    private Label lblProva;
    private int punteggio=0;
    
    public MyFrame() throws InterruptedException{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setFocusable(true);        
        this.addKeyListener(this);
        
        
        lblProva= new Label("prova");
        lblProva.setVisible(true);
        this.add(lblProva);
        cambiaColore();
        
    }
   
    public void cambiaColore() throws InterruptedException{
        new GestisciColore (lblProva).start();
    }
    
    
    public void paint(Graphics g){
        Graphics2D g2D= (Graphics2D) g;  
    }
    
    // Metodi dell'interfaccia KeyListener
    public void keyPressed(KeyEvent e) {
        // Codice da eseguire quando viene premuto un tasto
        int keyCode = e.getKeyCode();
        System.out.println("Tasto premuto: " + KeyEvent.getKeyText(keyCode));
        
        
    }

    public void keyReleased(KeyEvent e) {
        // Codice da eseguire quando viene rilasciato un tasto
    }

    public void keyTyped(KeyEvent e) {
        // Codice da eseguire quando viene digitato un tasto (ad esempio, caratteri)
        if(lblProva.getForeground().equals(Color.red)){
            punteggio++;
        }else{
            punteggio--;
        }
        System.out.println(punteggio);
    }
}

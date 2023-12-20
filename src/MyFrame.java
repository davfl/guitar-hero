
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class MyFrame extends JFrame implements KeyListener{
    
    public MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setFocusable(true);
        
        this.addKeyListener(this);
        
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
        
    }
    
    
    
}


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;




public class MyFrame extends JFrame implements KeyListener{
    
    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;
   // private JLabel lblImmagine;
    private int punteggio=0;
    
    public MyFrame() throws InterruptedException{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setFocusable(true);        
        this.addKeyListener(this);
        
        //lblImmagine=new JLabel(new ImageIcon("immagini/ball.jpg"));
        
        
        label1= new Label("label1");
        label2= new Label("label2");
        label3= new Label("label3");
        label4= new Label("label4");
        label5= new Label("label5");
        
        label1.setBounds(50,60,10,10);
        label2.setBounds(70,60,10,10);
        label3.setBounds(120,60,10,10);
        label4.setBounds(150,60,10,10);
        label5.setBounds(170,60,10,10);
        
        //label1.setVisible(true);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
      //  this.add(lblImmagine);
   
        cambiaColore();
        
    }
   
    public void cambiaColore() throws InterruptedException{
        new GestisciColore (label1).start();
        new GestisciColore (label2).start();
        new GestisciColore (label3).start();
        new GestisciColore (label4).start();
        new GestisciColore (label5).start();
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
        if(label1.getForeground().equals(Color.red) && e.getKeyChar()=='a')
            punteggio++;
        else if (label2.getForeground().equals(Color.red) && e.getKeyChar()=='s')
            punteggio++;
        else if(label3.getForeground().equals(Color.red) && e.getKeyChar()=='d')
            punteggio++;
        else if(label4.getForeground().equals(Color.red) && e.getKeyChar()=='f')
            punteggio++;
        else if(label5.getForeground().equals(Color.red) && e.getKeyChar()=='g')
            punteggio++;
        else{
            punteggio--;
        }
        System.out.println(punteggio);
    }
}

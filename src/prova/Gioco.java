/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prova;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author DAVIDE
 */
public class Gioco {
    private GridBagConstraints gbc;
    private GestioneMusica musica;
    private JPanel newPage;
    private JFrame frame;
    private JPanel mainPanel;
    private int punteggio=0;
    private JPanel quad1;
    private JPanel quad2;
    private JPanel quad3;
    private JPanel quad4;
    private JPanel quad5;
    private JLabel score;
    private ArrayList<JLabel> palle;
    private ImageIcon imgPalla;
    
    public Gioco(JFrame frame, JPanel mainPanel) throws LineUnavailableException {
        quad1 = new JPanel();
        quad2 = new JPanel();
        quad3 = new JPanel();
        quad4 = new JPanel();
        quad5 = new JPanel();
        this.frame=frame;
        this.mainPanel=mainPanel;
        gbc= new GridBagConstraints(); 
        newPage=new JPanel(new GridBagLayout());
        musica= new GestioneMusica("canzoni_ricevute/received_audio.wav");
        palle= new ArrayList<>();
        imgPalla = new ImageIcon("src/palla/ball.png"); 
    }
    public void openNewPage() throws IOException, LineUnavailableException {
        frame.getContentPane().removeAll();
        frame.repaint();
        //frame.addKeyListener((KeyListener) );

        //JPanel newPage = new JPanel(new GridBagLayout());
       // GridBagConstraints gbc = new GridBagConstraints();
        newPage.setBackground(Color.BLACK);
        
       /* score = new JLabel("Score");
        score.setFont(new Font("Arial", Font.BOLD, 35));
        score.setForeground(Color.WHITE);
        gbc.gridx = 6;
        gbc.gridy = 1; // o qualsiasi altra posizione desiderata
        gbc.insets = new Insets(10, 50, 0, 0);
        newPage.add(score, gbc);
        /*/
        // Linee bianche
        creaRighe();
       
        // Palla sopra ogni linea

         
        // Aggiungi l'immagine (palla) sopra ogni linea con il timer
      /*  for (int i = 0; i < 5; i++) {
            JLabel lblPalla = new JLabel(imgPalla);
            lblPalla.setPreferredSize(new Dimension(50, 50));
            //posizione
            gbc.gridx = i;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(0, 10, 0, 10);

            newPage.add(lblPalla, gbc);
            
            palle.add(lblPalla);
            
          
        }
        */
        // Quadratini
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        creaQuadrati();
       /*  // Reset vertical weight     
            //set dei colori
           /* quad1.setBackground(Color.BLACK);
            quad2.setBackground(Color.BLACK);
            quad3.setBackground(Color.BLACK);
            quad4.setBackground(Color.BLACK);
            quad5.setBackground(Color.BLACK);*/
            
          /*  quad1.add(new JLabel(new ImageIcon("src/image/img/verde.png")));
            quad2.add(new JLabel(new ImageIcon("src/image/img/rosso.png")));
            quad3.add(new JLabel(new ImageIcon("src/image/img/giallo.png")));
            quad4.add(new JLabel(new ImageIcon("src/image/img/blu.png")));
            quad5.add(new JLabel(new ImageIcon("src/image/img/arancio.png")));
*/
            new GestisciPallini(gbc, newPage, frame).start();
     
        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.setContentPane(new JLabel(new ImageIcon("src/image/red.jpg")));
                frame.setLayout(new BorderLayout());
                frame.add(mainPanel, BorderLayout.CENTER);//disegna sopra il main panel, ovvero quello di partenza
                frame.revalidate();//ricarica la pagina di base
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(55, 0, 0, 0); // Move the button down
        newPage.add(backButton, gbc);
        
        frame.add(newPage);
        frame.revalidate();
    }
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return button;
    }
    private void creaRighe(){
        // Linee bianche
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0; // Vertical weight
        gbc.insets = new Insets(0, 10, 0, 10);

        for (int i = 0; i < 5; i++) {
            JPanel line = new JPanel();
            line.setBackground(Color.WHITE);
            line.setPreferredSize(new Dimension(2, 370));
            newPage.add(line, gbc);
            gbc.gridx++;
        }
    }
    private void creaQuadrati(){
        quad1.setBackground(Color.red);
        quad2.setBackground(Color.yellow);
        quad3.setBackground(Color.green);
        quad4.setBackground(Color.pink);
        quad5.setBackground(Color.blue);

        quad1.setPreferredSize(new Dimension(50,50));
        quad2.setPreferredSize(new Dimension(50, 50));
        quad3.setPreferredSize(new Dimension(50, 50));
        quad4.setPreferredSize(new Dimension(50, 50));
        quad5.setPreferredSize(new Dimension(50, 50));

        newPage.add(quad1, gbc);gbc.gridx++;
        newPage.add(quad2, gbc);gbc.gridx++;
        newPage.add(quad3, gbc);gbc.gridx++;
        newPage.add(quad4, gbc);gbc.gridx++;
        newPage.add(quad5, gbc);gbc.gridx++;
    }
   /* public void keyTyped(KeyEvent e) {
        // Codice da eseguire quando viene digitato un tasto (ad esempio, caratteri)
        //score.setText("Score: "+punteggio);
        if(quad1.getBackground().equals(white) && e.getKeyChar()=='a'){
            punteggio++;
        }
        else if (quad2.getBackground().equals(white) && e.getKeyChar()=='s'){
            punteggio++; 
        }
        else if(quad3.getBackground().equals(white) && e.getKeyChar()=='d'){
            punteggio++; 
        }
        else if(quad4.getBackground().equals(white) && e.getKeyChar()=='f'){
            punteggio++; 
        }
        else if(quad5.getBackground().equals(white) && e.getKeyChar()=='g'){
            punteggio++; 
        }
        else{
            punteggio--; 
        }
        score.setText("Score: "+punteggio);
        //System.out.println(punteggio);
    }

    
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Tasto premuto: " + KeyEvent.getKeyText(keyCode));
    }

    
    public void keyReleased(KeyEvent e) {
        // Codice da eseguire quando viene rilasciato un tasto
    }*/
    
}

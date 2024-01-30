/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gioco;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DAVIDE
 */
public abstract class Grafica {
    
    protected GridBagConstraints gbc;
    protected JPanel panelGioco;
    protected JFrame frame;
    protected JPanel mainPanel;
    private int punteggio=0;
    private JPanel quad1;
    private JPanel quad2;
    private JPanel quad3;
    private JPanel quad4;
    private JPanel quad5;
    protected JLabel score;
    private ImageIcon imgPalla;
    protected JButton backButton;
    
    public Grafica (JFrame frame, JPanel mainPanel) {
        quad1 = new JPanel();
        quad2 = new JPanel();
        quad3 = new JPanel();
        quad4 = new JPanel();
        quad5 = new JPanel();
        this.frame=frame;
        this.mainPanel=mainPanel;
        gbc= new GridBagConstraints(); 
        panelGioco=new JPanel(new GridBagLayout());
        imgPalla = new ImageIcon("src/palla/ball.png");
        backButton = createStyledButton("Back");
    }
    public void window() throws IOException, LineUnavailableException, InterruptedException {

        panelGioco.removeAll();
        frame.getContentPane().removeAll();
        frame.repaint();
        panelGioco.setBackground(Color.BLACK);
        
        score = new JLabel("Score");
        score.setFont(new Font("Arial", Font.BOLD, 35));
        score.setForeground(Color.WHITE);
        gbc.gridx = 6;
        gbc.gridy = 1; // o qualsiasi altra posizione desiderata
        gbc.insets = new Insets(10, 50, 0, 0);
        panelGioco.add(score, gbc);
        
        // Linee bianche
        creaRighe();
        // Quadratini
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        creaQuadrati();

       /*     GestisciPallini pallini = new GestisciPallini(gbc, panelGioco, frame, score, connessione);
            pallini.start();
     */
       
        
        /*backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.setContentPane(new JLabel(new ImageIcon("src/image/red.jpg")));
                frame.setLayout(new BorderLayout());
                frame.add(mainPanel, BorderLayout.CENTER);//disegna sopra il main panel, ovvero quello di partenza
                frame.revalidate();//ricarica la pagina di base
            }
        });*/

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(55, 0, 0, 0); // Move the button down
        panelGioco.add(backButton, gbc);
       
       
       
       
       
        frame.add(panelGioco);
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
            panelGioco.add(line, gbc);
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

        JLabel label1 = new JLabel("A");
        JLabel label2 = new JLabel("S");
        JLabel label3 = new JLabel("D");
        JLabel label4 = new JLabel("F");
        JLabel label5 = new JLabel("G");
        
        label1.setFont(new Font("Calibri", Font.BOLD, 30));
        label2.setFont(new Font("Calibri", Font.BOLD, 30));
        label3.setFont(new Font("Calibri", Font.BOLD, 30));
        label4.setFont(new Font("Calibri", Font.BOLD, 30));
        label5.setFont(new Font("Calibri", Font.BOLD,30));
        
        
        label1.setForeground(Color.white);
        label2.setForeground(Color.white);
        label3.setForeground(Color.white);
        label4.setForeground(Color.white);
        label5.setForeground(Color.white);
        
        quad1.add(label1);
        quad2.add(label2);
        quad3.add(label3);
        quad4.add(label4);
        quad5.add(label5);
        
        
        panelGioco.add(quad1, gbc);gbc.gridx++;
        panelGioco.add(quad2, gbc);gbc.gridx++;
        panelGioco.add(quad3, gbc);gbc.gridx++;
        panelGioco.add(quad4, gbc);gbc.gridx++;
        panelGioco.add(quad5, gbc);gbc.gridx++;
    }

}

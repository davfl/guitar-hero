/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class SinglePlayer extends Grafica{    
    
    private GestioneMusica musica;
    private String livello;
    
    public SinglePlayer(JFrame frame, JPanel mainPanel, String livello) throws LineUnavailableException {
        super(frame, mainPanel);
        this.livello=livello;
        musica= new GestioneMusica("risorseOffline/"+livello+".mp3");
   
    }
    public void openNewPage() throws IOException, LineUnavailableException, InterruptedException {
        super.window();
        musica.playSong();
        GestisciPallini pallini = new GestisciPallini(gbc, panelGioco, frame, score, null,"risorseOffline/"+livello+".json" );
        pallini.start();
        super.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.setContentPane(new JLabel(new ImageIcon("src/image/red.jpg")));
                frame.setLayout(new BorderLayout());
                frame.add(mainPanel, BorderLayout.CENTER);//disegna sopra il main panel, ovvero quello di partenza
                frame.revalidate();//ricarica la pagina di base
                musica.fermaMusica();
            }
        });
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
}

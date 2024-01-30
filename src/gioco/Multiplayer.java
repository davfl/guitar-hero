/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gioco;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author DAVIDE
 */
public class Multiplayer extends Grafica {
    private GestioneMusica musica;
    private Connessione connessione;
    private String username;

    public Multiplayer(JFrame frame, JPanel mainPanel, String username) throws IOException, LineUnavailableException {
        super(frame, mainPanel);
        musica= new GestioneMusica("canzoni_ricevute/received_audio.wav");
        this.connessione = new Connessione();
        this.username = username;
    }
    public void openNewPage() throws IOException, LineUnavailableException, InterruptedException {
        super.window();
        connessione.connessione(username);
        musica.playSong();
        GestisciPallini pallini = new GestisciPallini(gbc, panelGioco, frame, score, connessione,"file_json/prova.json");
        pallini.start();

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

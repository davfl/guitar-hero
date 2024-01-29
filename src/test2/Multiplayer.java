/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test2;

import gioco.*;
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
        GestisciPallini pallini = new GestisciPallini(gbc, panelGioco, frame, score, connessione);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SceltaLivelli {
 
    private JFrame frame;
    private JPanel mainPanel;
    private GridBagConstraints gbc;
    
    public SceltaLivelli(JFrame frame) {
        this.frame=frame;
        gbc= new GridBagConstraints();
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);

        this.frame.getContentPane().removeAll();
        this.frame.repaint();
        ImageIcon backgroundImage = new ImageIcon("src/image/red.jpg");
    
        // labels
        JLabel titleLabel = new JLabel("Guitar Hero");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);

        // buttons
        JButton btnLivello1 = createStyledButton("livello1");
        JButton btnLivello2 = createStyledButton("livello2");

        btnLivello1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    SinglePlayer s;
                try {
                    s = new SinglePlayer(frame, mainPanel, "file1");
                    s.openNewPage();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(SceltaLivelli.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SceltaLivelli.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SceltaLivelli.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
        });

        btnLivello2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SinglePlayer s= new SinglePlayer(frame, mainPanel, "file2");
                    s.openNewPage();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(SceltaLivelli.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SceltaLivelli.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SceltaLivelli.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Padding
        mainPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(btnLivello1, gbc);

        gbc.gridy = 2;
        mainPanel.add(btnLivello2, gbc);

        // Set background image directly on the content pane
        this.frame.setContentPane(new JLabel(backgroundImage));
        this.frame.setLayout(new BorderLayout());
        this.frame.add(mainPanel, BorderLayout.CENTER);
        this.frame.setVisible(true);
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

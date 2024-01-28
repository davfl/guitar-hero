/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gioco;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author DAVIDE
 */
public class Username {
    private GridBagConstraints gbc;
    private JPanel panelUsername;
    private JFrame frame;
    private JPanel mainPanel;

    public Username(JFrame frame,JPanel mainPanel){
        this.frame=frame;
        this.mainPanel=mainPanel;
        gbc= new GridBagConstraints();
        this.panelUsername=new JPanel(new GridBagLayout());
    }
    public void pagina(){
        frame.getContentPane().removeAll();
        frame.repaint();
        JLabel lblUser = new JLabel("username:");
        lblUser.setFont(new Font("Arial", Font.BOLD, 30));
        lblUser.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1; // o qualsiasi altra posizione desiderata
        gbc.insets = new Insets(0, 0, 50, 0);
        panelUsername.add(lblUser, gbc);
        panelUsername.setBackground(Color.BLACK);
        JTextField txtUser= new JTextField(30);
        Border border= new LineBorder(Color.WHITE, 2);
        txtUser.setFont(new Font("Arial", Font.BOLD, 24));
        txtUser.setForeground(Color.WHITE);
        txtUser.setBorder(border);
        txtUser.setBackground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2; // o qualsiasi altra posizione desiderata
        gbc.insets = new Insets(0, 0, 0, 0);
        panelUsername.add(txtUser, gbc); 
        
        
        JButton invia= new JButton("invia");
        invia.setFont(new Font("Arial", Font.PLAIN, 24));
        invia.setForeground(Color.WHITE);
        invia.setContentAreaFilled(false);
        invia.setFocusPainted(false);
        invia.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        gbc.gridx = 0;
        gbc.gridy = 3; // o qualsiasi altra posizione desiderata
        gbc.insets = new Insets(10, 0, 0, 0);
        panelUsername.add(invia, gbc);
        frame.add(panelUsername);
        frame.revalidate();
        
            invia.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!txtUser.getText().isBlank()){
                        try {
                            Gioco g = new Gioco(frame, mainPanel, txtUser.getText());
                            g.openNewPage();
                        } catch (LineUnavailableException ex) {
                            java.util.logging.Logger.getLogger(Username.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            java.util.logging.Logger.getLogger(Username.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            java.util.logging.Logger.getLogger(Username.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                    } 
                }        
            });
    }
    
}

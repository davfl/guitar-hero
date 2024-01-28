package gioco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;

public class MainScreen {//implements KeyListener{
    private JFrame frame;
    private JPanel mainPanel;

    public MainScreen() {
        frame = new JFrame("Guitar Hero");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setResizable(false);
        
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.setOpaque(false);

        // Background Image
        ImageIcon backgroundImage = new ImageIcon("src/image/red.jpg");
        // labels
        JLabel titleLabel = new JLabel("Guitar Hero");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);

        // buttons
        JButton playButton = createStyledButton("Play");
        //JButton multiplayerButton = createStyledButton("Multiplayer");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // try {
                    Username u= new Username(frame, mainPanel);
                    u.pagina();
                    //Gioco g= new Gioco(frame, mainPanel);
                    //g.openNewPage();
               // } 
               /*  catch (LineUnavailableException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                */
            }
        });

        /*multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Gioco g= new Gioco(frame, mainPanel);
                    g.openNewPage();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
*/
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Padding
        mainPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(playButton, gbc);

       // gbc.gridy = 2;
        //mainPanel.add(multiplayerButton, gbc);

        // Set background image directly on the content pane
        frame.setContentPane(new JLabel(backgroundImage));
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
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



    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainScreen();
            }
        });
    }*/
}

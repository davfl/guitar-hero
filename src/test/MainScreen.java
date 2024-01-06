package test;

import javax.swing.*;
import java.awt.*;
import static java.awt.Color.blue;
import static java.awt.Color.green;
import static java.awt.Color.orange;
import static java.awt.Color.pink;
import static java.awt.Color.red;
import static java.awt.Color.white;
import static java.awt.Color.yellow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;

public class MainScreen implements KeyListener{
    private JFrame frame;
    private JPanel mainPanel;
    private int punteggio=0;
    private JPanel quad1;
    private JPanel quad2;
    private JPanel quad3;
    private JPanel quad4;
    private JPanel quad5;
    private JLabel score;
    public MainScreen() {
        frame = new JFrame("Guitar Hero");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        
        
        
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
        JButton multiplayerButton = createStyledButton("Multiplayer");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openNewPage();
                } 
                 catch (LineUnavailableException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openNewPage();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Padding
        mainPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(playButton, gbc);

        gbc.gridy = 2;
        mainPanel.add(multiplayerButton, gbc);

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

    private void openNewPage() throws IOException, LineUnavailableException {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.addKeyListener((KeyListener) this);

        JPanel newPage = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        newPage.setBackground(Color.BLACK);
        
        score = new JLabel("Score");
        score.setFont(new Font("Arial", Font.BOLD, 35));
        score.setForeground(Color.WHITE);
        gbc.gridx = 6;
        gbc.gridy = 1; // o qualsiasi altra posizione desiderata
        gbc.insets = new Insets(10, 50, 0, 0);
        newPage.add(score, gbc);
        
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

        // Quadratini
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.0; // Reset vertical weight
        
            quad1 = new JPanel();
            quad2 = new JPanel();
            quad3 = new JPanel();
            quad4 = new JPanel();
            quad5 = new JPanel();
            
            //set dei colori
            /*Color red = Color.red;
            Color yellow = Color.yellow;
            Color green = Color.green;
            Color pink = Color.pink;
            Color blue = Color.blue;
            quad1.setBackground(red);
            quad2.setBackground(yellow);
            quad3.setBackground(green);
            quad4.setBackground(pink);
            quad5.setBackground(blue);
*/
        
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
            quad1.setBackground(Color.BLACK);
            quad2.setBackground(Color.BLACK);
            quad3.setBackground(Color.BLACK);
            quad4.setBackground(Color.BLACK);
            quad5.setBackground(Color.BLACK);
            
            //BufferedImage img = ImageIO.read(new File("src/5.png"));
            //ImageIcon imga= new ImageIcon("src/image/img/verde.png");
            //JLabel imag= new JLabel(imga);
            quad1.add(new JLabel(new ImageIcon("src/image/img/verde.png")));
            quad2.add(new JLabel(new ImageIcon("src/image/img/rosso.png")));
            quad3.add(new JLabel(new ImageIcon("src/image/img/giallo.png")));
            quad4.add(new JLabel(new ImageIcon("src/image/img/blu.png")));
            quad5.add(new JLabel(new ImageIcon("src/image/img/arancio.png")));

            
           
           
            //new GestisciPallini(quad1, quad2, quad3, quad4, quad5).start();

            
            
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

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainScreen();
            }
        });
    }*/

        
    public void keyTyped(KeyEvent e) {
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
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prova;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author DAVIDE
 */
public class GestioneMusica {
    
    private final Clip clip;
    private String fileName;
    
    public GestioneMusica(String fileName) throws LineUnavailableException{
        this.fileName=fileName;
        clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
    }
    
    
    public void playSong()
    {
        try
        {
            clip.addLineListener(new LineListener()
            {
                @Override
                public void update(LineEvent event)
                {
                    if (event.getType() == LineEvent.Type.STOP)
                        clip.close();
                }
            });

            clip.open(AudioSystem.getAudioInputStream(new File(fileName)));
            clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }
}


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DAVIDE
 */
public class NewFrame {
    
    public NewFrame() throws IOException{
        BufferedImage raw = ImageIO.read(new File("immagini/ball.jpg"));
        BufferedImage img = blurImageBorder(raw, 1);

        JFrame frame = new JFrame();
        frame.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;

                g2d.setColor(Color.RED);
                g2d.drawLine(10, 10, 300, 100);

                g2d.translate(50, 200);
                g2d.rotate(Math.toRadians(30), getWidth() / 2.0, getHeight() / 2.0);

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                g2d.drawImage(img, 0, 0, this);
                g2d.fill(new Rectangle(-100, 100, 100, 100));
            }
        });
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
    }
    private static BufferedImage blurImageBorder(BufferedImage input, double border) {
        int w = input.getWidth();
        int h = input.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = output.createGraphics();
        g.drawImage(input, 0, 0, null);

        g.setComposite(AlphaComposite.DstOut);
        Color c0 = new Color(0x000000FF);

        // Left
        g.setPaint(new GradientPaint(new Point2D.Double(0, border), c0, new Point2D.Double(border, border), c0));
        g.fill(new Rectangle2D.Double(0, border, border, h- border - border));
// Right
        g.setPaint(new GradientPaint(new Point2D.Double(w - border, border), c0, new Point2D.Double(w, border), c0));
        g.fill(new Rectangle2D.Double(w- border, border, border, h- border - border));

        // Top
        g.setPaint(new GradientPaint(new Point2D.Double(border, 0), c0, new Point2D.Double(border, border), c0));
        g.fill(new Rectangle2D.Double(border, 0, w - border - border, border));

        // Bottom
        g.setPaint(new GradientPaint(new Point2D.Double(border, h - border), c0, new Point2D.Double(border, h), c0));
        g.fill(new Rectangle2D.Double(border, h - border, w - border - border, border));

        final float[] floatArray = new float[]{ 0, 1 };
        final Color[] colorArray = new Color[]{ c0, c0 };
// Top Left
        g.setPaint(new RadialGradientPaint(new Rectangle2D.Double(0, 0, border + border, border + border),
                floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(0, 0, border, border));

        // Top Right
        g.setPaint(new RadialGradientPaint(
                new Rectangle2D.Double(w - border - border, 0, border + border, border + border),
                floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(w - border, 0, border, border));

        // Bottom Left
        g.setPaint(new RadialGradientPaint(
                new Rectangle2D.Double(0, h - border - border, border + border, border + border),
                floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(0, h - border, border, border));
// Bottom Right
        g.setPaint(new RadialGradientPaint(
                new Rectangle2D.Double(w - border - border, h - border - border, border + border, border + border),
                floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(w - border, h - border, border, border));

        g.dispose();

        return output;
    }
    
    
    
}

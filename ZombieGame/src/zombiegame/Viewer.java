/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegame;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Xavier
 */
public class Viewer extends Canvas implements Runnable {
    private ZombieGame zombieGameViewer;
    private BufferedImage renderImage;
    private BufferedImage backgroundImage;
    private ArrayList <VisibleObject> visibleObjects;
    
    public Viewer(ZombieGame zombieGameViewer){
        this.zombieGameViewer=zombieGameViewer;
        this.visibleObjects = this.zombieGameViewer.getVisibleObjects();
        readBackgroundImage();
    }
    
    public void readBackgroundImage(){
       File image= new File("img/fondo.png");
        try {
            this.renderImage = ImageIO.read(image);
            this.backgroundImage = ImageIO.read(image);
        } catch (IOException ex) {
            Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(50);
            while (true) {
                try {
                    Thread.sleep(5);
                    bufferStrategy();

                } catch (InterruptedException ex) {
                    Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void bufferStrategy() {
        BufferStrategy bs = getBufferStrategy();
        Graphics2D g;
        
        
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics gBufer = bs.getDrawGraphics();

        this.renderImage = copyImage(this.backgroundImage);

        g = (Graphics2D) this.renderImage.getGraphics();

        for (int i = 0; i < this.visibleObjects.size(); i++) {
            this.visibleObjects.get(i).render(g);
        }

        gBufer.drawImage(this.renderImage, 0, 0, getWidth(), getHeight(), 0, 0, this.renderImage.getWidth(), this.renderImage.getHeight(), this);

        gBufer.dispose();
        bs.show();
    }
    
    public BufferedImage copyImage(BufferedImage b) {
        ColorModel cm = b.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = b.copyData(null);
        
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    
    
    
}

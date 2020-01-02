/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegame;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Xavier
 */
public class Wall extends Static {
    private int selectWall;

    public Wall(double x, double y, boolean state, ZombieGame zombieGameObjects, String id,int selectWall, int width, int height) {
        super(x, y, state, zombieGameObjects, id, width, height);
         
        this.selectWall=selectWall;
        
        for(int images=0 ; images<7 ; images++){
          File img=new File("img/walls/wall"+images+".png");
          try {
             setFrames(ImageIO.read(img));
            } catch (IOException ex) {
            Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
    }
    
    
    @Override
    public void render(Graphics2D g){
      g.drawImage(getFrames().get(this.selectWall), (int)getX(), (int)getY(),getWidth(),getHeight(),null);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Xavier
 */
public class ZombieGame extends JFrame implements ActionListener{
    private ArrayList <Pad> pads;
    private ArrayList <VisibleObject> visibleObjects;
    private Rules rules;
    private Viewer viewer;
    private Server server;
    
    public static void main(String[] args) {
        ZombieGame zombieGame = new ZombieGame();
    }
    
    public ZombieGame(){
       super("ZombieGame"); 
       this.pads=new ArrayList();
       this.visibleObjects=new ArrayList();
       this.server = new Server(this);
       this.viewer = new Viewer(this);
       setSize(960,640);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setResizable(true);
       setVisible(true);
       prepareGame();
    }
    
    public void prepareGame(){
       (new Thread(this.server)).start();
       (new Thread(this.viewer)).start();
       add(this.viewer);
       createWalls();
    }
    
    public void createWalls(){
       //Izquierda y derecha.
        for (int verticals = 96 ; verticals < 336 ; verticals+=48) {
            this.visibleObjects.add(new Wall(0,verticals,true,this,"GoodWall",1,16,48));
            this.visibleObjects.add(new Wall(945,verticals,true,this,"GoodWall",1,16,48)); 
        }
        
        this.visibleObjects.add(new Wall(0,288,true,this,"GoodWall",3,52,50));
        this.visibleObjects.add(new Wall(0,384,true,this,"GoodWall",4,52,50));
        this.visibleObjects.add(new Wall(910,288,true,this,"GoodWall",6,52,50));
        this.visibleObjects.add(new Wall(910,384,true,this,"GoodWall",5,52,50));
        
        for(int verticals=432 ; verticals<544 ; verticals+=48){
            this.visibleObjects.add(new Wall(0,verticals,true,this,"GoodWall",1,16,48));
            this.visibleObjects.add(new Wall(945,verticals,true,this,"GoodWall",1,16,48));
        }
        
        this.visibleObjects.add(new Wall(0,576,true,this,"GoodWall",1,16,34));
        this.visibleObjects.add(new Wall(945,576,true,this,"GoodWall",1,16,34));
        
        //Arriba y abajo.
        for(int horitzontals=30 ; horitzontals<864 ; horitzontals+=48){
            this.visibleObjects.add(new Wall(horitzontals,65,true,this,"GreatWall",0,48,16));
            this.visibleObjects.add(new Wall(horitzontals,625,true,this,"GreatWall",0,48,16));
        }
        
        this.visibleObjects.add(new Wall(893,65,true,this,"Esquina01",0,38,16));
        this.visibleObjects.add(new Wall(893,625,true,this,"Esquina01",0,38,16));
        
        //Esquinas.
        this.visibleObjects.add(new Wall(0,65,true,this,"Esquina00",2,32,32));
        this.visibleObjects.add(new Wall(930,65,true,this,"Good wall",2,32,32));
        this.visibleObjects.add(new Wall(0,609,true,this,"Good wall",2,32,32));
        this.visibleObjects.add(new Wall(930,609,true,this,"Good wall",2,32,32));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<VisibleObject> getVisibleObjects() {
        return visibleObjects;
    }
    
}

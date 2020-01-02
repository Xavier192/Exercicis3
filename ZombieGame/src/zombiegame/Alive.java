/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegame;

/**
 *
 * @author Xavier
 */
public abstract class Alive extends VisibleObject implements Runnable {
    private double velocity;

    public Alive(double x, double y, boolean state, ZombieGame zombieGameObjects, String id, int width, int height,double velocity) {
        super(x, y, state, zombieGameObjects, id, width, height);
        this.velocity=velocity;
    }
   

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegame;

import java.awt.Graphics2D;

/**
 *
 * @author Xavier
 */
public class Human extends Controlled {

    public Human(double x, double y, boolean state, ZombieGame zombieGameObjects, String id, int width, int height, double velocity) {
        super(x, y, state, zombieGameObjects, id, width, height, velocity);
    }
            
    @Override
    public void render(Graphics2D g) {
        g.fillOval((int) getX(), (int) getY(), 10, 10);
    }
}

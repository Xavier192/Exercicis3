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
public abstract class Static extends VisibleObject {
    
    public Static(double x, double y, boolean state, ZombieGame zombieGameObjects, String id, int width, int height) {
        super(x, y, state, zombieGameObjects, id, width, height);
    }
    
}

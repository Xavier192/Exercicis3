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
public class Server implements Runnable {
    private ZombieGame zombieGameServer;
    
    public Server(ZombieGame zombieGameServer){
        this.zombieGameServer=zombieGameServer;
    }
    
    @Override
    public void run() {
        System.out.println("Hola");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicis3;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Xavier
 */
public class Consultes {
    EntityManager em;
    
    public Consultes(EntityManager em){
    this.em=em;
    }
    
    //No modificam els atributs del client aquí, 
    //ho podriem fer en altres mètodes o directament al main.
    public void modificarClient(Client c){
        try{
         this.em.getTransaction().begin();
         //Utilitzam el mètode merge per a inserir un client
         //que ja existeix pero amb dades modificades (mateixa id).
         this.em.merge(c);  
         this.em.getTransaction().commit();   
        }catch(EntityNotFoundException e){
        e.printStackTrace();
        }
    }
    
    public Client cercarClientPerNif(String nif){//Obté un sol client per nif.
        Query clientPerId=this.em.createNamedQuery("ObtenirClientPerNif",Client.class);
        clientPerId.setParameter("nif",nif); //Li passam el nif del client a la query  
        Client c=(Client)clientPerId.getSingleResult();//Aconseguim el client.
        
        return c;//retornam el client
    }
    
    public void eliminarClient(int id){ //Cerca i elimina un client per nif.
        this.em.getTransaction().begin();
        try{
        Client c = this.em.find(Client.class,id);
        this.em.remove(c);//Mètode de l'entity manager per a borrar files de la base de dades.
        }catch(EntityNotFoundException e){
            e.printStackTrace();
        }
        this.em.getTransaction().commit();
    }
    
    /*Obté tots els clients que contenguin
    una serie de caràcters en el seu nom.*/
    public List <Client> cercarClientsPerNom(String nom){
    Query cercarClientsPerNom = em.createNamedQuery("ObtenirClientsPerNom",Client.class);
    String consulta="%"+nom+"%";
    
    cercarClientsPerNom.setParameter("nom",consulta);
    
    List<Client> llistaClients=cercarClientsPerNom.getResultList();
    
    return llistaClients;
    }
    
    //Obtenir un sol client per id.
    public Client obtenirClientPerId(int id){
    Client c=this.em.find(Client.class,id);
    
    if(c!=null){
    return c;    
    }
    else{
      System.out.println("No s'ha pogut trobar el client amb id "+id);
      return null;  
    }
    }
    //Obté tots els clients Que tenim a la base de dades.
    public List<Client> obtenirTotsElsClients(){
    Query cercarClientsPerNom = this.em.createNamedQuery("ObtenirTotsElsClients",Client.class);
    cercarClientsPerNom.setParameter("id",0);
    List <Client> llistaClients=cercarClientsPerNom.getResultList();
    
    return llistaClients;
    }
    //Obté tots els clients que estan assignats a un sector.
    public List<Client> obtenirClientsPerSector(String idSector){
    Query cercarClientsPerSector=this.em.createNamedQuery("ObtenirClientsPerSector",Client.class);
    cercarClientsPerSector.setParameter("idSector",idSector);
    List <Client> llistaClients=cercarClientsPerSector.getResultList();
    
    return llistaClients;
    }
    
    
    
    
    
    
    
    
    
    
}

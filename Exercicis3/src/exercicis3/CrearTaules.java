/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicis3;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.*;

/**
 *
 * @author Xavier
 */
public class CrearTaules {

    ArrayList<Comercial> arrayComercials;
    ArrayList<Zona> arrayZona;
    ArrayList<Tarticles> arrayArticles;
    ArrayList<Sector> arraySector;
    ArrayList<Client> arrayClients;
    private int idArticle = 0;

    /**
     * @param args the command line arguments
     */
    public CrearTaules() {
        this.arrayComercials = new ArrayList();
        this.arrayZona = new ArrayList();
        this.arrayArticles = new ArrayList();
        this.arraySector = new ArrayList();
        this.arrayClients = new ArrayList();
    }

    public static void main(String[] args) {
        CrearTaules e3 = new CrearTaules();
        
        EntityManagerFactory emfProd = Persistence.createEntityManagerFactory("ejercici3");
        EntityManager emProd = emfProd.createEntityManager();
        
        //Borrar les files si existeixenper a no repetir primary keys.
        e3.borrarRegistresSiExisteixen(emProd);
        
        //Creació dels objectes.
        System.out.println("");
        e3.crearComercial("32459153B", "659483745", "Joan Torrella");
        e3.crearComercial("2948345Z", "649382341", "Aina Martinez");
        e3.crearComercial("3948231B", "658293812", "Manuel Franco");
        
        System.out.println("");
        e3.crearZona("z1", "Porreres", "Carrer de ca'n suau nº 23", "32459153B");
        e3.crearZona("z2", "Felanitx", "Carrer de ca'n Bernat nº33", "2948345Z");
        e3.crearZona("z3", "Manacor", "Carrer de Maria Teresa nº11", "3948231B");
        System.out.println("");
        e3.crearArticle("Pastilla de sabó", 5, 3);
        e3.crearArticle("Tovallola", 10, 4);
        e3.crearArticle("Tovallola gran", 5, 10);
        e3.crearArticle("Pastilla de Ibuprofeno", 100, 5);
        e3.crearArticle("Taula", 20, 50);
        e3.crearArticle("Pastilles de Diazepam", 200, 3);
        System.out.println("");
        e3.crearSector("s1", "Sector Mèdic");
        e3.crearSector("s2", "Sector Lavanderia");
        System.out.println("");
        e3.crearClient("5938123B", "658472125", "s1", "z1", "Xavier Lliteras");
        e3.crearClient("5442211C", "685948234", "s2", "z2", "Josep Amengual");
        System.out.println("");
        e3.asignarArticleAlSector("s1", "Pastilla de Ibuprofeno");
        e3.asignarArticleAlSector("s1", "Pastilles de Diazepam");
        e3.asignarArticleAlSector("s2", "Pastilla de sabó");
        e3.asignarArticleAlSector("s2", "Taula");
        e3.asignarArticleAlSector("s2", "Tovallola gran");
        e3.asignarArticleAlSector("s2", "Tovallola");

        e3.transaccionarObjectes(e3.getArrayComercials(), e3.getArrayComercials().size(), emProd);//Fa les transaccions del objectes, les treu de l'arraylist i les introdueix a la base de dades.
        e3.transaccionarObjectes(e3.getArrayZona(), e3.getArrayZona().size(), emProd);
        e3.transaccionarObjectes(e3.getArrayArticles(), e3.getArrayArticles().size(), emProd);
        e3.transaccionarObjectes(e3.getArraySector(), e3.getArraySector().size(), emProd);
        e3.transaccionarObjectes(e3.getArrayClients(), e3.getArrayClients().size(), emProd);
        
        //Consultes
        
       
        
    }
    //Aquest mètode insereix qualsevol tipus de objecte a la base de dades.
    public void transaccioObjectes(EntityManager em, Object o) {
        em.getTransaction().begin();//Començam una transaccio
        em.persist(o);//Inserim a la base de dades l'objecte.
        em.getTransaction().commit();//guardam canvis
    }

    public void borrarRegistresSiExisteixen(EntityManager em) {
        try {
            em.getTransaction().begin();
            Sector s = em.find(Sector.class,"s1");
            Sector s2=em.find(Sector.class,"s2");
            Query q1 = em.createQuery("delete from Client where idClient>0");
            Query q2 = em.createQuery("delete from Zona where idZona!='f'");
            Query q3 = em.createQuery("delete from Comercial where nif!='a1'");
            
            q1.executeUpdate();
            q2.executeUpdate();
            q3.executeUpdate();
            
            if(s!=null){
             em.remove(s);
            }
            if(s2!=null){
             em.remove(s2);  
            }
            em.getTransaction().commit();
        } catch (SecurityException | IllegalStateException | RollbackException e) {
            e.printStackTrace();
        }
    }

    public void transaccionarObjectes(ArrayList objecte, int longitut, EntityManager em) {
        for (int objectes = 0; objectes < longitut; objectes++) {
            transaccioObjectes(em, objecte.get(objectes));
        }
    }

    public void asignarArticleAlSector(String idSector, String descripcio) {
        Sector s = null;
        Tarticles ta = null;
        if (cercarSector(idSector) != null) {
            if (cercarArticle(descripcio) != null) {
                s = cercarSector(idSector);
                ta = cercarArticle(descripcio);
                s.setArticle(ta);
            } else {
                System.out.println("L'article amb id " + idArticle + " que es vol assignar al sector amb id " + idSector + " no existeix");
            }
        } else {
            System.out.println("El sector amb id" + idSector + " no existeix, per tant no es pot assignar l'article amb id " + idArticle);
        }
    }

    public void crearClient(String nif, String tlf, String idSector, String idZona, String nom) {//A n'aquest mètode es creen els clients i es comprova que no estiguin ja creats.
        Sector s = null;
        Zona z = null;
        if (cercarClient(nif) == null) {//Si no es troba el client el cream.
            if (cercarSector(idSector) != null) {
                if (cercarZona(idZona) != null) {
                    s = cercarSector(idSector);
                    z = cercarZona(idZona);
                    this.arrayClients.add(new Client(nif, tlf, s, z, nom));
                    System.out.println("Client amb id " + nif + " creat amb èxit");
                } else {
                    System.out.println("Zona amb id " + idZona + " no existent, per tant no es pot assignar ni crear el client amb id "+nif);
                }
            } else {
                System.out.println("Sector amb id " + idSector + " no existent, per tant no es pot asignar ni crear el client amb id "+nif);
            }

        } else {
            System.out.println("Client amb id " + nif + " ja creat anteriorment");//==================>
        }
    }

    public void crearComercial(String nif, String tlf, String nom) {
        if (cercarComercial(nif) == null) {
            this.arrayComercials.add(new Comercial(nif, tlf, nom));
            System.out.println("Comercial amb id " + nif + " creat amb èxit!");
        } else {
            System.out.println("Comercial amb id " + nif + " ja creat anteriorment");
        }
    }

    public void crearArticle(String descripcio, int quantitat, int preu) {
        this.idArticle++;
        if (cercarArticle(descripcio) == null) {
            this.arrayArticles.add(new Tarticles(descripcio, quantitat, preu));
            System.out.println("Article amb id " + this.idArticle + " creat amb èxit");
        } else {
            System.out.println("Article amb id " + this.idArticle + " ja creat anteriorment");
        }

    }

    public void crearZona(String idZona, String nomZona, String direccio, String idComercial) {
        Comercial c = null;
        if (cercarZona(idZona) == null) {
            if (cercarComercial(idComercial) != null) {
                c = cercarComercial(idComercial);
                this.arrayZona.add(new Zona(idZona, nomZona, direccio, c));
                System.out.println("Zona amb id " + idZona + " creada amb èxit!");
            } else {
                System.out.println("No es pot crear la zona amb id" + idZona + " Perque el comercial amb id " + idComercial + " que se li vol assignar no existeix");
            }

        } else {
            System.out.println("Zona amb id " + idZona + " ja creada anteriorment");
        }
    }

    public void crearSector(String idSector, String nomSector) {
        if (cercarSector(idSector) == null) {
            this.arraySector.add(new Sector(idSector, nomSector));
            System.out.println("Sector amb id " + idSector + " creat amb èxit");
        } else {
            System.out.println("Sector amb id " + idSector + " ja creat anteriorment");
        }
    }

    public Client cercarClient(String nif) {//Aquests mètodes només estan creats per a no repetir dos clients quan els cream (encara que no els transaccionem).
        Client c = null;
        for (int clients = 0; clients < this.arrayClients.size(); clients++) {
            if (this.arrayClients.get(clients).getNif().equals(nif)) {
                c = this.arrayClients.get(clients);
            }
        }
        return c;
    }

    public Comercial cercarComercial(String idComercial) {//Aquests mètodes només estan creats per a no repetir dos clients quan els cream.
        Comercial c = null;
        for (int comercials = 0; comercials < this.arrayComercials.size(); comercials++) {
            if (this.arrayComercials.get(comercials).getNif().equals(idComercial)) {//Comparam Strings
                c = this.arrayComercials.get(comercials);
            }
        }
        return c;
    }

    public Tarticles cercarArticle(String descripcio) {//Aquests mètodes només estan creats per a no repetir dos clients quan els cream.
        Tarticles Ta = null;
        for (int articles = 0; articles < this.arrayArticles.size(); articles++) {
            if (this.arrayArticles.get(articles).getDescripcio().equals(descripcio)) {//Comparam int
                Ta = this.arrayArticles.get(articles);
            }
        }
        return Ta;
    }

    public Sector cercarSector(String idSector) {
        Sector s = null;
        for (int Sector = 0; Sector < this.arraySector.size(); Sector++) {
            if (this.arraySector.get(Sector).getIdSector().equals(idSector)) {//Comparam String
                s = this.arraySector.get(Sector);
            }
        }
        return s;
    }

    public Zona cercarZona(String idZona) {
        Zona z = null;
        for (int zona = 0; zona < this.arrayZona.size(); zona++) {
            if (this.arrayZona.get(zona).getIdZona().equals(idZona)) {
                z = arrayZona.get(zona);
            }
        }
        return z;
    }

    public ArrayList<Comercial> getArrayComercials() {
        return arrayComercials;
    }

    public void setArrayComercials(ArrayList<Comercial> arrayComercials) {
        this.arrayComercials = arrayComercials;
    }

    public ArrayList<Zona> getArrayZona() {
        return arrayZona;
    }

    public void setArrayZona(ArrayList<Zona> arrayZona) {
        this.arrayZona = arrayZona;
    }

    public ArrayList<Tarticles> getArrayArticles() {
        return arrayArticles;
    }

    public void setArrayArticles(ArrayList<Tarticles> arrayArticles) {
        this.arrayArticles = arrayArticles;
    }

    public ArrayList<Sector> getArraySector() {
        return arraySector;
    }

    public void setArraySector(ArrayList<Sector> arraySector) {
        this.arraySector = arraySector;
    }

    public ArrayList<Client> getArrayClients() {
        return arrayClients;
    }

    public void setArrayClients(ArrayList<Client> arrayClients) {
        this.arrayClients = arrayClients;
    }

}

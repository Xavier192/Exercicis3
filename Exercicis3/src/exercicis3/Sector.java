/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicis3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Xavier
 */
@Entity
@Table(name="Sector")//Nom de la taula

public class Sector implements Serializable{
    @Id//atribut idSector primary key 
    @Column(length=30)
    
    private String idSector;
    //Atribut descripcio limitat a 100 caràcters
    @Column(length=100)
    private String descripcio;
    //ArrayList de articles generarà una taula intermitja a la base de dades
    /*Es recuperarà de forma diferida i l'atribut cascade ens 
    diu que es borraran les foreign keys associades si es borra el sector*/
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private  List <Tarticles>  articles=new ArrayList<Tarticles>();
    public Sector(String idSector, String nomSector){
        this.idSector=idSector;
        this.descripcio=nomSector;
    }
    public Sector(){
        
    }

    public String getIdSector() {
        return idSector;
    }

    public void setIdSector(String idSector) {
        this.idSector = idSector;
    }

    public String getNomSector() {
        return descripcio;
    }

    public void setNomSector(String nomSector) {
        this.descripcio = nomSector;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public List<Tarticles> getArticles() {
        return articles;
    }

    public void setArticles(List<Tarticles> articles) {
        this.articles = articles;
    }
    
    public void setArticle(Tarticles a){
    this.articles.add(a);
    }
    
    
    
}

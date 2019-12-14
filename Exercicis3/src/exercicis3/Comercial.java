/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicis3;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Xavier
 */
@Entity
@Table(name="Comercial")//Nom de la taula que es crearà a l'sgbd.
public class Comercial implements Serializable{
    @Id//Primary key de la taula Comercial.
    //Aquest atribut com a màxim tendrà 15 caràcters de longitut
    @Column(length=15) 
    private String nif;
    //Aquest atribut com a màxim tendrà 175 caràcters de longitut
    @Column(length=175)
    private String nom;
    private String tlf;
    //Constructor reb tots els atributs per paràmetre. 
    public Comercial(String nif,String tlf,String nom){
     this.nif=nif;
     this.nom=nom;
     this.tlf=tlf;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String id) {
        this.nif = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }
    
}

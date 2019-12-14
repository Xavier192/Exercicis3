/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicis3;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Xavier
 */
@Entity
@Table(name="Tarticles")
public class Tarticles implements Serializable {
    //Indica que l'atribut es la primary key
    @Id
    //La primary key es generarà automàticament
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idArticle;
    //Restricció de longitut maxim 150 caràcters.
    @Column(length=150)
    private String descripcio;
    private int quantitat;
    private int preu;
    
    //Constructor: quan es crea l'objecte també se li assignen les característiques
    public Tarticles(String descripcio, int quantitat, int preu){
        this.descripcio=descripcio;
        this.quantitat=quantitat;
        this.preu=preu;
    }
    
    public Tarticles(){
        
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public int getPreu() {
        return preu;
    }

    public void setPreu(int preu) {
        this.preu = preu;
    }
}

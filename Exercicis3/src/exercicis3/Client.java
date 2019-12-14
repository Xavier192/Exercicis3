/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicis3;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Xavier
 */
@Entity
@Table(name="Client",uniqueConstraints={@UniqueConstraint(columnNames="nif")})//La taula a l'sgbd s'anomenarà Client.
@NamedQueries({
 @NamedQuery(name="ObtenirClientPerNif" 
 ,query="SELECT clien from Client clien where clien.nif=:nif" ),
 @NamedQuery(name="ObtenirClientsPerNom",
 query="SELECT clien from Client clien where clien.denominacio like :nom"),
 @NamedQuery(name="ObtenirTotsElsClients",
 query="SELECT clien from Client clien where clien.idClient>:id"),
 @NamedQuery(name="ObtenirClientsPerSector"
 ,query="SELECT clien from Client clien where clien.sector.idSector=:idSector")
})
public class Client implements Serializable {
    @Id//Declaram el següent atribut com a primary key.
    //La primary key es generarà automàticament.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idClient;
    //La columna nif màxim tendrà 15 caràcters.
    @Column(length=15)
    private String nif;
    private String tlf;
    //La columna denominacio màxim tendrà 120 caràcters.
    @Column(length=120)
    private String denominacio;
    //Relació ManyToOne entre client i zona
    @ManyToOne
    private Zona zona;
    //Relació manytoOne entre client i sector.
    @ManyToOne
    private Sector sector;
    //Constructor reb per paràmetre tots els atributs de la classe.
    public Client(String nif,String tlf,Sector sector,Zona zona,String nom){
        this.nif=nif;
        this.tlf=tlf;
        this.sector=sector;
        this.zona=zona;
        this.denominacio=nom;
        this.zona=zona;
        this.sector=sector;
    }

    public String getNom() {
        return denominacio;
    }

    public void setNom(String nom) {
        this.denominacio = nom;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    
    
}

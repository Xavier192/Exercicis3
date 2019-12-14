/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicis3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *
 * @author Xavier
 */
@Entity
@Table(name = "Zona")
public class Zona implements Serializable {
    @Id//Definim aquest atribut com a primary key.
    //La primary key serà de màxim 30 caràcters.
    @Column(length = 30)
    private String idZona;
    //La descripció seà com a màxim de 100 caràcters
    @Column(length = 100)
    private String descripcio;
    private String direccio;
    //Relacio oneToMany entre Zona i client.
    //La llista de clients es recuperara de forma diferida "FetchType.Lazy".
    @OneToMany(mappedBy = "zona", fetch = FetchType.LAZY)
    //S'ordenara per nom.
    @OrderBy(value = "denominacio")
    private List<Client> clients;
    //Relacio one to one amb el comercial.
    @OneToOne()
    private Comercial comercial;
    //Se li passarà per paràmetre tots els atributs d la classe
    public Zona(String idZona, String nomZona, String direccio,Comercial comercial) {
        this.comercial=comercial;
        this.idZona = idZona;
        this.descripcio = nomZona;
        this.direccio = direccio;
        this.clients = new ArrayList<Client>();
    }

    public String getIdZona() {
        return idZona;
    }

    public void setIdZona(String idZona) {
        this.idZona = idZona;
    }

    public String getNomZona() {
        return descripcio;
    }

    public void setNomZona(String nomZona) {
        this.descripcio = nomZona;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

}

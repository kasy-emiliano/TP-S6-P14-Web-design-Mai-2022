/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.TPS6P14WebdesignMai2022.model;

import demo.TPS6P14WebdesignMai2022.generic.Attr;
import demo.TPS6P14WebdesignMai2022.generic.ClassAnotation;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * 
 */

/**
 *
 * 
 */
@Entity
@ClassAnotation(table = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Attr(isPrimary = true)
    int id;
    @Attr
    String nom;
    @Attr
    String mdp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Admin() {
    }

    public Admin(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
    }
  
}

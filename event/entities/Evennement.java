/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.entities;

import java.time.LocalDate;

/**
 *
 * @author MSI
 */
public class Evennement {
    private int id;
    private String nom;
    private String lieu;
    private LocalDate date;

    public String getNom() {
        return nom;
    }
     public int getid() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
     public void setid(int id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Evennement() {
    }

    public Evennement(int id,String nom, String lieu, LocalDate date) {
        this.id =id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Evennement{" + "nom=" + nom + ", lieu=" + lieu + ", date=" + date + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsfx.beans;

import java.time.*;
/**
 *
 * @author boukhoulda
 */
public class Utilisateur {

    /* Attributs */
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String cellule;
    private String couleur;

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    private LocalDateTime dateInscription;
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setCellule(String cellule) {
        this.cellule = cellule;
    }
    
    public void setDateInscription(LocalDateTime dateInscription) {
    this.dateInscription = dateInscription;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getCellule() {
        return cellule;
    }
    
    public LocalDateTime getDateInscription() {
    return dateInscription;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsfx.beans;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 *
 * @author boukhoulda
 */
public class Tache {
    private String tache;
    private String graphe;
    private String statut;
    private String couleur;
    private LocalDateTime dateCreation;
    private LocalDate dateEventuelle;
    private LocalDate dateReelle;
    private String pourUtilisateur;
    private String commentaire;
    private int id;

    public String getPourUtilisateur() {
        return pourUtilisateur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setPourUtilisateur(String pourUtilisateur) {
        this.pourUtilisateur = pourUtilisateur;
    }
    public LocalDate getDateEventuelle() {
        return dateEventuelle;
    }

    public void setDateEventuelle(LocalDate dateEventuelle) {
        this.dateEventuelle = dateEventuelle;
    }

    public LocalDate getDateReelle() {
        return dateReelle;
    }

    public void setDateReelle(LocalDate dateReelle) {
        this.dateReelle = dateReelle;
    }
    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getId() {
        return id;
    }

     
    public void setId(int id) {
        this.id = id;
    }

      public LocalDateTime getDateCreation() {
        return dateCreation;
      }
      
  public void setDateCreation(LocalDateTime dateCreation) {
         this.dateCreation = dateCreation;
  }
  
    public String getTache() {
        return tache;
    }

    public String getGraphe() {
        return graphe;
    }

    public void setGraphe(String graphe) {
        this.graphe = graphe;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}

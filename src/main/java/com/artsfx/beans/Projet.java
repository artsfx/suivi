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
public class Projet {
    
private String graphe;
private String clli;
private String hp;
private String cellule;
private LocalDateTime dateCreation;
private String moisLivraison;
    public String getCellule() {
        return cellule;
    }

    public String getMoisLivraison() {
        return moisLivraison;
    }

    public void setMoisLivraison(String moisLivraison) {
        this.moisLivraison = moisLivraison;
    }

    public void setCellule(String cellule) {
        this.cellule = cellule;
    }

    public String getGraphe() {
        return graphe;
    }

    public void setGraphe(String graphe) {
        this.graphe = graphe;
    }

    public String getClli() {
        return clli;
    }

    public void setClli(String clli) {
        this.clli = clli;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }



}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsfx.forms;
/**
 *
 * @author boukhoulda
 */
import com.artsfx.beans.Projet;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class ProjetForm {
    /* les attributs */
    private static final String CHAMP_GRAPHE = "graphe";
    private static final String CHAMP_CLLI = "clli";
    private static final String CHAMP_HP = "homepass";
    private static final String CHAMP_CELLULE = "cellule";
    private static final String CHAMP_MOIS = "mois_livraison";
    Projet projet = new Projet();

    private Map<String, String> erreurs = new HashMap<>();
    
    public Map<String, String> getErreurs() {
    return erreurs;
    }
    private void setErreurs(String champ, String erreur) {
    erreurs.put(champ, erreur);
    }

    public Projet creerProjet(HttpServletRequest request) {
    String graphe = getValeurChamp(CHAMP_GRAPHE, request);
    String clli = getValeurChamp(CHAMP_CLLI, request);
    String hp = getValeurChamp(CHAMP_HP, request);
    String cellule = getValeurChamp(CHAMP_CELLULE, request);
    String moisLivraison = getValeurChamp(CHAMP_MOIS, request);
    try {
    validationGraphe(graphe);
    } catch (Exception e) {
    setErreurs(CHAMP_GRAPHE, e.getMessage());
    }
    
    
    try {
    validationCLLI(clli);
    } catch (Exception e) {
    setErreurs(CHAMP_CLLI, e.getMessage());
    }
   
    
    try {
    validationHP(hp);
    } catch (NumberFormatException e){
    setErreurs(CHAMP_HP, e.getMessage());
    }
         
    projet.setGraphe(graphe);
    projet.setClli(clli);
    projet.setHp(hp);
    projet.setCellule(cellule);
    projet.setDateCreation(LocalDateTime.now());
    projet.setMoisLivraison(moisLivraison);
return projet;
    }
    
    private static void validationHP(String hp) throws NumberFormatException {
    try {
           Integer.parseInt(hp);
      }
    catch (NumberFormatException e) {
    throw new NumberFormatException("Vous devez entrer un nombre entier.");
    } 
    }
    private static void validationCLLI(String clli) throws Exception {
    if (clli == null || clli.trim().length() != 8) {
    throw new Exception("Le CLLI doit contenir 8 caractères");
    }
    }
    private static void validationGraphe(String graphe) throws Exception {
     if (graphe == null || graphe.trim().length() != 6) {
     throw new Exception("Le numéro de graphe doit contenir 6 caractères.");
     }
    }
    
    public String getValeurChamp(String champ, HttpServletRequest request) {
    String valeurChamp = request.getParameter(champ);
    if (valeurChamp == null || valeurChamp.trim().length() == 0) {
            return null;
        } else {
            return valeurChamp.trim();
    }
}
}

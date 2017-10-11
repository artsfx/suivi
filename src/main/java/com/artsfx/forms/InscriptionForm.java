/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsfx.forms;

import com.artsfx.beans.Utilisateur;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author boukhoulda
 */
public final class InscriptionForm {
    
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_CONF = "confirmation";
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_PRENOM = "prenom";
    private static final String CHAMP_CELLULE = "cellule";
    private static final String CHAMP_COULEUR = "couleur";


    private String resultat;
    private Map<String, String> erreurs = new HashMap<>();
    
    public String getResultat() {
    return resultat;
    }
    
    public Map<String, String> getErreurs() {
    return erreurs;
    }
    Utilisateur utilisateur = new Utilisateur();
    
    public Utilisateur inscrireUtilisateur(HttpServletRequest request) {
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        String confirmation = getValeurChamp(request, CHAMP_CONF);
        String nom = getValeurChamp(request, CHAMP_NOM);
        String prenom = getValeurChamp(request, CHAMP_PRENOM);
        String cellule = getValeurChamp(request, CHAMP_CELLULE);
        String couleur = getValeurChamp(request, CHAMP_COULEUR);
            try {
            validationNom(nom);
        } catch (Exception e) {
            setErreurs(CHAMP_NOM, e.getMessage());
        }
        utilisateur.setNom(nom);
        
        
        try {
        validationPrenom(prenom);
        }
        catch (Exception e) {
                setErreurs(CHAMP_PRENOM, e.getMessage() );
                }
        utilisateur.setPrenom(prenom);
        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreurs(CHAMP_EMAIL, e.getMessage());
        }
        
        utilisateur.setEmail(email);
        try {
            validationMotDePasse(motDePasse, confirmation);
        } catch (Exception e) {
            setErreurs(CHAMP_PASS, e.getMessage());
            setErreurs(CHAMP_CONF, null);
        }
        utilisateur.setMotDePasse(motDePasse);
    utilisateur.setCouleur(couleur);
        
        if (erreurs.isEmpty()) {
        resultat = "Succès de l'inscription";
        } else resultat = "Échec de l'inscription";
        
                
         try {
    validationCellule(cellule) ;
} catch (Exception e) {
setErreurs(CHAMP_CELLULE, e.getMessage());
}
         
        utilisateur.setCellule(cellule);
        utilisateur.setDateInscription(LocalDateTime.now());
        return utilisateur;

    }
    
   
    
    private static void validationEmail(String email) throws Exception {
        
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse email valide.");
            } 
        } else {
                throw new Exception("Entrer une adresse email.");
            }
    }
    
    private static void validationMotDePasse(String motDePasse, String confirmation) throws Exception {
        if (motDePasse != null && confirmation != null) {
            if (motDePasse.equals(confirmation)) {
                if (motDePasse.trim().length() < 3) {
                    throw new Exception("Veuillez entrer un mot de passe valide.");
                }
            } else {
                throw new Exception("Les mots de passe que vous avez entré ne sont pas identiques");
            }
        } else {
            throw new Exception("Veuillez SVP entrer un mot de passe.");
        }
    }
    
    private static void validationNom(String nom) throws Exception {
        if (nom != null && nom.trim().length() < 3) {
            throw new Exception("Veuillez entrer un nom valide.");
        }
    }
    
      private static void validationPrenom(String prenom) throws Exception {
        if (prenom != null && prenom.trim().length() < 3) {
            throw new Exception("Veuillez entrer un nom valide.");
        }
    }
    
      private static void validationCellule(String cellule) throws Exception {
      if (cellule == null) {
      throw new Exception("Veuillez choisir votre cellule.");
      }
      }
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }
    
    private void setErreurs(String champ, String message) {
        erreurs.put(champ, message);
    }
    

}
